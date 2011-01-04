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
 */

includeTargets << griffonScript("_GriffonInit")

if(!(config.flatten().'griffon.jython.dynamicPropertyName')) {
    configFile.append('''
griffon.jython.dynamicPropertyName = 'py'
''')
}
if(!(config.flatten().'griffon.jython.injectInto')) {
    configFile.append('''
griffon.jython.injectInto = ['controller']
''')
}

ant.mkdir(dir: "${basedir}/src/jython")
ant.mkdir(dir: "${basedir}/griffon-app/resources/jython")

// check to see if we already have a JythonGriffonAddon
boolean addonIsSet1
builderConfig.each() { prefix, v ->
    v.each { builder, views ->
        addonIsSet1 = addonIsSet1 || 'JythonGriffonAddon' == builder
    }
}

if (!addonIsSet1) {
    println 'Adding JythonGriffonAddon to Builder.groovy'
    builderConfigFile.append('''
root.'JythonGriffonAddon'.addon=true
''')
}