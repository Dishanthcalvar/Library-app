pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK11'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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

        stage('Build') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Verify JAR') {
            steps {
                sh 'java -jar target/library-app-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            // Email notification
            mail to: 'dishanth2904@gmail.com',
                 subject: "✅ BUILD SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """
                 Your Jenkins build passed successfully!

                 Job:    ${env.JOB_NAME}
                 Build:  #${env.BUILD_NUMBER}
                 Status: SUCCESS
                 URL:    ${env.BUILD_URL}
                 """

            // Phone notification via ntfy
            sh """
                curl -d "Build #${env.BUILD_NUMBER} PASSED for ${env.JOB_NAME}" \
                     -H "Title: Jenkins Build Success" \
                     -H "Priority: default" \
                     -H "Tags: white_check_mark" \
                     https://ntfy.sh/jenkins-yourname-build
            """
        }

        failure {
            // Email notification
            mail to: 'dishanth2904@gmail.com',
                 subject: "❌ BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """
                 Your Jenkins build has failed!

                 Job:    ${env.JOB_NAME}
                 Build:  #${env.BUILD_NUMBER}
                 Status: FAILED
                 URL:    ${env.BUILD_URL}

                 Check console output at the URL above for details.
                 """

            // Phone notification via ntfy
            sh """
                curl -d "Build #${env.BUILD_NUMBER} FAILED for ${env.JOB_NAME}" \
                     -H "Title: Jenkins Build Failed" \
                     -H "Priority: urgent" \
                     -H "Tags: x" \
                     https://ntfy.sh/jenkins-yourname-build
            """
        }
    }
}
