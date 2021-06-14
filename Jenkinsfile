node ('docker') {
    Map scmVars = scmCheckout()
    sh "git pull origin ${BRANCH_NAME} && npm version patch -m 'Automated version bump to %s'"
    github.push(scmVars.GIT_URL, '506843ca-f776-43d2-aa72-143072aa2688', BRANCH_NAME)
}

