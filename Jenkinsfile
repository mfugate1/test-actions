node ('docker') {
    checkout scm
    sh 'git config --global user.email "jenkins@carnegielearning.com"'
    sh 'git config --global user.name "Jenkins"'
    String script = "git pull origin ${BRANCH_NAME} && npm version patch && git push origin ${BRANCH_NAME}"
    Map results = betterSh script: script, logFilename: "version-increment.log"
    echo results.output
    echo results.exitCode
}

