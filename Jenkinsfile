pipeline {
  agent {
    docker {
      image 'maven:3.3.9-jdk-8'
      args '-v users man/.m2:/root/.m2'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh '''echo PATH = ${PATH}
mvn clean'''
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -Dmaven.test.failure.ignore=true install'
      }
    }

    stage('Report') {
      steps {
        archiveArtifacts 'target/*.jar'
      }
    }

  }
}