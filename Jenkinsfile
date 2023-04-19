pipeline {
    agent any
    environment {
          JIRA_XRAY_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI2M2VmM2E0MTMwMzBmYTdkYjgwYTMyMGEiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTY4MTgyNDU0MiwiZXhwIjoxNjgxOTEwOTQyLCJhdWQiOiJBQjBEOTlCRUIxOTg0QTY4ODE3RkFCRTBDNjNDODU4NCIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IkFCMEQ5OUJFQjE5ODRBNjg4MTdGQUJFMEM2M0M4NTg0In0.HKBi4CFpbbjCvTgAX7GUjy9ihzXO3X15mdTn2MxNSbE"
    }

    stages {
        stage('exporting BDD tests cases from Jira/Xray') {
            steps {
                sh "curl -H 'Content-Type: application/json' -X GET -H 'Authorization: Bearer ${JIRA_XRAY_TOKEN}' 'https://xray.cloud.getxray.app/api/v1/export/cucumber?keys=POEI23-388;POEI23-378;POEI23-377&fz=true' --output features.zip"
                sh "unzip -o features.zip -d src/test/resources/features"
//                 unzip  dir: 'src/test/rersources/features', glob: ' ', zipFile: 'features.zip'
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
                sh "curl -H 'Content-Type: application/json' -X POST -H 'Authorization: Bearer ${JIRA_XRAY_TOKEN}'  --data @'target/cucumber.json' https://xray.cloud.getxray.app/api/v1/import/execution/cucumber"
            }
        }
    }

      post {
        always {
          step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
      }
}