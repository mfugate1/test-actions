


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
        String defaultCpu = config.cpu ?: "1024"
        String ecsCpuScript = """\
            return [256, 512, 1024, 2048, 4096].collect {
                it == ${defaultCpu} ? it.toString() + ':selected' : it
            }""".stripIndent()

        String defaultMemory = config.memory ?: "null"
        String ecsMemoryScript = """\
            int defaultMemory = ${defaultMemory}
            List possibleValues = [512] + (1..30).collect{it * 1024}
            int cpu = ecsCpu as Integer
            return possibleValues.findAll{it >= 2 * cpu && it <= 8 * cpu}
                                 .collect {it == defaultMemory ? it.toString() + ':selected' : it}""".stripIndent()

        String ecsImageScript = '''\
            import com.amazonaws.services.ecrpublic.AmazonECRPublicClient
            import com.amazonaws.services.ecrpublic.AmazonECRPublicClientBuilder
            import com.amazonaws.services.ecrpublic.model.DescribeImagesRequest
            import com.amazonaws.services.ecrpublic.model.DescribeRegistriesRequest
            AmazonECRPublicClient client = AmazonECRPublicClientBuilder.standard()
                                                                       .withRegion("us-east-1")
                                                                       .build()
            String repo = "jenkins-agent"
            String registryUri = client.describeRegistries(new DescribeRegistriesRequest())
                                       .getRegistries()[0]
                                       .getRegistryUri()
            List tags = client.describeImages(new DescribeImagesRequest().withRepositoryName(repo))
                               .getImageDetails().collect{it.getImageTags()}.flatten()

            return ["jenkins/inbound-agent:alpine"] + tags.sort().collect{"${registryUri}/${repo}:${it}"}'''.stripIndent()

        config.job.with {
            parameters {
                string {
                    name("ecsImage")
                    defaultValue(config.image ?: "jenkins/inbound-agent:alpine")
                }
                choiceParameter {
                    name("ecsImage")
                    description("Docker image to use for ECS task.")
                    choiceType("PT_SINGLE_SELECT")
                    randomName("")
                    filterable(true)
                    filterLength(1)
                    script {
                        groovyScript {
                            script {
                                sandbox(true)
                                script(ecsImageScript)
                            }
                            fallbackScript {
                                script("'Error getting list of images'")
                                sandbox(true)
                            }
                        }
                    }
                }
                choiceParameter {
                    name("ecsCpu")
                    description("""\
                        Memory (in MB) to allocate to the ECS task. List is dynamically 
                        populated to only include valid values based on the CPU units 
                        chosen above.""".stripIndent().replaceAll("[\\n]", "")
                    )
                    choiceType("PT_SINGLE_SELECT")
                    randomName("")
                    filterable(false)
                    filterLength(0)
                    script {
                        groovyScript {
                            script {
                                sandbox(true)
                                script(ecsCpuScript)
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
