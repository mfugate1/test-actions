node ('docker') {
    checkout scm
    sh 'git config --global user.email "jenkins@carnegielearning.com"'
    sh 'git config --global user.name "Jenkins"'
    withCredentials([usernameColonPassword(credentialsId: '506843ca-f776-43d2-aa72-143072aa2688', variable: 'USERPASS')]) {
        String script = "git pull origin ${BRANCH_NAME} && npm version patch && git log -1 && git push https://${USERPASS}@github.com/mfugate1/test-actions.git ${BRANCH_NAME}"
        Map results = betterSh script: script, logFilename: "version-increment.log"
        echo results.output
        echo results.exitCode
    }
}

