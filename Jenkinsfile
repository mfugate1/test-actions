node ('built-in') {
    cleanWs()
    checkout scm
    echo currentBuild.previousSuccessfulBuild.changeSets[0].last().commitId
    cleanWs()
}



