package com.cl.dev

class JobUtils {
    static void createPipelineJob(String jobName) {
        pipelineJob (jobName) {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url("https://github.com/mfugate1/test-actions")
                            }
                            branch("master")
                        }
                        lightweight()
                        scriptPath("Jenkinsfile")
                    }
                }
            }
            properties {
                disableConcurrentBuilds {
                    abortPrevious(false)
                }
            }
        }
    }
}