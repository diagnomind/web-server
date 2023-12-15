pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                
                powershell -File 'Jenkins_Test.ps1'
                
            }
        }
    }
}