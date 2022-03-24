import static PipelineJobUtils.addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)

job.with {
    parameters {
        choice {
            name("testparam")
            choices(["one", "two"])
            defaultValue("two")
        }
    }
}