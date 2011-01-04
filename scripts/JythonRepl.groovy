/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Eric Wendelin
 * @since 0.1
 */

import org.python.util.PythonInterpreter
import org.python.core.*

includeTargets << griffonScript("_GriffonPackage")
includePluginScript("jython", "_JythonCommon")

target(default: "Run Jython REPL") {
    depends(checkVersion, classpath)

    ant.fileset(dir: "${jythonPluginDir}/lib/", includes:"*.jar").each { jar ->
        classLoader.addURL(jar.file.toURI().toURL())
    }
    
    classLoader.parent.addURL(classesDir.toURI().toURL())
    classLoader.parent.addURL("file:${basedir}/griffon-app/resources/".toURL())
    classLoader.parent.addURL("file:${basedir}/griffon-app/i18n/".toURL())

	//TODO: setup python interpreter to allow up-arrow etc. May have to use JLine
    PythonInterpreter pi = new PythonInterpreter()
    pi.exec("from code import InteractiveConsole")
    pi.exec("InteractiveConsole.interact(InteractiveConsole(), '\\nWelcome to the Griffon - Jython REPL!\\nUse Ctrl+D to exit\\n')")
}