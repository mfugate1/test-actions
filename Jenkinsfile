node ('built-in') {
    Map scmVars = checkout([
        $class: 'GitSCM',
        branches: scm.branches,
        extensions: scm.extensions + [[$class: 'LocalBranch']],
        userRemoteConfigs: scm.userRemoteConfigs
    ])
}



