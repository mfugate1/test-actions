import static PipelineJobUtils.addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)

job.with {
    parameters {
        activeChoiceParam("ecsCpu") {
            choiceType("SINGLE_SELECT")
            groovyScript {
                script("['256', '512', '1024:selected', '2048', '4096']")
                fallbackScript("'Error getting cpu values'")
            }
        }
    }
}