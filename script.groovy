def buildApp() {
    echo "Building the application"
    sh 'mvn clean verify'
}

return this; // this is, so that Jenkins can get a hold of this scripts as a reference