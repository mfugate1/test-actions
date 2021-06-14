node ('docker') {
    sh 'npm version patch && git add package.json package-lock.json && git pull && git commit -m"Automated version bump" && git push'
}

