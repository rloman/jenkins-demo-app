def gv; // holds the groovy script which in initialised below in the init step

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        NEW_VERSION = "0.2.0-SNAPSHOT"
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
            steps {
                echo "Testing the application ... "
            }
        }
        stage("release") {
            when {
                expression {
                    when {
                        anyOf {
                            branch 'develop'; branch 'release/*'; branch 'hotfix/*'
                        }
                    }
                }
            }
            steps {
                echo "Releasing the application (using the release/ branch) ... "
                echo "Release the app with version: ${NEW_VERSION}"
                echo "Release the app with build number: ${BUILD_NUMBER}"
            }
        }
        stage("deploy") {
            steps {
                echo "Deploying the application ... "
                script {
                    withCredentials([usernamePassword(credentialsId: 'registry.hub.docker.com',
                            usernameVariable: 'USER', passwordVariable: 'PWD'
                    )]) {
                        gv.printSome(USER, PWD)
                    }
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
        }
    }
}