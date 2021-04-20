node ('master || default || background') {
    echo 'hi'
}

void createRelease(String credentialsId, String owner, String repo, String tag, String targetCommitish, String name, String body = '') {
    String url = "https://api.github.com/repos/${owner}/${repo}/releases"
    
    String previousTag = ''
    catchError {
        def response = httpRequest authentication: credentialsId,
                                   url: "${url}/latest"

        previousTag = readJSON(text: response.content).tag_name
    }

    if (previousTag && !body) {
        body = sh("git log --color=never --pretty=' - %h %s' ${previousTag}..HEAD", true)
    }

    Map requestBody = [
        tag_name: tag,
        target_commitish: targetCommitish,
        name: name,
        body: body
    ]

    httpRequest authentication: credentialsId,
                contentType: 'APPLICATION_JSON', 
                httpMode: 'POST', 
                requestBody: writeMapToJsonString(requestBody), 
                url: url, 
                validResponseCodes: '201' 
}