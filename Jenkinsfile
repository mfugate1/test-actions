node ('master || default || background') {
    echo 'hello there'
    String version = '3.0.5'
    String owner = 'mfugate1'
    String repo = 'test-actions'
    String c= '506843ca-f776-43d2-aa72-143072aa2688'

    Map scmVars = checkout scm

    echo(scmVars, true)

    //createRelease(c, owner, repo, "v${version}", scmVars.GIT_BRANCH, "Release ${version}")

    sh 'git tag'
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