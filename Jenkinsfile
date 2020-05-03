node {
    stage('SCM Checkout'){
        git credentialsId: 'GIT_CREDENTIALS', url:  'https://github.com/Steven8519/developer.git', branch: 'master'
    }
    stage("Maven Build"){
      sh " mvn clean install"

    }
    stage('Build Docker Image'){
        sh 'docker build -t steven8519/developer .'
    }

    stage('Push Docker Image'){
        withCredentials([string(credentialsId: 'Docker_Hub_ID', variable: 'Docker_Hub_ID')]) {
          sh "docker login -u steven8519 -p ${Docker_Hub_ID}"
        }
        sh 'docker push steven8519/developer'
    }

    stage("Deploy app"){
       kubernetesDeploy(
         configs: 'deployment.yaml',
         kubeconfigId: 'KUBERNATES_CONFIG',
         enableConfigSubstitution: true
        )
    }
}
