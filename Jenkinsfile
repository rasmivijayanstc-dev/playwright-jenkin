
pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out code from GitHub'

                git branch: 'main',
                    url: 'https://github.com/rasmivijayanstc-dev/playwright-jenkin.git'
            }
        }

        stage('Check Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Install Playwright Browser') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: '**/target/surefire-reports/*.xml'

            echo 'Playwright execution completed'
        }

        success {
            echo 'All tests executed successfully!'
        }

        failure {
            echo 'Playwright test execution failed!'
        }
    }
}
