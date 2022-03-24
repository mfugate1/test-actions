import static com.cl.dev.JobUtils.addDefaultPipelineDefinition

job = pipelineJob ("TEST-JOB")

addDefaultPipelineDefinition(job, "https://github.com/mfugate1/test-actions", "master")