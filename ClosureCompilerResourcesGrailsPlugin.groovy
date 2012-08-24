class ClosureCompilerResourcesGrailsPlugin {
  // the plugin version
  def version = "1.0"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.3 > *"
  // the other plugins this plugin depends on
  def dependsOn = [resources: '1.0 > *']
  def loadAfter = ['resources']
  def pluginExcludes = [
      "grails-app/views/**/*.*",
      "web-app/css/**/*.*",
      "web-app/js/**/*.*"
  ]

  def title = 'Google Closure Resources'
  def description = 'Compresses JavaScript resources using Google Closure Compiler'
  def documentation = 'https://github.com/conmio/google-closure-compiler-resources'

  def author = 'Conmio Ltd'
  def authorEmail = 'info@conmio.com'
  def license = 'APACHE'
  def issueManagement = [ system: "github", url: "https://github.com/conmio/google-closure-compiler-resources/issues" ]

  def organization = [name: "Conmio Ltd", url: "http://www.conmio.com"]
  def scm = [url: "https://github.com/conmio/google-closure-compiler-resources"]

}
