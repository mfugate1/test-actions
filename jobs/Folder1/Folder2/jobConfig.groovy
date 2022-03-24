import static JobUtils.addDefaultPipelineDefinition

job = pipelineJob ("TEST-JOB-666")

addDefaultPipelineDefinition(job, "https://github.dev.carnegielearning.com/CarnegieLearning/curate")