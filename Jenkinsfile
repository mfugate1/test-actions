node ("built-in") {
  sh "env"
  
  pullRequest.createStatus(
    status: "success",
    context: "My Tests",
    description: "All Tests Passing",
    targetUrl: JOB_URL
    )
  
 pullRequest.comment("Things are looking good")
}
