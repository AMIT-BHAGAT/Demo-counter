pipeline{

    agent any

    environment{
        VERSION  = "${env.BUILD_ID}"
    }

    stages{

        stage('sonar quality check'){

            agent{

                docker{

                    image 'maven'
                    args '-u root'
                }
            }
            steps{

              script{ 
                
                withSonarQubeEnv(credentialsId: 'sonar-token') {
                    // some block
                    sh 'mvn clean package sonar:sonar'
                 
                    }

                  }
            }  
        }
        stage('Quality Gate Status'){
                
                steps{
                    
                    script{
                        
                        waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                    }
                }
            }

            stage('docker build & docker push to Nexus repo') {
                steps{
                    script{
                        withCredentials([string(credentialsId: 'nexus_passwd', variable: 'nexus_creds')]) {
                            sh '''
                                docker build -t 3.110.87.207:8083/springapp:${VERSION} .
                                docker login -u admin -p Nexus 3.110.87.207:8083
                                docker push 3.110.87.207:8083/springapp:${VERSION}
                                docker rmi 3.110.87.207:8083/springapp:${VERSION}
                            '''
                        }
                    }
                
                }
            }

        post {
		always {
			mail bcc: '', body: "<br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "${currentBuild.result} CI: Project name -> ${env.JOB_NAME}", to: "kausarmujawar1011@gmail.com";  
		}
	}

        
    }
}

    

          