def gv; // holds the groovy script which in initialised below in the init step

pipeline {
    agent any
    environment {
        NEW_VERSION = "0.2.0-SNAPSHOT"
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                    // rloman test vrijdag
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
