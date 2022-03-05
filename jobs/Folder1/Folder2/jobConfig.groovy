String jobPath = new File(__FILE__).parent

println(__FILE__)

pipelineJob("${jobPath - 'jobs/'}".replaceAll("/", "-")) {
  definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url("https://github.dev.carnegielearning.com/CarnegieLearning/cli-jenkins.git")
                        credentials("github-enterprise-credentials")
                    }
                    branch("dev")
                }
                lightweight()
                scriptPath("jobs/Mathia/Publish-Typescript-From-DTOs/Jenkinsfile")
            }
        }
    }
    
}
