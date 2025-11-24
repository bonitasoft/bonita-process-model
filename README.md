# bonita-process-model

Contains the Bonita Process Domain Logic used by Studio and related tools.

## Prerequisites

For building the code in this repository: **Java JDK 17+**

For using the libraries released: **Java JDK 11+**

## How to build

### With Eclipse

Different launch configurations exist in this repository. `Build Bonita Process Model.launch` can be used to compile and
build the model java classes.

In your Eclipse IDE, right-click on the launch file, `Run as -> Build Bonita Process Model`

A console should appear and after a while, the build will be done. You can find the jar in the target folder of each
bundle project.

### With Maven Wrapper

```
./mvnw install
```

This will install all the Bonita Process Domain Logic artifacts and also a maven archetype `process-reader-archetype`.

## How to use the Bonita Process Domain model in your own work

To make it easier for you, this project contains a maven archetype, which allows you to set up a project for reading a
Bonita Business Process Model file.

### The maven archetype : process-reader-archetype

This sets up a classic maven project that uses:

* [org.bonitasoft.bpm.model](https://github.com/bonitasoft/bonita-process-model) for knowing the Bonita Process Domain
  Logic
* [org.eclipse.emf.ecore](https://eclipse.dev/emf/) to manipulate and load the model
* A sample `.proc` Business Process Model file

#### How to use the archetype

```
mvn archetype:generate \
    -DarchetypeGroupId=org.bonitasoft.archetypes \
    -DarchetypeArtifactId=process-reader-archetype \
    -DgroupId=org.company.bpm \
    -DartifactId=my-process-reader \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackage=org.company.bpm.reader \
    -DdisplayName="My Custom Process Reader" 
```

#### Archetype parameters

| Parameter                      | Required  | Default value                     | Description                                                                                   |
|--------------------------------|-----------|-----------------------------------|-----------------------------------------------------------------------------------------------|
| -DarchetypeGroupId             | __true__  | __org.bonitasoft.archetypes__     | Group id of the archetype                                                                     |
| -DarchetypeArtifactId          | __true__  | __process-reader-archetype__      | Artifact id of the archetype (must be fixed to the given value)                               |
| -DarchetypeVersion             | __false__ | Latest release version (8.0.0)    | Version id of the archetype                                                                   |
| -DgroupId                      | __true__  |                                   | Group id of the project                                                                       |
| -DartifactId                   | __true__  |                                   | Artifact id of the project                                                                    |
| -Dversion                      | __true__  | suggested and asked interactively | Version of the project, suggested to use 0.0.1-SNAPSHOT                                       |
| -Dpackage                      | __false__ | suggested and asked interactively | Name of the root java package                                                                 |
| -DdisplayName                  | __false__ | suggested and asked interactively | Name of the project displayed                                                                 |
| -Ddescription                  | __false__ | Describe your project here        | Description of the project                                                                    |
| -Dwrapper                      | __false__ | true                              | If set to true, project will setup a [maven wrapper](https://github.com/takari/maven-wrapper) |
| -Djava-version                 | __false__ | Same as in the main module __\*__ | Version of Java used by the project.                                                          |
| -Dbonita-process-model-version | __false__ | Same as in the main module __\*__ | Version of the bonita-process-model used.                                                     |
| -Dmaven-clean-version          | __false__ | Same as in the main module __\*__ | Version of maven-clean-plugin module used by the project.                                     |
| -Dmaven-resources-version      | __false__ | Same as in the main module __\*__ | Version of maven-resources-plugin module used by the project.                                 |
| -Dmaven-compiler-version       | __false__ | Same as in the main module __\*__ | Version of maven-compiler-plugin module used by the project.                                  |
| -Dmaven-surefire-version       | __false__ | Same as in the main module __\*__ | Version of maven-surefire-plugin module used by the project.                                  |
| -Dmaven-jar-version            | __false__ | Same as in the main module __\*__ | Version of maven-jar-plugin module used by the project.                                       |
| -Dmaven-install-version        | __false__ | Same as in the main module __\*__ | Version of maven-install-plugin module used by the project.                                   |
| -Dmaven-deploy-version         | __false__ | Same as in the main module __\*__ | Version of maven-deploy-plugin module used by the project.                                    |

__\* *These parameters are used to fix some versions. It is recommended not to use them and let the default values be
used to ensure compatibility.*__

The versions of dependent modules are taken recursively by org.bonitasoft.bpm modules, so you can not choose them. This
concerns EMF Core, EMF Common, EMF Ecore XMI, EMF Edit and maven-artifact modules.

## Tutorial : displaying the content of a simple process

This tutorial will help you to read the content of a process file from the Bonita Studio and to display the content (
tasks, events, transitions... ).
The code itself is already present in your newly project created.

### The business process model file

In the resources folder of your newly created project, you should have a MyDiagram-1.0.proc file.
This file is a Business Process Model File coming from Bonita Studio. It's a very simple process containing 4 tasks and
a simple flow.

### Launching the application with App.java

The App.java contain a main method which can be used to launch this application like a java application. It does the
following things:

* **Loading the process file:** It will load the process file by using the ModelLoader class present in
  org.bonitasoft.bpm.model. This loading is very important : it will allow you to manipulate the content of the process
  itself through emf.
* **Displaying the content loaded in html:** The ModelDisplayer present in the project created can show you how to
  display in an html page the content of the process file. It can be used as a way to better understand how emf and the
  model logic work.

## Other tools related to Bonita Process Domain models

### Import from and export to BPMN 2.0

//TODO

## P2 update site for integration with Eclipse RCP

You can also use the maven update site as a P2 repository / update site to use the provided features in
your [Eclipse RCP](https://www.vogella.com/tutorials/EclipseRCP/article.html) application.
For the repository URL, you have to mention [mvn:org.bonitasoft.bpm:org.bonitasoft.bpm.eclipse-repository:<version>:zip]
in your target platform (replace <version> with the intended one).
P2 will then ask Maven to search for the **eclipse-repository** maven artifact and will extract the P2 repository from
it (provided your Maven configuration has access to Maven Central).

## EMF Resources Knowledge

- EMF official eclipse documentation: https://eclipse.dev/emf/docs.html
- A tutorial describing the usage of Eclipse EMF:  https://www.vogella.com/tutorials/EclipseEMF/article.html

## How to release

This project uses the [gitflow-maven-plugin](https://github.com/aleksandr-m/gitflow-maven-plugin) for release
management. Releases are created using the GitHub Actions workflow.

### Branch Strategy

- **develop**: Main development branch for future releases
- **support/A.B.x**: Maintenance branches for older versions (e.g., support/8.0.x, support/8.1.x, support/9.0.x)
- **master**: Not used (removed, use support branches for maintenance)

### Creating a Release

Releases are created via the GitHub Actions
workflow [Release](https://github.com/bonitasoft/bonita-process-model/actions/workflows/release.yml)

#### Workflow Parameters

| Parameter                   | Description                                                                  | Default     | Example                         |
|-----------------------------|------------------------------------------------------------------------------|-------------|---------------------------------|
| **version**                 | Version to release (leave empty to use current pom.xml version)              | empty       | `9.0.4`                         |
| **nextDevelopmentVersion**  | Next development version (leave empty to use versionDigitToIncrement policy) | empty       | `9.0.5-SNAPSHOT`                |
| **versionDigitToIncrement** | Version digit to increment in next development version                       | `2` (patch) | `0`=major, `1`=minor, `2`=patch |

#### Release Types

**Patch Release (X.Y.Z)** - Bug fixes and minor updates

- Set `versionDigitToIncrement`: **2** (default)
- Example: `9.0.3 → 9.0.4-SNAPSHOT`
- Use for: Support branches, hotfixes

**Minor Release (X.Y.0)** - New features, backward compatible

- Set `versionDigitToIncrement`: **1**
- Example: `9.0.3 → 9.1.0-SNAPSHOT`
- Use for: Regular releases from develop

**Major Release (X.0.0)** - Breaking changes

- Set `versionDigitToIncrement`: **0**
- Example: `9.0.3 → 10.0.0-SNAPSHOT`
- Use for: Major version bumps

### Release Process

When you run the workflow from any branch (develop or support/*), it will:

1. Update version to release version (removes -SNAPSHOT)
2. Commit: "chore(release): Update versions for release"
3. Create git tag (e.g., `9.0.4`)
4. Update version to next development version
5. Commit: "chore(release): Update for next development version"
6. Push commits and tags to GitHub

**Note**: The workflow does NOT create a release branch - it performs the release directly on the branch you selected.

### Cascade Merging for Support Branches

When releasing from a **support branch**, you should manually cascade merge the changes up to newer branches and
develop.

**Example**: After doing a release from `support/8.0.x`, merge `support/8.0.x` into `support/8.1.x`, then merge
`support/8.1.x` into `support/9.0.x`, and so on into `develop`.
