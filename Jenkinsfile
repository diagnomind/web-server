pipeline {
  agent any
  stages {
    stage("Tests") {
      steps {
        sh "mvn clean test verify"
      }
    }
  }
}