pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                powershell(script: "Jenkins_Test.ps1")                
            }
        }
    }
}