import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer

def buildApp() {
    echo "Building the application"
    // Disable maven for now, might come back later on sh 'mvn clean verify'
}

def printSome(user, pass) {

    echo user
    echo pass
}

return this; // this is, so that Jenkins can get a hold of this scripts as a reference
