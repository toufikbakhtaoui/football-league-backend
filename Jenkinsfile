pipeline {
   agent any

    tools {
       jdk 'JDK-21'
       maven 'Maven-3.9'
    }

    environment {
        MVN_CMD = 'mvn -B -ntp'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build / Tests / Package') {
            steps {
                sh """
                  ${MVN_CMD} clean verify
                """
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    junit '**/target/failsafe-reports/*.xml'
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                            sh """
                               ${MVN_CMD} sonar:sonar -DskipTests
                            """
                    }
                }
            }
        }
    }
}
