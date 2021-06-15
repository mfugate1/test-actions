node ('docker') {
    changedFiles()
}

void changedFiles() {
    currentBuild.changeSets.each { changeSet ->
        changeSet.items.each {
            echo it.commitId
            echo it.msgEscaped
            echo it.author
            echo it.affectedPaths
            it.affectedFiles.each { file ->
                echo file.getPath()
            }
        }
    }
}

