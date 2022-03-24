import static PipelineJobUtils.addGitScmDefinition
import static PipelineJobUtils.addEcsParameters

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (job: job)

job.with {
    parameters {
        string {
            name("testparam")
        }
    }
}

addEcsParameters(job: job, cpu: "2048", memory: "8192")

addGithubWebhookTrigger(job: job, token: "Test")
