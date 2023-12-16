node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'mvn';
    withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar -Dsonar.projectKey=diagnomind-java -Dsonar.projectName='diagnomind-java'"
    }
  }
}