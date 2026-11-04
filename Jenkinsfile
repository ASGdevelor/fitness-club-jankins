pipeline {
    agent any

    stages {

        stage('Check workspace') {
            steps {
                bat 'echo CURRENT DIRECTORY:'
                bat 'cd'
                bat 'echo FILES:'
                bat 'dir /s /b'
            }
        }

        stage('Project Information') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" help:evaluate -Dexpression=project.artifactId -q -DforceStdout'
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" help:evaluate -Dexpression=project.version -q -DforceStdout'
            }
        }

        stage('Build') {
            steps {
                echo 'Компиляция проекта'
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" compile'
            }
        }

        stage('Static Analysis') {
            steps {
                echo 'Запуск статического анализа Checkstyle'
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" checkstyle:check'
            }
        }

        stage('Test') {
            steps {
                echo 'Запуск автоматических тестов'
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" test'
            }
        }

        stage('Package') {
            steps {
                echo 'Создание JAR-файла'
                bat '"D:\\IDEA\\IntelliJ IDEA 2026.2.2\\plugins\\maven-plugin\\lib\\maven3\\bin\\mvn.cmd" package -DskipTests'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
