def buildOptions = buildOptions(env, params)

pipeline {
     
    agent any
    
    options {
        skipDefaultCheckout() // Custom shallow clone in stage
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '3'))
        skipStagesAfterUnstable()
    }
    
    environment {
       JAVA_TOOL_OPTIONS = ''
       JAVA_HOME = "$JAVA_HOME_17"
       MAVEN_OPTS = '-Xmx2g -Dstyle.color=always -Djansi.passthrough=true'
    }
    
    stages {
    
        stage('🌍 SCM Checkout') {
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: scm.branches,
                        doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
                        extensions: scm.extensions +  [[$class: 'CloneOption',noTags: true, shallow: true, depth: 0, timeout: 20],[$class: 'RelativeTargetDirectory', relativeTargetDir: 'sources']],
                        userRemoteConfigs: scm.userRemoteConfigs
                   ])
           }
        }
        
        stage('⚙ Build') {
    
            steps {
                dir('sources'){
                    echo "params=$params"
                    echo "buildOptions=$buildOptions"
                    sh './mvnw --version'
                    configFileProvider([configFile(fileId: 'maven-settings', variable: 'MAVEN_SETTINGS')]) {
                        sh "./mvnw -s $MAVEN_SETTINGS --no-transfer-progress install -DmavenSettingsFile=$MAVEN_SETTINGS -DsignServiceURL=${env.SIGN_SERVICE_URL} -DmacSignServiceURL=${env.MAC_SIGN_SERVICE_URL} -DmacBuildAndSignInstallerServiceURL=${env.BUILD_AND_SIGN_MAC_INSTALLER_SERVICE_URL} -Dtest.workspace.dir=\"${env.WORKSPACE}\" ${buildOptions.mvnProfiles} ${buildOptions.mvnArgs} 2> /dev/null"
                    }
                }
           }
        }
     
    }
    
   post {
        always {
            junit allowEmptyResults : true, testResults: 'sources/**/target/*-reports/*.xml'
            publishHTML target: [
				            allowMissing: true,
				            alwaysLinkToLastBuild: false,
				            keepAll: true,
				            reportDir: 'sources/reports/target/site/jacoco-aggregate',
				            reportFiles: 'index.html',
				            reportName: 'Business Process Model Jacoco Report'
				          ]
            archiveArtifacts  artifacts: '''sources/sites/*/target/*''',
                              allowEmptyArchive: true,
                              fingerprint: true
        }
        
        failure {
            archiveArtifacts  artifacts: '''sources/tests/**/screenshots/*,
                                            **/.metadata/*.log''',
                              allowEmptyArchive: true
        }
        
   }
    
}

def buildOptions(env, params){
    def isPRBuild = env.BRANCH_NAME.startsWith("PR-")
    def prBody = isPRBuild && pullRequest['body'] ? pullRequest['body'].toLowerCase() : ''
    def buildOptions = isPRBuild ? [
        isPRBuild: isPRBuild,
        skipTests: prBody.contains("!skiptests"),
        forceITExec: prBody.contains("!executeit"),
        zip: prBody.contains("!zip"),
        sign: prBody.contains("!sign"),
        mvnArgs: prBody.contains("!mvnargs=") ? parseArgs(prBody) : '',
        mvnProfiles: '-Pdefault,jacoco'
    ] : [
        isPRBuild: false,
        skipTests: params.skipTests ?: false,
        forceITExec: params.forceITExec ?: true,
        zip: params.zip ?: true,
        sign: params.sign ?: false,
        mvnArgs: '',
        mvnProfiles: '-Pdefault,jacoco'
    ]
    
    buildOptions.forceITExec = buildOptions.forceITExec && !buildOptions.skipTests
    if(buildOptions.skipTests){
          buildOptions.mvnArgs =   buildOptions.mvnArgs + ' -DskipTests'
    }
    buildOptions.mvnProfiles = buildOptions.zip ? buildOptions.mvnProfiles + ',all-in-one' : buildOptions.mvnProfiles
    buildOptions.mvnProfiles = buildOptions.sign ? buildOptions.mvnProfiles + ',sign' :  buildOptions.mvnProfiles
  	buildOptions.mvnProfiles = buildOptions.forceITExec ? buildOptions.mvnProfiles + ',it' :  buildOptions.mvnProfiles
    def remoteConfiguration = "${env.ALT_DEPLOYMENT_REPOSITORY_SNAPSHOTS}".split('::')
    buildOptions.remoteId = remoteConfiguration[0]
    buildOptions.remoteUrl = remoteConfiguration[2]
            
    return buildOptions
}

def String parseArgs(prBody){
     int start = prBody.indexOf("!mvnArgs=")
     if(start != -1){
        def argsLine = prBody.substring(start+"!mvnArgs=".length()) 
        int end = argsLine.indexOf("\\n")
        return end != -1 ? argsLine.substring(0,end) : argsLine
     }
     return ''
}
