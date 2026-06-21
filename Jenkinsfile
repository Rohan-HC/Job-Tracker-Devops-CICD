pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        timestamps()
    }

    environment {
        IMAGE_NAME = 'job-tracker'
        IMAGE_TAG = "build-${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                dir('app') {
                    sh 'chmod +x mvnw'
                    sh './mvnw test'
                }
            }
        }

        stage('Build Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG -t $IMAGE_NAME:latest .'
            }
        }

        stage('Deploy Locally') {
            steps {
                sh '''
                    docker rm -f job-tracker || true
                    docker run -d \
                      --name job-tracker \
                      --restart unless-stopped \
                      -p 8081:8080 \
                      $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }

        stage('Smoke Test') {
            steps {
                sh '''
                    for attempt in $(seq 1 15); do
                      curl --fail --silent http://localhost:8081/actuator/health && exit 0
                      sleep 2
                    done
                    exit 1
                '''
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'app/target/surefire-reports/*.xml'
        }
    }
}
