pipeline {

    agent any

    tools {

        jdk 'JDK25'
        maven 'Maven'
    }

    stages {


        stage('Check Environment') {
            steps {
                bat 'java -version'
                bat 'echo %JAVA_HOME%'
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

    }
<<<<<< HEAD

}
}
