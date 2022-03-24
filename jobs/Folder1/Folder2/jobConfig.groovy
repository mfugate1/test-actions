import static addGitScmDefinition

job = pipelineJob ("TEST-JOB-666")

addGitScmDefinition (
    job: job
)