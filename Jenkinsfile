def gv; // holds the groovy script which in initialised below in the init step
def port = 8088;

if (env.BRANCH_NAME == "release/v0.2.0") {
    port = 8089
}
if (env.BRANCH_NAME == "master") {
    port = 8090
}

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        NEW_VERSION = "0.2.0-SNAPSHOT"
        ARTIFACTID = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build") {
            steps {
                script {
                    gv.buildApp()
                }
                //sh 'mvn clean verify'
            }
        }
        stage("test") {
            steps { // for every branch
                echo "Testing the application ... "
            }
        }
        stage("acceptance") {
            when {
                anyOf {
                    branch 'release/*'; branch 'hotfix/*'
                }
            }
            steps {
                echo "Releasing the application to acceptance (using the release/ branch) ... "
                echo "Release the app with version: ${NEW_VERSION}"
                echo "Release the app with build number: ${BUILD_NUMBER}"
            }
        }
        stage("prod") {
            when {
                anyOf {
                    branch 'master'
                }
            }
            steps {
                echo "Deploying the application to production ... since the master branch is updated"
                script {
                    withCredentials([usernamePassword(credentialsId: 'registry.hub.docker.com',
                            usernameVariable: 'USER', passwordVariable: 'PWD'
                    )]) {
                        gv.printSome(USER, PWD)
                    }
                }
            }
        }
        stage("docker") {
            steps {
                echo "Deploying the application to release with docker"
                script {
//                    sh "docker container stop -f ${BUILD_TAG}-v${VERSION}"
                  
                    //sh "docker container run -dit --name ${BUILD_TAG}-v${VERSION} -p ${port}:8080 rloman/backend-scaffolder:2.3.0-SNAPSHOT"
                }
            }
        }
    }

    post {
        failure {
            echo "Build failed (in post)"
        }
        success {
            echo "Build succcess (in post)"
        }
        always {
            echo "Build completed in always (in post)"
            echo "project.version is:  [${VERSION}]"
        }
    }
}
