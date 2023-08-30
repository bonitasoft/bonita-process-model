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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.bpm.model.process.Messages;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.util.ProcessResourceImpl;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.common.IResourceSetFactory;
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
public class MigrationHelper extends AdapterImpl {

    /**
     * This option allows to specify a policy for model file migration, for {@link Resource} implementations supporting it.
     * Corresponding value must be a {@link MigrationPolicy}.
     * 
     * @see MigrationPolicy#ALWAYS_MIGRATE_POLICY
     * @see MigrationPolicy#NEVER_MIGRATE_POLICY
     * @see ProcessResourceImpl#setMigrationPolicy(BiPredicate)
     */
    public static final String OPTION_MIGRATION_POLICY = "MIGRATION_POLICY";//$NON-NLS-1$

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
        /*
         * We could store instances in WeakHashMap<Resource, MigrationHelper> and remove them when resource is unloaded.
         * But it happens that resources are not always unloaded (which is bad).
         * So instead, we'll just store the helper as EAdapter to let it be deleted with the resource.
         */
        return emfResource.eAdapters().stream().filter(MigrationHelper.class::isInstance)
                .map(MigrationHelper.class::cast).findFirst().orElseGet(
                        () -> {
                            var helper = new MigrationHelper(emfResource, streamSupplier);
                            emfResource.eAdapters().add(helper);
                            return helper;
                        })
                .orThrowParseException();
    }

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
        // parse the resource for nsURI and model version
        parseForInformation(streamSupplier);
        // try and compare the model version with the current version
        modelVersionStatus = compareModelVersions(resource.getURI().lastSegment());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(Notification msg) {
        if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED && !msg.getNewBooleanValue()) {
            // resource has been unloaded, forget about it
            Resource resource = (Resource) msg.getNotifier();
            resource.eAdapters().remove(this);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    @Override
    public void setTarget(Notifier newTarget) {
        // check that target was not set and that new target is a Resource
        if (newTarget != null && (target != null || !(newTarget instanceof Resource))) {
            throw new IllegalArgumentException();
        }
        super.setTarget(newTarget);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#getTarget()
     */
    @Override
    public Resource getTarget() {
        return (Resource) super.getTarget();
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
        Optional<IOException> io = storedParseException.filter(IOException.class::isInstance)
                .map(IOException.class::cast);
        Optional<SAXException> sax = storedParseException.filter(SAXException.class::isInstance)
                .map(SAXException.class::cast);
        Optional<ParserConfigurationException> conf = storedParseException
                .filter(ParserConfigurationException.class::isInstance).map(ParserConfigurationException.class::cast);
        if (io.isPresent()) {
            throw io.get();
        } else if (sax.isPresent()) {
            throw sax.get();
        } else if (conf.isPresent()) {
            throw conf.get();
        } else {
            return this;
        }
    }

    /**
     * Parse the resource for information relative to namespace and model version
     * 
     * @param streamSupplier supplies a stream with the resource content for information parsing
     */
    private void parseForInformation(InputStreamSupplier streamSupplier) {
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
            doParseForInformation(newStream, contentHandler, reader);
        } catch (IOException | SAXException | ParserConfigurationException exception) {
            // store the exception, and throw it when trying to get the instance
            storedParseException = Optional.of(exception);
        }
    }

    /**
     * Do parse the resource for information relative to namespace and model version
     * 
     * @param newStream stream with the resource content for information parsing
     * @param contentHandler migation content handler
     * @param reader SAX XML reader
     * @throws IOException reading exception
     */
    private void doParseForInformation(InputStream newStream, final ContentHandler contentHandler, XMLReader reader)
            throws IOException {
        try {
            informationHandler = contentHandler;
            reader.parse(new InputSource(newStream));
        } catch (final SAXException e) {
            // loading should fail immediately after reading relevant information
        }
    }

    /**
     * A resource set which reuse content from target resource to load, and do not load from file.
     * Reading must occur with newly registered metamodels, not to produce AnyType, so we use a piped stream.
     */
    private final class ResourceSetLoadingFromTarget extends ResourceSetImpl {

        /**
         * The save options to use for target
         */
        private final Map<?, ?> saveOptions;

        /**
         * Default Constructor.
         * 
         * @param saveOptions
         */
        private ResourceSetLoadingFromTarget(Map<?, ?> saveOptions) {
            this.saveOptions = saveOptions;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#demandLoad(org.eclipse.emf.ecore.resource.Resource)
         */
        @Override
        protected void demandLoad(Resource resource) throws IOException {
            URI uri = resource.getURI();
            if (uri.equals(getTarget().getURI())) {
                String fileExt = "." + uri.fileExtension();
                String fileName = uri.lastSegment().replace(fileExt, "");
                File temp = File.createTempFile(fileName, fileExt);
                try (var out = new FileOutputStream(temp)) {
                    getTarget().save(out, saveOptions);
                } catch (IOException e) {
                    handleDemandLoadException(resource, e);
                    return;
                }
                try (var in = new FileInputStream(temp)) {
                    resource.load(in, getLoadOptions());
                }
            } else {
                super.demandLoad(resource);
            }
        }
    }

    /** Content handler for extraction of namespace URI and model version using SAX. */
    private static class ContentHandler extends DefaultHandler {

        /**
         * The first and default model version from which we migrate in absence of information
         */
        private static final String VERY_FIRST_VERSION = "6.0.0-Alpha";

        /** Namespace URI. */
        private String nsURI;

        /** All registered namespace URIs by prefix */
        private List<String> allNsURIs = new ArrayList<>();

        /** Model version */
        private String modelVersion;

        /*
         * (non-Javadoc)
         * @see org.xml.sax.helpers.DefaultHandler#startPrefixMapping(java.lang.String, java.lang.String)
         */
        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            // register all used namespace URIs
            allNsURIs.add(uri);
        }

        /** {@inheritDoc} */
        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if (!ExtendedMetaData.XMI_URI.equals(uri) && !ExtendedMetaData.XML_SCHEMA_URI.equals(uri)) {
                nsURI = uri;
                // element should be "process:MainProcess", "process:Configuration" or "process:Connector Configuration" but namespace prefix may enventually change
                if (qName.endsWith(":" + ProcessPackage.Literals.MAIN_PROCESS.getName())) {
                    String modelVersionAtt = attributes
                            .getValue(ProcessPackage.Literals.MAIN_PROCESS__BONITA_MODEL_VERSION.getName());
                    setModelVersion(modelVersionAtt);
                } else if (qName.endsWith(":" + ConfigurationPackage.Literals.CONFIGURATION.getName())) {
                    String modelVersionAtt = attributes
                            .getValue(ConfigurationPackage.Literals.CONFIGURATION__VERSION.getName());
                    setModelVersion(modelVersionAtt);
                } else if (qName
                        .endsWith(":" + ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION.getName())) {
                    String modelVersionAtt = attributes.getValue(
                            ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__MODEL_VERSION.getName());
                    setModelVersion(modelVersionAtt);
                }
                throw new SAXException();
            }
        }

        /**
         * Set the model version from the attribute
         * 
         * @param modelVersionAtt the attribute value set in model element
         */
        private void setModelVersion(String modelVersionAtt) {
            /*
             * If version is not filled, take value before version appeared.
             * There's also this weird case where version is like 6.0.0 but means alpha
             */
            modelVersion = Optional.ofNullable(modelVersionAtt).filter(v -> !VERY_FIRST_VERSION.startsWith(v))
                    .orElse(VERY_FIRST_VERSION);
        }
    }

    /**
     * Get the Edapt migrator, with appropriate fixes
     * 
     * @return the migrator
     */
    public Migrator getMigrator(final String nsURI) {
        var singleMigrator = SingleResourceMigrator.getInstance();
        if (!singleMigrator.getNsURIs().contains(nsURI)) {
            Migrator registeredMigrator = MigratorRegistry.getInstance().getMigrator(nsURI);
            if (registeredMigrator != null) {
                return new LenientResourceMigrator(registeredMigrator);
            } else {
                return null;
            }
        } else {
            return singleMigrator;
        }
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
     * Returns all the declared namespace URIs.
     * 
     * @return namespace URIs
     */
    public List<String> getAllNamespaceUris() {
        return Optional.ofNullable(informationHandler).map(c -> c.allNsURIs).orElseGet(Collections::emptyList);
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
     * @param defaultLoadOptions the default options for loading the resource
     * @param defaultSaveOptions the default options for saving the resource
     * @return how the model has actually been migrated
     * @throws MigrationException exception during migration
     */
    public MigrationResult tryAndMigrate(MigrationPolicy migrationPolicy, Map<?, ?> defaultLoadOptions,
            Map<?, ?> defaultSaveOptions)
            throws MigrationException {
        if (modelVersionStatus.getSeverity() == IStatus.WARNING) {
            // test whether read model is read-only
            URI uri = getTarget().getURI();
            boolean isReadOnly = Boolean.TRUE.equals(getTarget().getResourceSet().getURIConverter()
                    .getAttributes(uri, Map.of(URIConverter.ATTRIBUTE_READ_ONLY, true))
                    .get(URIConverter.ATTRIBUTE_READ_ONLY));
            String name = uri.lastSegment();
            // ask migration policy what to do
            MigrationResult desiredResult = migrationPolicy.decideMigration(modelVersionStatus, name, isReadOnly);
            if (desiredResult.doMigrate()) {
                String modelVersion = getModelVersion();
                String nsUri = getNsURI();
                boolean hasActuallyMigrated = false;
                // main migration
                Migrator actualMigrator = getMigrator(nsUri);
                // other namespaces may also contain a migration
                Map<Release, Migrator> extraMigrators = collectExtraMigrators(actualMigrator);
                Map<Object, Object> loadOptions = new HashMap<>(defaultLoadOptions);
                Map<String, Object> saveOptions = new HashMap<>();
                saveOptions.forEach((k, v) -> saveOptions.put(k.toString(), v));
                if (!extraMigrators.isEmpty()) {
                    // the main migration will encounter some old (hence unknown) metamodels, we must be enable partial load
                    loadOptions.putAll(Map.of(
                            XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE,
                            XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE));
                    saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
                }
                // perform main migration
                Optional<Release> release = actualMigrator.getReleases().stream()
                        .filter(r -> r.getLabel().equals(modelVersion)).findFirst();
                if (release.filter(r -> !r.isLastRelease()).isPresent()) {
                    hasActuallyMigrated = performMigration(loadOptions, saveOptions, uri, desiredResult,
                            actualMigrator, release.get(), false);
                }
                // then the extra namespace migrations
                for (var entry : extraMigrators.entrySet()) {
                    var currentRelease = entry.getKey();
                    var extraMigrator = entry.getValue();
                    hasActuallyMigrated = performMigration(loadOptions, saveOptions, uri,
                            desiredResult,
                            extraMigrator, currentRelease,
                            hasActuallyMigrated && !desiredResult.doEraseResource());
                }
                if (hasActuallyMigrated) {
                    return desiredResult;
                }
            }
        }
        return MigrationResult.NO_MIGRATION;
    }

    /**
     * Collect map with migrators from extra namespaces and the corresponding current release.
     * 
     * @param mainMigrator the migrator used for main migration
     * @return map
     */
    private Map<Release, Migrator> collectExtraMigrators(Migrator mainMigrator) {
        String nsUri = getNsURI();
        List<String> allNamespaceUris = getAllNamespaceUris();
        Map<Release, Migrator> extraMigrators = new HashMap<>();
        for (String extraNsUri : allNamespaceUris) {
            if (!nsUri.equals(extraNsUri)) {
                Migrator extraMigrator = getMigrator(extraNsUri);
                if (extraMigrator != null && !mainMigrator.equals(extraMigrator)) {
                    // perform extra migration (the version is in the nsUri)
                    Optional<Release> currentRelease = extraMigrator.getReleases().stream()
                            .filter(r -> extraNsUri.endsWith("/" + r.getLabel())).findFirst();
                    if (currentRelease.filter(r -> !r.isLastRelease()).isPresent()) {
                        extraMigrators.put(currentRelease.get(), extraMigrator);
                    }
                }
            }
        }
        return extraMigrators;
    }

    /**
     * Perform a migration with the migrator
     * 
     * @param loadOptions the options for loading the resource
     * @param saveOptions the options for saving the resource
     * @param uri resource uri
     * @param desiredResult the intended migration result
     * @param migrator the migrator to use
     * @param release the source release to migrate from
     * @param reuseResource true when resource was already partially virtually migrated and we want to reuse it without reloading from file
     * @return true when migration was successful
     * @throws MigrationException exception during migration
     */
    private boolean performMigration(Map<?, ?> loadOptions, Map<String, Object> saveOptions, URI uri,
            MigrationResult desiredResult, Migrator migrator, Release release, boolean reuseResource)
            throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        // handle load and save options
        IResourceSetFactory rsetFactory = () -> {
            ResourceSetImpl set;
            if (reuseResource) {
                /*
                 * Also, we want to reuse content from target resource and not load from file.
                 */
                set = new ResourceSetLoadingFromTarget(saveOptions);
            } else {
                set = new ResourceSetImpl();
            }
            set.getLoadOptions().putAll(loadOptions);
            set.getLoadOptions().remove(OPTION_MIGRATION_POLICY);
            return set;
        };
        migrator.setResourceSetFactory(rsetFactory);
        try {
            if (desiredResult.doEraseResource()) {
                // migrate and update the file
                migrator.migrateAndSave(Collections.singletonList(uri), release, null, new NullProgressMonitor(),
                        saveOptions);
            } else {
                // migrate virtually only
                ResourceSet resourceSet = migrator.migrateAndLoad(Collections.singletonList(uri), release, null,
                        new NullProgressMonitor());
                resourceSet.getLoadOptions().putAll(loadOptions);
                resourceSet.getLoadOptions().remove(OPTION_MIGRATION_POLICY);
                EList<EObject> contents = resourceSet.getResources().get(0).getContents();
                getTarget().getContents().clear();
                getTarget().getContents().addAll(contents);
            }
            return true;
        } catch (RuntimeException e) {
            throw new MigrationException(String.format("Failed to migrate %s", uri), e);
        }
    }

}
