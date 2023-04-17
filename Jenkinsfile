pipeline {
    agent any
    environment {
          JIRA_XRAY_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI2M2VmM2E0MDQwZDBmZTcwOTA3NTdhNzIiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTY4MTY1NTcwMiwiZXhwIjoxNjgxNzQyMTAyLCJhdWQiOiIyMUI3NTNDNzc1MkE0RjQwQTg5N0FEOTU4QTY2NjQ3MyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjIxQjc1M0M3NzUyQTRGNDBBODk3QUQ5NThBNjY2NDczIn0.cedOGWxuEmi3JOMPv0S1TKQFN4MVh17GIDuGN97MGDI"
    }

    stages {
        stage('exporting BDD tests cases from Jira/Xray') {
            steps {
                sh "curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer ${JIRA_XRAY_TOKEN}" "https://xray.cloud.getxray.app/api/v1/export/cucumber?keys=POEI23-388;POEI23-378;POEI23-377&fz=true" --output features.zip"
                sh "unzip features.zip -d src/test/resources/features"
                sh "rm features.zip"
            }
        }
        stage('Launch Tests') {
            steps {
                 sh "mvn clean test"
            }
        }
        stage('Importing tests execution to Jira/Xray') {
            steps {
                    sh "curl -H "Content-Type: application/json" -X POST -H "Authorization: Bearer ${JIRA_XRAY_TOKEN"  --data @"target/cucumber.json" https://xray.cloud.getxray.app/api/v1/import/execution/cucumber"
            }
        }
    }

      post {
        always {
          step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
      }
}