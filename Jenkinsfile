node ('master || default || background') {
    echo 'Test Actions! change'
    echo 'is this working?'
    echo 'Probably'

    scmVars = checkout scm

    if (currentBuild.changeSets) {
        String changelog = '*Git Changelog:*\n'
        currentBuild.changeSets[0].items.each {
            changelog += "\n- `${it.commitId.take(7)}` ${it.getMsgEscaped()} (${it.getAuthorEmail()})"
        }
        echo changelog
    }

    echo "${scmVars.toString()}"
}