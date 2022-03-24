import javaposse.jobdsl.dsl.Job

class PipelineJobUtils {
    

    static void addGitScmDefinition (Map config) {
        String repoUrl = config.repoUrl ?: "https://github.com/mfugate1/test-actions"

        job.with {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url(config.repoUrl)
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
}