Closure-compiler-resources
--------------------------

This plugin integrates with Grails' [resources framework](https://github.com/grails-plugins/grails-resources) to automatically
compress javascript using the Google Closure [compiler]
(https://developers.google.com/closure/compiler/).

The current version supports minification and compilation of JavaScript files as part of static resources processing.
Other features (such as Linter or CSS processing) of Google Closure Tools are not supported.

Settings
--------

* *grails.resources.mappers.closurecompiler.compilationlevel*: Controls the [compilation level](https://developers.google.com/closure/compiler/docs/compilation_levels) of the
compressor. Defaults to _SIMPLE_OPTIMIZATIONS_. This setting is passed as a string.

* *grails.resources.googleclosurejscompile.includes* and *.excludes*: Controls which files are processed by the compiler. The default setting is to include _*.js_, and to exclude _*.min.js*_.

Acknowledgements
----------------

This plugin is based on the [YUI minify resources](https://github.com/cblock/yui-minify-resources) plugin and 
[Google Closure Compiler](https://developers.google.com/closure/compiler/).


License
-------
This plugin is licensed under Apache License, Version 2.0.
