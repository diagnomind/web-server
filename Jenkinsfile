pipeline {
  agent any
  stages {
    stage("Tests") {
      steps {
        sh "mvn clean verify sonar:sonar \
              -Dsonar.projectKey=web-server \
              -Dsonar.projectName='web-server' \
              -Dsonar.host.url=https://sonarqube.diagnomind.duckdns.org \
              -Dsonar.token=sqp_b8e33aa7f656a5ed4c4103f3652580451711bcc9"
      }
    }
  }
}