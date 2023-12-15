pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat '''
                powershell -File '.\Jenkins_Test.ps1' 
                '''
            }
        }
    }
}