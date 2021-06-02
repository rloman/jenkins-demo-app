pipeline {
    agent any
    environment {
        NEW_VERSION = "0.2.0-SNAPSHOT"
    }

    stages {
        stage("build") {
            steps {
                echo "Building the application ... "
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
                    BRANCH_NAME == 'release/*' || BRANCH_NAME == 'master'
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
                withCredentials([usernamePassword(credentials: 'registry.hub.docker.com',
                        usernameVariable: 'USER', passwordVariable: 'PWD'
                    )]) {
                    sh "some script ${USER} ${PWD}"
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