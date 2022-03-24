import javaposse.jobdsl.dsl.Job

class JobUtils {
    static void addDefaultPipelineDefinition(Job job, String repoUrl, String _branch = "main", String _scriptPath = "Jenkinsfile") {
        job.with {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url(repoUrl)
                                credentials(GITHUB_ENTERPRISE_CREDENTIALS_ID)
                            }
                            branch(_branch)
                        }
                        lightweight()
                        scriptPath(_scriptPath)
                    }
                }
            }
        }
    }
}