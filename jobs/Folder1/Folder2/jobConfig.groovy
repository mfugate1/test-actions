String jobPath = new File(__FILE__).parent

println(__FILE__)

pipelineJob("${jobPath - 'jobs/'}".replaceAll("/", "-")) {
  definition {
        cps {
            sandbox(true)
            script(readFileFromWorkspace("testJenkinsfile"))
        }
    }
    
}
