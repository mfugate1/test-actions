package com.carnegielearning.jenkins

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