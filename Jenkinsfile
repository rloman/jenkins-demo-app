pipeline {
    agent any

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
                    BRANCH_NAME == 'release' || BRANCH_NAME == 'master'
                }
            }
            steps {
                echo "Releasing the application (using the release/ branch) ... "
                echo "Release the app with build number: ${BUILD_NUMBER}"
            }
        }
        stage("deploy") {
            steps {
                echo "Deploying the application ... "
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