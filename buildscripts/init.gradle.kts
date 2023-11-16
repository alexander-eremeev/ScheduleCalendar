import java.time.Year
/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

val ktlintVersion = "latest.release" 	//"0.46.1"

initscript {
    val spotlessVersion ="latest.release"	//"6.10.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

allprojects {
    if (this == rootProject) {
        return@allprojects
    }
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
//            targetExclude("**/build/**/*.kt")
            targetExclude("**/build/**/*.kt","**/src/androidTest/**/*.kt","**/src/test/**/*.kt")
            ktlint(ktlintVersion).editorConfigOverride(
                mapOf(
                    "ktlint_code_style" to "android",
                    "ij_kotlin_allow_trailing_comma" to true,
                    // These rules were introduced in ktlint 0.46.0 and should not be
                    // enabled without further discussion. They are disabled for now.
                    // See: https://github.com/pinterest/ktlint/releases/tag/0.46.0
                    "disabled_rules" to
                        "filename," +
                        "annotation,annotation-spacing," +
                        "argument-list-wrapping," +
                        "double-colon-spacing," +
                        "enum-entry-name-case," +
                        "multiline-if-else," +
                        "no-empty-first-line-in-method-block," +
                        "package-name," +
                        "trailing-comma," +
                        "spacing-around-angle-brackets," +
                        "spacing-between-declarations-with-annotations," +
                        "spacing-between-declarations-with-comments," +
                        "unary-op-spacing"
                )
            )
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            // Look for the first line that doesn't have a block comment (assumed to be the license)
            // Найдите первую строку без блочного комментария (предполагается, что это лицензия).
            licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
            // Найдите первый тег XML, который не является комментарием (<!--) или объявлением xml (<?xml)
            licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
        }
        protobuf {
            // by default the target is every '.proto' file in the project
            buf()
//            licenseHeader ("/* (C) $Year */") // or licenseHeaderFile
//            licenseHeader ("/* (C) $YEAR */") // or licenseHeaderFile
        }
        sql {
            target ("src/main/resources/**/*.sql") // have to set manually

            dbeaver()  // has its own section below
            prettier() // has its own section below
        }

        javascript {
            target ("src/**/*.js") // you have to set the target manually
            prettier() // has its own section below
//            eslint()   // has its own section below
            rome()     // has its own section below
//            licenseHeader ("/* (C) $YEAR */", "REGEX_TO_DEFINE_TOP_OF_FILE") // or licenseHeaderFile
//            licenseHeader ("/* (C) $YEAR */") // or licenseHeaderFile
//            licenseHeaderFile(rootProject.file("spotless/copyright.js"))

        }

        json {
            target ("src/**/*.json")
            simple()
            // optional: specify the number of spaces to use
//            simple().indentWithSpaces(6)
        }

        yaml {
            target( "src/**/*.yaml")                // you have to set the target manually
            jackson()                             // has its own section below
            prettier()                            // has its own section below
        }

        /*
        //---------------------------
        // Protobuf
		//com.diffplug.gradle.spotless.ProtobufExtension
		protobuf {
			// by default the target is every '.proto' file in the project
			buf()
			licenseHeader ("/* (C) $YEAR */") // or licenseHeaderFile
		}
        //---------------------------
        // Sql
		// 	com.diffplug.gradle.spotless.SqlExtension

		spotless {
		  sql {
			target ("src/main/resources/**/*.sql") // have to set manually

			dbeaver()  // has its own section below
			prettier() // has its own section below
		  }
		}
        //---------------------------
        // Javascript
		//com.diffplug.gradle.spotless.JavascriptExtension
		spotless {
		  javascript {
			target ("src/**/*.js") // you have to set the target manually
			prettier() // has its own section below
			eslint()   // has its own section below
			rome()     // has its own section below
			licenseHeader ("/* (C) $YEAR */", "REGEX_TO_DEFINE_TOP_OF_FILE") // or licenseHeaderFile
		  }
		}
        //---------------------------
        // Json
		//com.diffplug.gradle.spotless.JsonExtension
		spotless {
		  json {
			target("src/**/*.json")                // you have to set the target manually
			simple()                              // has its own section below
			prettier().config(["parser": "json"]) // see Prettier section below
			eclipseWtp("json")                    // see Eclipse web tools platform section
			gson()                                // has its own section below
			jackson()                             // has its own section below
			rome()                                // has its own section below
			jsonPatch([])                         // has its own section below
		  }
		}
		simple
		Uses a JSON pretty-printer that optionally allows configuring the number of spaces that are used to pretty print objects:

		spotless {
		  json {
			target ("src/**/*.json")
			simple()
			// optional: specify the number of spaces to use
			simple().indentWithSpaces(6)
		  }
		}
        //---------------------------
        // Yaml
		com.diffplug.gradle.spotless.YamlExtension javadoc, code
		spotless {
		  yaml {
			target( "src/**/*.yaml")                // you have to set the target manually
			jackson()                             // has its own section below
			prettier()                            // has its own section below
		  }
		}

*/
    }
}
