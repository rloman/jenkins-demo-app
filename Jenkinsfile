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