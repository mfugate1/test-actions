import javaposse.jobdsl.dsl.Job

    
static void addGitScmDefinition (Map config) {
    String repoUrl = config.repoUrl ?: "https://github.com/mfugate1/test-actions"

    config.job.with {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                        }
                        branch(config.branch ?: "main")
                    }
                    lightweight()
                    scriptPath(config.scriptPath ?: "Jenkinsfile")
                }
            }
        }
    }
}
