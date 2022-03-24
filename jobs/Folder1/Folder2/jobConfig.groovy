import static PipelineJobUtils.addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)

job.with {
    parameters {
        activeChoiceParameter ("ecsCpu") {
            choiceType("SINGLE_SELECT")
            groovyScript {
                script("return ['256', '512', '1024:selected', '2048', '4096']")
            }
        }
    }
}