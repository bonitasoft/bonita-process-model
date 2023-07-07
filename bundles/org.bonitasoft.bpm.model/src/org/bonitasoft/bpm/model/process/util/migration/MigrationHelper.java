/**
 * Copyright (C) 2022 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.process.util.migration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.bpm.model.process.Messages;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.spi.history.Release;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A helper for EMF resources, which helps in performing the model migration with Edapt.
 */
public class MigrationHelper {

    /** All helper instances */
    private static final Map<Resource, MigrationHelper> INSTANCES = new WeakHashMap<>();

    /**
     * Get a helper for migration
     * 
     * @param emfResource the EMF model resource
     * @param streamSupplier supplies a stream with the resource content for eventual information parsing
     * @return the corresponding migration helper
     * @throws IOException the exception which occurred during parsing
     * @throws SAXException the exception which occurred during parsing
     * @throws ParserConfigurationException the exception which occurred during parsing
     */
    public static MigrationHelper getHelper(Resource emfResource, InputStreamSupplier streamSupplier)
            throws IOException, SAXException, ParserConfigurationException {
        return INSTANCES.computeIfAbsent(emfResource, r -> new MigrationHelper(r, streamSupplier))
                .orThrowParseException();
    }

    /** The resource this helper works with */
    private Resource helpedResource;
    /** Edapt migrator */
    private Migrator migrator;
    /** The eventual exception thrown during the resource parsing */
    private Optional<? extends Exception> storedParseException = Optional.empty();
    /** The content handler holding information about original resource */
    private ContentHandler informationHandler;
    /** The status about model version comparison */
    private IStatus modelVersionStatus;

    /**
     * Default Constructor.
     * 
     * @param resource the decorated EMF resource
     * @param streamSupplier supplies a stream with the resource content for information parsing
     */
    private MigrationHelper(Resource resource, InputStreamSupplier streamSupplier) {
        helpedResource = resource;
        // parse the resource for nsURI and model version
        parseForInformation(resource, streamSupplier);
        // try and compare the model version with the current version
        modelVersionStatus = compareModelVersions(resource.getURI().lastSegment());
        // init the migrator
        initializeMigrator(resource);
    }

    /**
     * Compare the model version with the current one and store the corresponding validation status.
     * Resulting status should be :
     * <ul><li>OK when the version is already the current one.</li>
     * <li>WARNING when the version is a previous one, which we can eventually migrate.</li>
     * <li>ERROR when the version is later than the current version, not defined, or incorrect.</li></ul>
     * 
     * @param filename the name of the resource
     * @return the model version comparison resulting status
     */
    private IStatus compareModelVersions(String filename) {
        String modelVersion = getModelVersion();
        if (modelVersion == null || !HistoryUtils.IS_KNOWN_VERSION.test(modelVersion)) {
            return Status.error(String.format(Messages.incompatibleModelVersion, filename));
        } else {
            ArtifactVersion version = new DefaultArtifactVersion(modelVersion);
            ArtifactVersion currentVersion = new DefaultArtifactVersion(HistoryUtils.CURRENT_MODEL_VERSION);
            switch (currentVersion.compareTo(version)) {
                case 0:
                    return Status.OK_STATUS;
                case 1:
                    return Status.warning(String.format(Messages.migrationWillBreakRetroCompatibility, filename));
                case -1:
                default:
                    return Status.error(String.format(Messages.incompatibleModelVersion, filename));
            }
        }
    }

    /**
     * Get itselft, unless there was an exception during initial parsing
     * 
     * @return itself
     * @throws IOException the exception which occurred during parsing
     * @throws SAXException the exception which occurred during parsing
     * @throws ParserConfigurationException the exception which occurred during parsing
     */
    private MigrationHelper orThrowParseException() throws IOException, SAXException, ParserConfigurationException {
        if (storedParseException.filter(IOException.class::isInstance).isPresent()) {
            throw storedParseException.map(IOException.class::cast).get();
        } else if (storedParseException.filter(SAXException.class::isInstance).isPresent()) {
            throw storedParseException.map(SAXException.class::cast).get();
        } else if (storedParseException.filter(ParserConfigurationException.class::isInstance).isPresent()) {
            throw storedParseException.map(ParserConfigurationException.class::cast).get();
        } else {
            return this;
        }
    }

    /**
     * Parse the resource for information relative to namespace and model version
     * 
     * @param resource the decorated EMF resource
     * @param streamSupplier supplies a stream with the resource content for information parsing
     */
    private void parseForInformation(Resource resource, InputStreamSupplier streamSupplier) {
        try (InputStream newStream = streamSupplier.get();) {
            /*
             * Now, get namespace URI and model version.
             * Inspire from org.eclipse.emf.edapt.migration.ReleaseUtils.getNamespaceURI_SAX(InputStream)
             */
            final ContentHandler contentHandler = new ContentHandler();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            // to be compliant with Sonar's java:S2755, completely disable DOCTYPE declaration:
            parserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            parserFactory.setNamespaceAware(true);
            XMLReader reader = parserFactory.newSAXParser().getXMLReader();

            reader.setContentHandler(contentHandler);
            try {
                informationHandler = contentHandler;
                reader.parse(new InputSource(newStream));
            } catch (final SAXException e) {
                // loading should fail immediately after reading relevant information
            }
        } catch (IOException | SAXException | ParserConfigurationException exception) {
            // store the exception, and throw it when trying to get the instance
            storedParseException = Optional.of(exception);
        }
    }

    /** Content handler for extraction of namespace URI and model version using SAX. */
    private static class ContentHandler extends DefaultHandler {

        /** Namespace URI. */
        private String nsURI;

        /** Model version */
        private String modelVersion;

        /** {@inheritDoc} */
        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if (!ExtendedMetaData.XMI_URI.equals(uri) && !ExtendedMetaData.XML_SCHEMA_URI.equals(uri)) {
                // element should be "process:MainProcess" but namespace prefix may enventually change
                if (qName.endsWith(":" + ProcessPackage.Literals.MAIN_PROCESS.getName())) {
                    nsURI = uri;
                    modelVersion = attributes
                            .getValue(ProcessPackage.Literals.MAIN_PROCESS__BONITA_MODEL_VERSION.getName());
                    throw new SAXException();
                }
            }
        }
    }

    /**
     * Initialize the Edapt migrator
     * 
     * @param resource the decorated EMF resource
     */
    private void initializeMigrator(Resource resource) {
        try {
            migrator = new SingleResourceMigrator();
        } catch (final MigrationException e) {
            final String location = resource.getURI() == null ? null : resource.getURI().toString();
            class DiagnosticWrappedException extends WrappedException implements Resource.Diagnostic {

                private static final long serialVersionUID = 1L;

                public DiagnosticWrappedException(Exception exception) {
                    super(exception);
                }

                public String getLocation() {
                    return location;
                }

                public int getColumn() {
                    return 0;
                }

                public int getLine() {
                    return 0;
                }
            }
            resource.getErrors().add(new DiagnosticWrappedException(e));
        }
    }

    /**
     * Get the Edapt migrator
     * 
     * @return the migrator
     */
    public Migrator getMigrator(final String nsURI) {
        if (!migrator.getNsURIs().contains(nsURI)) {
            return MigratorRegistry.getInstance().getMigrator(nsURI);
        }
        return migrator;
    }

    /**
     * Returns the namespace URI.
     * 
     * @return namespace URI
     */
    public String getNsURI() {
        return Optional.ofNullable(informationHandler).map(c -> c.nsURI).orElse(null);
    }

    /**
     * Returns the model version
     * 
     * @return mode version
     */
    public String getModelVersion() {
        return Optional.ofNullable(informationHandler).map(c -> c.modelVersion).orElse(null);
    }

    /**
     * Get the validation status for the model version regarding migration.
     * Resulting status should be :
     * <ul><li>OK when the model version is already the current one.</li>
     * <li>WARNING when the model version is a previous one, which we can eventually migrate.</li>
     * <li>ERROR when the model version is later than the current version, not defined, or incorrect.</li></ul>
     * 
     * @return the model version status for migration
     */
    public IStatus getModelVersionStatus() {
        return modelVersionStatus;
    }

    /**
     * Try and migrate the model, when the migration policy allows it.
     * 
     * @param migrationPolicy a policy to decide whether to migrate the file, based on the model information
     * @return how the model has actually been migrated
     * @throws MigrationException exception during migration
     */
    public MigrationResult tryAndMigrate(MigrationPolicy migrationPolicy) throws MigrationException {
        if (modelVersionStatus.getSeverity() == IStatus.WARNING) {
            // test whether read model is read-only
            URI uri = helpedResource.getURI();
            boolean isReadOnly = Boolean.TRUE.equals(helpedResource.getResourceSet().getURIConverter()
                    .getAttributes(uri, Map.of(URIConverter.ATTRIBUTE_READ_ONLY, true))
                    .get(URIConverter.ATTRIBUTE_READ_ONLY));
            String name = uri.lastSegment();
            // ask migration policy what to do
            MigrationResult desiredResult = migrationPolicy.decideMigration(modelVersionStatus, name, isReadOnly);
            if (desiredResult.doMigrate()) {
                String modelVersion = getModelVersion();
                String nsUri = getNsURI();
                Migrator migrator = getMigrator(nsUri);
                Optional<Release> release = migrator.getReleases().stream()
                        .filter(r -> r.getLabel().equals(modelVersion)).findFirst();
                if (release.filter(r -> !r.isLastRelease()).isPresent()) {
                    migrator.setLevel(ValidationLevel.RELEASE);
                    Map<String, Object> loadOptions = new HashMap<>();
                    loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
                    try {
                        if (desiredResult.doEraseResource()) {
                            // migrate and update the file
                            migrator.migrateAndSave(Collections.singletonList(uri),
                                    release.get(), null, new NullProgressMonitor(), loadOptions);
                            return MigrationResult.HARD_MIGRATION;
                        } else {
                            // migrate virtually only
                            ResourceSet resourceSet = migrator.migrateAndLoad(Collections.singletonList(uri),
                                    release.get(),
                                    null, new NullProgressMonitor());
                            EList<EObject> contents = resourceSet.getResources().get(0).getContents();
                            helpedResource.getContents().clear();
                            helpedResource.getContents().addAll(contents);
                            return MigrationResult.SOFT_MIGRATION;
                        }
                    } catch (RuntimeException e) {
                        throw new MigrationException(String.format("Failed to migrate %s", uri), e);
                    }
                }
            }
        }
        return MigrationResult.NO_MIGRATION;
    }

}
