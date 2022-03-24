import static PipelineJobUtils.addGitScmDefinition
import static PipelineJobUtils.addEcsParameters

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (job: job)
addEcsParameters(job: job, cpu: "2048")