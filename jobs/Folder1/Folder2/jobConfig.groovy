import com.carnegielearning.jenkins.JobUtils.addDefaultPipelineDefinition

job = pipelineJob ("TEST-JOB-666")

addDefaultPipelineDefinition(job, "https://github.com/mfugate1/test-actions", "master")