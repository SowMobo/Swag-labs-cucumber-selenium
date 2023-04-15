pipeline {
    agent any

    stages {
        stage('Init') {
            steps {
                echo "Testing..."
            }
        }
        stage('Run') {
            steps {
                 sh "mvn clean test"
            }
        }
    }

      post {
        always {
          step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
      }
}