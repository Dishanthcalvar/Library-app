pipeline {

    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout') {

            steps {

                git branch: 'main',
                url: 'https://github.com/chethan6186/mvn1.git'

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
        }

        stage('Package') {

            steps {

                sh 'mvn package'

            }
        }

        stage('Run Application') {

            steps {

                sh 'mvn exec:java -Dexec.mainClass="com.example.app.App"'

            }
        }
    }

    post {

        success {

            emailext(
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build succeeded!\nCheck: ${BUILD_URL}",
                to: "dishanth2904@gmail.com"
            )
        }

        failure {

            emailext(
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build failed!\nCheck: ${BUILD_URL}",
                to: "dishanth2904@gmail.com"
            )
        }
    }
}
