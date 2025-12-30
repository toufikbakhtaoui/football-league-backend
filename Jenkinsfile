pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK-21'
    }

    environment {
        IMAGE_NAME = 'football-league-backend'
        MAVEN_REPO = '/var/jenkins_home/.m2/repository'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Tests') {
            steps {
                withMaven(
                    maven: 'Maven-3.9',
                    mavenLocalRepo: "${MAVEN_REPO}"
                ) {
                    sh '''
                      mvn clean verify \
                        -DskipITs=false \
                        -B
                    '''
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    junit '**/target/failsafe-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                        withMaven(
                            maven: 'Maven-3.9',
                            mavenLocalRepo: "${MAVEN_REPO}"
                        ) {
                            sh '''
                              mvn sonar:sonar \
                                -Dsonar.projectKey=football-league-backend \
                                -Dsonar.login=${SONAR_TOKEN}
                            '''
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Package') {
            steps {
                withMaven(
                    maven: 'Maven-3.9',
                    mavenLocalRepo: "${MAVEN_REPO}"
                    options: [
                        artifactsPublisher(disabled: true),
                        junitPublisher(disabled: true)
                      ]
                ) {
                    sh 'mvn package -DskipTests -B'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo '✅ Pipeline completed successfully'
        }
        failure {
            echo '❌ Pipeline failed'
        }
    }
}
