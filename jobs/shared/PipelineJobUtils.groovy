import javaposse.jobdsl.dsl.Job

class PipelineJobUtils {
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

    static void addEcsParameters(Map config) {
        String ecsMemoryScript = """\
            List possibleValues = [512] + (1..30).collect{it * 1024}
            int cpu = ecsCpu as Integer
            return possibleValues.collect{it >= 2 * cpu && it <= 8 * cpu}""".stripIndent()

        config.job.with {
            parameters {
                string {
                    name("ecsImage")
                    defaultValue(config.image ?: "jenkins/inbound-agent:alpine")
                }
                choiceParameter {
                    name("ecsCpu")
                    choiceType("PT_SINGLE_SELECT")
                    randomName("")
                    filterable(false)
                    filterLength(0)
                    script {
                        groovyScript {
                            script {
                                sandbox(true)
                                script("['256', '512', '1024:selected', '2048', '4096']")
                            }
                            fallbackScript {
                                script("'Error getting cpu values'")
                                sandbox(true)
                            }
                        }
                    }
                }
                cascadeChoiceParameter {
                    name("ecsMemory")
                    choiceType("PT_SINGLE_SELECT")
                    randomName("")
                    filterable(false)
                    filterLength(0)
                    referencedParameters("ecsCpu")
                    script {
                        groovyScript {
                            script {
                                sandbox(true)
                                script(ecsMemoryScript)
                            }
                            fallbackScript {
                                script("'Error getting memory values'")
                                sandbox(true)
                            }
                        }
                    }
                }
            }
        }
    }
}
