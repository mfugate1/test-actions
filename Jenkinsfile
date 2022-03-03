node ('built-in') {
    Map scmVars = checkout scm
    sh "git remote -v"
    sh "git tag 84.44.33"
    sh "git push origin 84.44.33"
    cleanWs()
}



