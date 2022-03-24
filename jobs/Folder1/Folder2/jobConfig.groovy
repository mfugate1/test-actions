import static PipelineJobUtils.addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)

String ecsMemoryScript = """\
    List possibleValues = [512] + (1..30).collect{it * 1024}
    int cpu = ecsCpu as Integer
    return possibleValues.findAll{it >= 2 * cpu && it <= 8 * cpu}"""

job.with {
    parameters {
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