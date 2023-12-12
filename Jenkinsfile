pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/enaut-genua/diagnomind.git'
                bat 'mvn clean compile'
            }
        }
    }
}