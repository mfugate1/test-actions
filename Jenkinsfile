node ('master || default || background') {
    echo 'Test Actions! change'
    echo 'is this working?'
    echo 'Probably'

    catchError {
        echo 'nope'
    }

    if (currentBuild.result != 'FAILURE') {
        echo 'not a failure'
    } else {
        echo 'failure'
    }

    catchError {
        sh 'blargdebalrk'
    }

    if (currentBuild.result != 'FAILURE') {
        echo 'not a failure'
    } else {
        echo 'failure'
    }
}