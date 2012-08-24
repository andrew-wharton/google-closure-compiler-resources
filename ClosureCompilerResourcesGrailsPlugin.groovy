class ClosureCompilerResourcesGrailsPlugin {
  // the plugin version
  def version = "1.0"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.2 > *"
  // the other plugins this plugin depends on
  def dependsOn = [resources: '1.0 > *']
  def loadAfter = ['resources']
  def pluginExcludes = [
      "grails-app/views/**/*.*",
      "web-app/css/**/*.*",
      "web-app/js/**/*.*"
  ]

  def title = 'Google Closure Resources'
  def description = 'Passes js resources through the Google Closure Compiler'
  def documentation = 'http://grails.org/plugin/yui-minify-resources'

  def author = 'Conmio Ltd'
  def authorEmail = 'info@conmio.com'
  def license = '''
The plugin is licensed under Apache 2 license, see http://www.apache.org/licenses/LICENSE-2.0
'''
  def issueManagement = [ system: "github", url: "https://github.com/conmio/google-closure-compiler-resources/issues" ]

  def organization = [name: "Conmio Ltd", url: "http://www.conmio.com"]
  def scm = [url: "https://github.com/conmio/google-closure-compiler-resources"]

  def doWithWebDescriptor = {}
  def doWithSpring = {}
  def doWithDynamicMethods = {}
  def doWithApplicationContext = {}
  def onChange = {}
  def onConfigChange = {}
}
