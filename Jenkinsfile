pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK11'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Dishanthcalvar/Library-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Run Application') {
            steps {
                sh 'java -jar target/library-app-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            emailext(
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: """
                Your Jenkins build passed!

                Job:    ${JOB_NAME}
                Build:  #${BUILD_NUMBER}
                Status: SUCCESS
                URL:    ${BUILD_URL}
                """,
                to: "dishanth2904@gmail.com"
            )
        }

        failure {
            emailext(
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: """
                Your Jenkins build failed!

                Job:    ${JOB_NAME}
                Build:  #${BUILD_NUMBER}
                Status: FAILED
                URL:    ${BUILD_URL}

                Check console output at the URL above for details.
                """,
                to: "dishanth2904@gmail.com"
            )
        }
    }
}
