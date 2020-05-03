node {
    stage('SCM Checkout'){
        git credentialsId: 'GIT_CREDENTIALS', url:  'https://github.com/Steven8519/developer.git', branch: 'master'
    }
    stage("Maven Build"){
      sh " mvn clean install -X"

    }
    stage('Build Docker Image'){
        sh 'docker build -t steven8519/developer .'
    }

    stage('Push Docker Image'){
        withCredentials([string(credentialsId: 'docker_hub', variable: 'docker_hub')]) {
          sh "docker login -u steven8519 -p ${docker_hub}"
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
