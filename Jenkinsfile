node ('built-in') {
    cleanWs()
    checkout scm
    echo getLastSuccessfulCommit()
    echo env
    cleanWs()
}

@NonCPS
String getLastSuccessfulCommit() {
    def build = currentBuild.previousBuild
    String commit = ''
    while (build != null) {
        echo(build.buildVariables, true)
        commit = build.buildVariables.GIT_COMMIT
        if (build.result == 'SUCCESS') {
            break
        }
        build = build.previousBuild
    }
    return commit
}


