import static PipelineJobUtils.addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)

job.with {
    parameters {
        choiceParameter {
            name("testparam")
            choiceType("PT_SINGLE_SELECT")
            randomName("")
            filterable(false)
            filterLength(0)
            script {
                groovyScript {
                    script {
                        sandbox(true)
                        script("return ['256', '512', '1024:selected', '2048', '4096")
                    }
                }
            }
        }
    }
}