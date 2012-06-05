package com.conmio.googleclosureresources

import com.google.javascript.jscomp.*
import org.grails.plugin.resource.mapper.MapperPhase

class GoogleClosureJsCompileResourceMapper {

    def phase = MapperPhase.COMPRESSION
    def grailsApplication

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
                /*
                 * refer to AbstractCommandLineRunner.doRun() for an example of how to use the compression classes
                 */
                def compiler = new Compiler()
                CompilerOptions options = getCompilerOptions()
                compiler.compile(SourceFile.fromFile(targetFile),
                                 SourceFile.fromFile(inputFile),
                                 options)
                writer.writeLine(compiler.toSource())
            }
            resource.processedFile = targetFile
            resource.updateActualUrlFromProcessedFile()
        } catch (Exception e) {
            log.error "Stopped minifying [${inputFile}]: ${e.message} Set log level to warn for more details."
            return false
        }
    }

    /*
     * refer to CommandLineRunner.createOptions() for a reference
     */
    private CompilerOptions getCompilerOptions() {
        def options = new CompilerOptions()
        CompilationLevel level = getCompilationLevel()
        level.setOptionsForCompilationLevel(options)
        return options
    }

    private CompilationLevel getCompilationLevel() {
        String level = grailsApplication.config.grails.google_closure_compiler.resources.compression_level ?: 'default_to_simple'
        switch (level) {
            case "white_space":
                return CompilationLevel.WHITESPACE_ONLY
                break
            case "advanced":
                return CompilationLevel.ADVANCED_OPTIMIZATIONS
                break
            default:
                return CompilationLevel.SIMPLE_OPTIMIZATIONS
        }
    }
}
