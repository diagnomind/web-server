pipeline {
  agent any
  stages {
    stage("Tests") {
      steps {
        sh "mvn clean verify"
      }
    }
  }
}