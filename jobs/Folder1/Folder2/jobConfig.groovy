String jobPath = new File(__FILE__).parent

println(hudson.model.Executor.currentExecutor().getCurrentWorkspace().getRemote())
