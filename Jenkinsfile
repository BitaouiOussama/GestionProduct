pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/BitaouiOussama/GestionProduct.git'
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Post-Build') {
            steps {
                echo 'Build and Test completed successfully!'
            }
        }
    }
}
