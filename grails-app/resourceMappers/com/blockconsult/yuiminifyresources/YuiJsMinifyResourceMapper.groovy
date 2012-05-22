package com.blockconsult.yuiminifyresources

import com.google.javascript.jscomp.*
import org.grails.plugin.resource.mapper.MapperPhase

class YuiJsMinifyResourceMapper {

  def phase = MapperPhase.COMPRESSION

  static defaultExcludes = ['**/*.min.js']
  static defaultIncludes = ['**/*.js']

  def map(resource, config) {

    if (config?.disable) {
      if (log.debugEnabled) log.debug "YUI JS Minifier disabled in Config.groovy"
      return false
    }

    File inputFile = resource?.processedFile
    File targetFile = Util.getTargetFile(resource, Util.jsFilePattern)
    if (!targetFile) return false

    try {
      String encoding = config?.charset ?: 'UTF-8'
      if (log.debugEnabled) log.debug "Minifying Javascript file [$inputFile] to [$targetFile]"
      targetFile.withWriter(encoding) { writer ->
        def compressor = new Compiler()
        compressor.compile(SourceFile.fromFile(targetFile),
                           SourceFile.fromFile(inputFile),
                           new CompilerOptions())
        writer.writeLine(compressor.toSource())
      }
      resource.processedFile = targetFile
      resource.updateActualUrlFromProcessedFile()
    } catch (Exception e) {
      log.error "Stopped minifying [${inputFile}]: ${e.message} Set log level to warn for more details."
      return false
    }
  }
}
