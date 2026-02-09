# Bonita Process Model - Architecture Guide for Claude

## Project Overview

This repository contains the **Bonita Process Domain Logic** used by Bonita Studio and related tools. It provides EMF-based models for defining business processes compatible with BPMN 2.0, along with transformation and export capabilities.

**License:** GNU General Public License v2.0 or later
**Current Version:** 9.0.4-SNAPSHOT
**Java Version:** 17+
**Primary Technologies:** Eclipse Modeling Framework (EMF), Eclipse Tycho, Maven

---

## Build & Development Commands

### Prerequisites
- Java JDK 17+
- Maven (wrapper included: `./mvnw`)
- For Eclipse development: Eclipse IDE with Tycho support

### Build Commands

```bash
# Full build with install
./mvnw install

# Clean build with code formatting
./mvnw clean spotless:apply install

# Build without transfer progress (cleaner output)
./mvnw clean install --no-transfer-progress

# Build with code coverage (Jacoco)
./mvnw clean install -Pjacoco

# Build and run SonarQube analysis
./mvnw deploy sonar:sonar -Pjacoco

# Generate SBOM (Software Bill of Materials)
./mvnw package  # SBOM generated automatically in target/bom.json

# Validate code formatting
./mvnw spotless:check

# Apply code formatting
./mvnw spotless:apply

# Build in offline mode (skip formatting checks)
./mvnw install -Poffline
```

### Eclipse Launch Configurations

Two launch configurations are provided:
1. **Build Bonita Process Model.launch** - Compiles and builds model Java classes
2. **Generate Bonita Process Model SBOM.launch** - Generates Software Bill of Materials

Usage: Right-click launch file → Run As → [Launch Configuration Name]

### Testing

```bash
# Run all tests
./mvnw test

# Run tests with Groovy support (required for some tests)
# On Ubuntu:
sudo apt-get update && sudo apt-get -y install groovy
./mvnw test

# Run specific test module
./mvnw test -pl tests/org.bonitasoft.bpm.model.tests

# Run standalone integration tests
./mvnw test -pl tests/standalone-tests
```

### Maven Archetype Usage

Generate a project to read Bonita process models:

```bash
mvn archetype:generate \
    -DarchetypeGroupId=org.bonitasoft.archetypes \
    -DarchetypeArtifactId=process-reader-archetype \
    -DgroupId=org.company.bpm \
    -DartifactId=my-process-reader \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackage=org.company.bpm.reader \
    -DdisplayName="My Custom Process Reader"
```

### Release Process

Releases are managed via GitHub Actions using the gitflow-maven-plugin:
- **Workflow:** `.github/workflows/release.yml`
- **Branch Strategy:**
  - `develop` - Main development branch
  - `support/A.B.x` - Maintenance branches (e.g., support/8.0.x, support/9.0.x)
- **Release Types:**
  - Patch (X.Y.Z): `versionDigitToIncrement=2`
  - Minor (X.Y.0): `versionDigitToIncrement=1`
  - Major (X.0.0): `versionDigitToIncrement=0`

---

## High-Level Architecture

### Architectural Style
**Eclipse RCP Plugin Architecture with EMF-Based Domain Models**

The project follows a modular, plugin-based architecture using Eclipse Tycho for OSGi bundle management. The core is built around Eclipse Modeling Framework (EMF) for domain model definition and code generation.

### Key Architectural Layers

```
┌─────────────────────────────────────────────────────────────┐
│                    Eclipse RCP Layer                        │
│         (P2 Update Site / Eclipse Plugin Integration)       │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
┌─────────────────────────────────────────────────────────────┐
│                   Transformation Layer                      │
│  ┌──────────────────┐  ┌──────────────────┐                │
│  │  bonita2bar      │  │  bonita2bpmn     │                │
│  │  (BAR Export)    │  │  (BPMN Export)   │                │
│  └──────────────────┘  └──────────────────┘                │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
┌─────────────────────────────────────────────────────────────┐
│                   Business Logic Layer                      │
│  ┌──────────────────┐  ┌──────────────────┐                │
│  │  bpm.migration   │  │  bpm.model.edit  │                │
│  │  (Migrations)    │  │  (Edit Support)  │                │
│  └──────────────────┘  └──────────────────┘                │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
┌─────────────────────────────────────────────────────────────┐
│                   Domain Model Layer (EMF)                  │
│  ┌──────────────────┐  ┌──────────────────┐                │
│  │  bpm.model       │  │ connector.model  │                │
│  │  (Process Model) │  │ (Connectors)     │                │
│  └──────────────────┘  └──────────────────┘                │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
┌─────────────────────────────────────────────────────────────┐
│              Foundation Layer (Dependencies)                │
│  ┌──────────────────┐  ┌──────────────────┐                │
│  │ plugin-deps      │  │ gmf-plugin-deps  │                │
│  │ (EMF, Maven)     │  │ (GMF Notation)   │                │
│  └──────────────────┘  └──────────────────┘                │
└─────────────────────────────────────────────────────────────┘
```

---

## Key Components

### 1. **org.bonitasoft.bpm.model** (Core Domain Model)
**Purpose:** Defines the Bonita business process domain model using EMF

**Key Responsibilities:**
- EMF model definitions (.ecore files) for processes, expressions, forms, configurations, etc.
- Generated Java classes from EMF models (impl, util packages)
- Model loading and resource management (ModelLoader)
- Model migration support (process.history - 1MB migration history)

**EMF Models (11 .ecore files):**
- `process.ecore` - Main process definition (pools, lanes, tasks, events, gateways)
- `configuration.ecore` - Process configurations and environments
- `expression.ecore` - Expression types and evaluation
- `form.ecore` - Form definitions
- `connector-configuration.ecore` - Connector configurations
- `ActorMapping.ecore` - Actor-to-user mappings
- `parameter.ecore` - Process parameters
- `kpi.ecore` - Key Performance Indicators
- `simulation.ecore` - Process simulation data
- `decision.ecore` - Decision tables
- `transitions.ecore` - Transition logic

**Key Patterns:**
- EMF Factory pattern for object creation
- Resource loading with automatic migration support
- OSGi vs non-OSGi environment detection

### 2. **org.bonitasoft.bonita2bar** (Business Archive Export)
**Purpose:** Transforms Bonita process models into Business Archive (.bar) files for deployment

**Key Responsibilities:**
- Build BAR files from process models
- Generate Maven POMs for process dependencies
- Bundle connectors, dependencies, and resources
- Handle multiple configuration environments

**Architecture Pattern: Provider-Based Extensibility**
```java
interface BarArtifactProvider {
    void build(BusinessArchiveBuilder, Pool, ProcessPom, Configuration)
    void configure(EnvironmentConfigurationBuilder, Configuration, Pool)
}
```

**Key Classes:**
- `BarBuilder` - Main builder orchestrator
- `BarBuilderFactory` - Factory for builder instances
- `ProcessPomGenerator` - Generates pom.xml for process dependencies
- `BarArtifactProvider` - Extension point for artifact providers

**Artifact Providers:**
- `DependenciesArtifactProvider` - JAR dependencies
- `ConnectorImplementationArtifactProvider` - Connector implementations
- `FormMappingArtifactProvider` - Form mappings
- `ParameterArtifactProvider` - Process parameters

### 3. **org.bonitasoft.bonita2bpmn** (BPMN 2.0 Export)
**Purpose:** Exports Bonita process models to standard BPMN 2.0 XML format

**Key Responsibilities:**
- Transform Bonita model to BPMN 2.0 specification
- Preserve BPMN compatibility for interoperability
- Handle Bonita-specific extensions

**Key Classes:**
- `BonitaToBPMNExporter` - Main transformation engine
- `BonitaModelExporterImpl` - Model exporter implementation

**Dependencies:**
- `org.bonitasoft.spec.bpmn` - BPMN 2.0 specification models
- GMF Notation support for diagram information

### 4. **org.bonitasoft.bpm.migration** (Model Migration)
**Purpose:** Handles backward compatibility and model version migrations

**Key Responsibilities:**
- Migrate old process files to current version
- Custom migration rules for breaking changes
- Connector definition updates
- Expression migration

**Migration Types:**
- EMF Edapt-based automatic migrations (process.history)
- Custom Java migrations (66 migration classes)
- Connector version updates (20+ connector migrations)
- Expression type migrations
- Contract migrations

**Notable Migrations:**
- `StartTimerConditionCustomMigration`
- `MultiInstanceCustomMigration`
- `FormMappingCustomMigration`
- `DocumentTypeMigration`
- Various connector version updates (Email, REST, Groovy, Salesforce, etc.)

### 5. **org.bonitasoft.bpm.connector.model** (Connector Definitions)
**Purpose:** Defines connector and filter models

**EMF Models:**
- `definition.ecore` - Connector definitions
- `implementation.ecore` - Connector implementations

### 6. **org.bonitasoft.bpm.model.edit** (Edit Support)
**Purpose:** Provides EMF Edit framework support for UI manipulation

**Key Responsibilities:**
- Item providers for property sheets
- Label providers
- Command support for undo/redo

### 7. **Plugin Dependencies Bundles**
**org.bonitasoft.bpm.plugin-dependencies:**
- Bundles EMF dependencies for Maven environments
- Includes: EMF Edapt, Maven Artifact APIs

**org.bonitasoft.gmf.plugin-dependencies:**
- Bundles GMF (Graphical Modeling Framework) dependencies
- Includes: GMF Notation, Draw2D, GEF

### 8. **process-reader-archetype** (Maven Archetype)
**Purpose:** Maven archetype to generate projects for reading Bonita process models

**Generated Project Includes:**
- Sample .proc file
- ModelLoader usage examples
- HTML display utilities

---

## Component Interaction Patterns

### 1. **Model Loading Flow**
```
User Code
  → ModelLoader.loadModel(URI)
    → ResourceSetImpl with custom handlers
      → Check EMF model version
        → If old version: Migrate via process.history
        → Load into EMF objects (Pool, Configuration, etc.)
  → Return loaded model
```

### 2. **BAR Building Flow**
```
BarBuilderFactory.create()
  → BarBuilder with providers
    → buildAll(environment)
      → For each process:
        → Load Configuration for environment
        → ProcessPomGenerator.generatePom()
          → Filter dependencies based on configuration
        → BusinessArchiveBuilder
          → Call all BarArtifactProvider.build()
            → Add connectors, dependencies, forms, etc.
        → Write .bar file
```

### 3. **BPMN Export Flow**
```
BonitaToBPMNExporter.export()
  → Load Bonita model (EMF)
  → Transform to BPMN 2.0 spec model
    → Map Bonita elements to BPMN elements
    → Preserve diagram notation (GMF)
  → Serialize to BPMN XML
```

### 4. **Migration Flow**
```
ModelLoader detects old version
  → HistoryUtils.getMigrator()
    → Load process.history (EMF Edapt)
    → Apply automatic migrations
  → CustomMigrationUtil.applyCustomMigrations()
    → Apply 60+ custom migrations in sequence
  → Update model version
  → Save migrated model
```

---

## Important Patterns & Conventions

### EMF Model-Driven Development
- **Code Generation:** Java classes auto-generated from .ecore models
- **Package Structure:**
  - `*.impl` - Implementation classes (generated)
  - `*.util` - Utilities (generated + custom)
  - Root packages - Interfaces and factories (generated)
- **Model Files:**
  - `.ecore` - Model definition
  - `.genmodel` - Generation configuration
  - `.history` - Migration history (Edapt)

### Eclipse OSGi Bundle Structure
Each bundle follows Eclipse plugin conventions:
- `META-INF/MANIFEST.MF` - OSGi bundle metadata
- `plugin.xml` / `plugin.properties` - Plugin configuration
- `pom.xml` - Maven Tycho build configuration
- Packaging: `eclipse-plugin` or `eclipse-feature`

### Tycho Multi-Module Maven Build
```
bonita-process-model/
├── pom.xml                    # Parent aggregator POM
├── releng/target-platform/    # Eclipse target platform definition
├── bundles/                   # OSGi bundles (plugins)
├── features/                  # Eclipse features (bundle groups)
├── tests/                     # Test fragments
├── sites/                     # P2 update site (Eclipse repository)
└── releng/coverage-report/    # Aggregated Jacoco reports
```

### Provider Pattern for Extensibility
The BAR builder uses a provider pattern for extensibility:
```java
interface BarArtifactProvider {
    void build(BusinessArchiveBuilder, Pool, ProcessPom, Configuration)
}
```
This allows adding new artifact types without modifying the core builder.

### Factory Pattern for Complex Object Creation
- `BarBuilderFactory` - Creates configured BarBuilder instances
- EMF Factories (e.g., `ProcessFactory`) - Create model objects

### Resource Pattern for File Handling
EMF Resources abstract file I/O:
```java
Resource resource = resourceSet.getResource(uri, true);
Pool process = (Pool) resource.getContents().get(0);
```

### Migration History with EMF Edapt
- Automatic structural migrations tracked in `.history` files
- Custom migrations for complex transformations
- Ensures backward compatibility across versions

---

## Code Style & Formatting

### Spotless Configuration
- **Formatter:** `formatter.xml` (Eclipse Java formatter - 291 lines)
- **Import Order:** `eclipse.importorder` (java, javax, org, com)
- **License Header:** `header.txt` (GPL v2.0)
- **Enforcement:** Runs on `mvn validate` phase

### Import Order
1. java.*
2. javax.*
3. org.*
4. com.*

### License Header Template
```java
/**
 * Copyright (C) $YEAR Bonitasoft S.A.
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
```

### Code Formatting Commands
```bash
# Check formatting
./mvnw spotless:check

# Apply formatting
./mvnw spotless:apply

# Format specific files
./mvnw spotless:apply -Dspotless.apply.skip=false
```

---

## Commit Message Format

### Convention
```
type(scope): subject
```

### Types
- **feat**: Nouvelle fonctionnalité
- **fix**: Correction de bug
- **chore**: Tâches de maintenance (deps, CI, etc.)
- **refactor**: Refactoring (pas de changement fonctionnel)
- **test**: Ajout ou modification de tests
- **docs**: Documentation
- **perf**: Amélioration de performance
- **style**: Formatage, style (pas de changement de logique)

### Rules
- ✅ Impératif: "fix bug" pas "fixed bug"
- ✅ Concis: < 72 caractères
- ✅ Minuscules pour type et scope
- ❌ PAS d'emojis
- ❌ PAS de mention "Claude", "AI", "Generated"
- ❌ PAS de "Co-Authored-By: Claude"

### Examples
```bash
feat(config): restore dependencies configuration page
fix(tests): resolve timeout in ProfileSWTBotIT
chore(deps): update Tycho to 4.0.10
refactor(uib): simplify Docker health check logic
```

---

## Testing Architecture

### Test Module Structure
```
tests/
├── org.bonitasoft.bpm.model.tests/          # Core model tests
├── org.bonitasoft.bpm.model.tests-utils/    # Test utilities
├── org.bonitasoft.bpm.model.edit.tests/     # Edit support tests
├── org.bonitasoft.bonita2bar.tests/         # BAR export tests
├── org.bonitasoft.bonita2bpmn.tests/        # BPMN export tests
├── org.bonitasoft.bpm.migration.tests/      # Migration tests
└── standalone-tests/                        # Integration tests (non-OSGi)
```

### Test Patterns
- **Eclipse Fragment Projects:** Tests are fragments of the bundles they test
- **OSGi Tests:** Run with `tycho-surefire-plugin` in Eclipse runtime
- **Standalone Tests:** Pure Maven tests without Eclipse runtime
- **Test Resources:** `.proc` files and sample projects in `resources/` or `src/test/resources/`

### Test Execution
```bash
# All tests
./mvnw test

# With coverage
./mvnw test -Pjacoco

# Specific test module
./mvnw test -pl tests/org.bonitasoft.bonita2bar.tests
```

---

## P2 Update Site & Eclipse Integration

### P2 Repository
The project generates a P2 update site for Eclipse RCP integration:
- **Location:** `sites/org.bonitasoft.bpm/`
- **Maven Coordinates:** `mvn:org.bonitasoft.bpm:org.bonitasoft.bpm.eclipse-repository:<version>:zip`

### Features (Bundle Groups)
Defined in `sites/org.bonitasoft.bpm/category.xml`:
- **Bonita Business Process Model** - Core process models and transformations
- **Developer Resources** - Test resources
- **Third party dependencies** - Jackson, Bonita artifacts model

### Eclipse Target Platform
- **Definition:** `releng/target-platform/target-platform.target`
- **Source:** `releng/target-platform/target-platform.tpd` (TPD editor format)
- **Validation:** `./mvnw -f releng/target-platform/pom.xml validate -Pvalidate`

---

## CI/CD Pipelines

### GitHub Actions Workflows

**`.github/workflows/build.yml`** - Continuous Integration
- Triggers: Push to develop, release/*, support/*
- Steps: Setup Java 17, Setup Groovy, Maven build, SonarQube analysis
- Command: `./mvnw -B -ntp deploy sonar:sonar -Pjacoco`

**`.github/workflows/release.yml`** - Release Management
- Trigger: Manual (workflow_dispatch)
- Uses: gitflow-maven-plugin
- Command: `./mvnw gitflow:release`

**`.github/workflows/build-pr.yml`** - Pull Request validation
**`.github/workflows/publish.yml`** - Publish to Maven Central
**`.github/workflows/codeql.yml`** - Security analysis

### SonarQube Configuration
- **Host:** https://sonarcloud.io
- **Organization:** bonitasoft
- **Project Key:** bonitasoft_bonita-process-model
- **Coverage:** Aggregated Jacoco reports from `releng/coverage-report/`
- **Exclusions:** `src-gen/**/*` (generated code)

---

## Development Workflow Tips

### Making Changes to EMF Models
1. Edit `.ecore` file (use Eclipse EMF editor)
2. Regenerate code from `.genmodel`
3. Update `.history` file if breaking changes
4. Add custom migration if needed
5. Run tests to ensure backward compatibility

### Adding a New BarArtifactProvider
1. Implement `BarArtifactProvider` interface
2. Implement `build()` method to add artifacts to BAR
3. Optionally implement `configure()` for environment configs
4. Register provider in `BarBuilderFactory` or via OSGi service

### Adding a Migration
1. Create class implementing EMF Edapt migration interface or custom migration interface
2. Add to `process.history` (automatic) or custom migration list
3. Test with old process files
4. Document in migration tests

### Working with Process Files
- **Format:** `.proc` files (EMF XMI serialization)
- **Loading:** Use `ModelLoader.loadModel(URI)`
- **Saving:** Use EMF Resource API
- **Migration:** Automatic on load if version mismatch

---

## Dependencies & Technology Stack

### Core Technologies
- **EMF (Eclipse Modeling Framework):** 2.37.0
- **Tycho (Maven/OSGi build):** 4.0.12
- **Java:** 17
- **Maven:** 3.9.3

### Key Dependencies
- `org.eclipse.emf.ecore` - EMF core
- `org.eclipse.emf.edapt` - Model migration
- `org.eclipse.gmf.runtime.notation` - Diagram notation
- `bonita-artifacts-model` - Bonita engine models (1.2.0)
- `jackson` - JSON/YAML processing (2.15.2)
- `groovy` - Scripting support (3.0.19)
- `maven-artifact` - Maven POM manipulation (3.9.3)

### Development Dependencies
- `jacoco-maven-plugin` - Code coverage
- `spotless-maven-plugin` - Code formatting
- `cyclonedx-maven-plugin` - SBOM generation
- `gitflow-maven-plugin` - Release management

---

## Important Files & Locations

### Configuration
- `pom.xml` - Root Maven configuration
- `formatter.xml` - Eclipse Java code formatter
- `eclipse.importorder` - Import organization rules
- `header.txt` - License header template

### Model Definitions
- `bundles/org.bonitasoft.bpm.model/model/*.ecore` - Domain models
- `bundles/org.bonitasoft.bpm.model/model/process.genmodel` - Generation config
- `bundles/org.bonitasoft.bpm.model/model/process.history` - Migration history (1MB)

### Build & CI
- `.github/workflows/` - GitHub Actions pipelines
- `releng/target-platform/` - Eclipse target platform
- `releng/coverage-report/` - Jacoco aggregation

### Distribution
- `sites/org.bonitasoft.bpm/` - P2 update site
- `bundles/process-reader-archetype/` - Maven archetype

---

## Troubleshooting & Common Issues

### Build Issues
**Problem:** Tycho build fails with "Cannot resolve project dependencies"
**Solution:** Ensure target platform is up to date: `./mvnw clean install -U`

**Problem:** Spotless formatting failures
**Solution:** Apply formatting: `./mvnw spotless:apply` or skip with `-Poffline`

**Problem:** Tests fail with Groovy compilation errors
**Solution:** Install Groovy: `sudo apt-get install groovy` (Linux) or ensure GROOVY_HOME is set

### Model Loading Issues
**Problem:** "Cannot load process model - version mismatch"
**Solution:** Ensure migration bundles are on classpath and process.history is accessible

**Problem:** "Resource cannot be loaded"
**Solution:** Check ModelLoader prerequisites are initialized and EMF packages are registered

### Development Environment
**Problem:** Eclipse cannot find dependencies
**Solution:** Set target platform to `target-platform.target` in Eclipse preferences

---

## External Resources

### Documentation
- **EMF Official Docs:** https://eclipse.dev/emf/docs.html
- **Vogella EMF Tutorial:** https://www.vogella.com/tutorials/EclipseEMF/article.html
- **Eclipse RCP Guide:** https://www.vogella.com/tutorials/EclipseRCP/article.html
- **Target Platform Guide:** https://www.vogella.com/tutorials/EclipseTargetPlatform/article.html

### Project Links
- **GitHub:** https://github.com/bonitasoft/bonita-process-model
- **SonarCloud:** https://sonarcloud.io/project/overview?id=bonitasoft_bonita-process-model
- **Organization:** https://www.bonitasoft.com/

### Maven Central
- **Group ID:** org.bonitasoft.bpm
- **Artifacts:** org.bonitasoft.bpm.model, org.bonitasoft.bonita2bar, etc.

---

## Quick Reference Commands

```bash
# Most common development commands
./mvnw clean install                    # Full build
./mvnw spotless:apply                   # Format code
./mvnw test                             # Run tests
./mvnw -B -ntp deploy -Pjacoco         # CI build with coverage

# Code quality
./mvnw spotless:check                   # Check formatting
./mvnw sonar:sonar -Pjacoco            # SonarQube analysis

# Archetype usage
mvn archetype:generate \
    -DarchetypeGroupId=org.bonitasoft.archetypes \
    -DarchetypeArtifactId=process-reader-archetype

# Release (via GitHub Actions)
# Trigger "Release" workflow with version parameters
```

---

*This document was generated to help Claude understand the Bonita Process Model codebase architecture and development practices.*
