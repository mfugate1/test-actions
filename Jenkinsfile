node ('docker') {
    checkout scm
    String script = 'npm version patch && git add package.json package-lock.json && git pull && git commit -m"Automated version bump" && git push'
    Map results = betterSh script: script, logFilename: "version-increment.log"
    echo results.output
    echo results.exitCode
}

