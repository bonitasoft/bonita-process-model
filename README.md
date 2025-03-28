# bonita-process-model
Contains the Bonita Process Domain Logic used by Studio and related tools.

## License
Copyright (C) 2022 BonitaSoft S.A.
BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2.0 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see &lt;http://www.gnu.org/licenses/&gt;.

## Prerequisites
For building the code in this repository : Java [JDK 17+]

For using the libraries released : Java [JDK 17+]

## How to build

### With Eclipse
Different launch configurations exists in this repository. Build Bonita Process Model.launch can be used to compile and build the model java classes. 

In your Eclipse IDE, right click on the launch file, Run as => Build Bonita Process Model. 

A console should appear and after a while, the build will be done. You can found the jar in the target folder of each bundle project. 

### With Maven
```
mvn install
```
This will install all the Bonita Process Domain Logic artifacts and also a maven archetype process-reader-archetype.

## How to use the Bonita Process Domain model into your own work ?
To make it more easy for you, this project contains a maven archetype, which allows to setup a project reading a Bonita Business Process Model file.  

### The maven archetype : process-reader-archetype
This setups a classic maven project that uses:
* [org.bonitasoft.bpm.model] (https://github.com/bonitasoft/bonita-process-model) for knowing the Bonita Process Domain Logic
* [org.eclipse.emf.ecore](https://www.eclipse.org/emf) to manipulate and load the model
* A sample .proc Business Process Model file

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

| Parameter                      | Required   | Default value                      | Description                  |
| -------------------------------|------------|------------------------------------|------------------------------|
| -DarchetypeGroupId             | __true__   | __org.bonitasoft.archetypes__      | Group id of the archetype    |
| -DarchetypeArtifactId          | __true__   | __process-reader-archetype__       | Artifact id of the archetype (must be fixed to the given value) |
| -DarchetypeVersion             | __false__  | Latest release version (9.0.0)     | Version id of the archetype  |
| -DgroupId                      | __true__   |                                    | Group id of the project      |
| -DartifactId                   | __true__   |                                    | Artifact id of the project   |
| -Dversion                      | __true__   | suggested and asked interactively  | Version of the project, suggested to use 0.0.1-SNAPSHOT |
| -Dpackage                      | __false__  | suggested and asked interactively  | Name of the root java package|
| -DdisplayName                  | __false__  | suggested and asked interactively  | Name of the project displayed|
| -Ddescription                  | __false__  |     Describe your project here     | Description of the project   |
| -Dwrapper                      | __false__  | true                               | If set to true, project will setup a [maven wrapper](https://github.com/takari/maven-wrapper)|
| -Djava-version                 | __false__  | Same as in the main module __\*__  | Version of Java used by the project. |
| -Dbonita-process-model-version | __false__  | Same as in the main module __\*__  | Version of the bonita-process-model used. |
| -Dmaven-clean-version          | __false__  | Same as in the main module __\*__  | Version of maven-clean-plugin module used by the project. |
| -Dmaven-resources-version      | __false__  | Same as in the main module __\*__  | Version of maven-resources-plugin module used by the project. |
| -Dmaven-compiler-version       | __false__  | Same as in the main module __\*__  | Version of maven-compiler-plugin module used by the project. |
| -Dmaven-surefire-version       | __false__  | Same as in the main module __\*__  | Version of maven-surefire-plugin module used by the project. |
| -Dmaven-jar-version            | __false__  | Same as in the main module __\*__  | Version of maven-jar-plugin module used by the project. |
| -Dmaven-install-version        | __false__  | Same as in the main module __\*__  | Version of maven-install-plugin module used by the project. |
| -Dmaven-deploy-version          | __false__  | Same as in the main module __\*__  | Version of maven-deploy-plugin module used by the project. |

__\* *These parameters are used to fix some versions. It is recommended not to use them and let the default values be used to ensure compatibility.*__

The versions of dependent modules are taken recursively by org.bonitasoft.bpm modules, so you can not choose them. This concerns EMF Core, EMF Common, EMF Ecore XMI, EMF Edit and maven-artifact modules.

## Tutorial : displaying the content of a simple process
This tutorial will help you to read the content of a process file from the Bonita Studio and to display the content (tasks, events, transitions... ). 
The code itself is already present in your newly project created.

### The business process model file 
In the resources folder of your newly created project, you should have a MyDiagram-1.0.proc file. 
This file is a Business Process Model File coming from Bonita Studio. It's a very simple process containing 4 tasks and a simple flow.

### Launching the application with App.java
The App.java contain a main method which can be used to launch this application like a java application. It does the following things:
* **Loading the process file:** It will load the process file by using the ModelLoader class present in org.bonitasoft.bpm.model. This loading is very important : it will allow you to manipulate the content of the process itself through emf. 
* **Displaying the content loaded in html:** The ModelDisplayer present in the project created can show you how to display in an html page the content of the process file. It can be used as a way to better understand how emf and the model logic work. 

## Other tools related to Bonita Process Domain models

### Import from and export to BPMN 2.0
//TODO

## P2 update site for integration with Eclipse RCP
You can also use the maven update site as a P2 repository / update site to use the provided features in your [Eclipse RCP](https://www.vogella.com/tutorials/EclipseRCP/article.html) application.
For the repository URL, you have to mention [mvn:org.bonitasoft.bpm:org.bonitasoft.bpm.eclipse-repository:<version>:zip] in your target platform (replace <version> with the intended one).
P2 will then ask Maven to search for the **eclipse-repository** maven artifact and will extract the P2 repository from it (provided your Maven configuration has access to Maven Central).

## EMF Resources Knowledge
- Emf official eclipse documentation: https://www.eclipse.org/modeling/emf/docs/
- A tutorial describing the usage of Eclipse EMF:  https://www.vogella.com/tutorials/EclipseEMF/article.html
