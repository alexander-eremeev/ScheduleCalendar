# ScheduleCalendar
Производственный календарь
## Static Analysis
This template is using [**detekt**](https://github.com/detekt/detekt) to analyze the source code, 
with the configuration that is stored in the [detekt.yml](gradle/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task). 
It also uses the **detekt-formatting** plugin which includes the ktlint rules (see https://detekt.dev/docs/rules/formatting/).

### Displays the dependency updates for the project.
```kotlin
./gradlew dependencyUpdates

## Static Analysis
This template is using [**detekt**](https://github.com/detekt/detekt) to analyze the source code, 
with the configuration that is stored in the [detekt.yml](gradle/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task). 
It also uses the **detekt-formatting** plugin which includes the ktlint rules (see https://detekt.dev/docs/rules/formatting/).

## Formatting

To automatically format all samples: Run `./scripts/format.sh`
To check one sample for errors: Navigate to the sample folder and run `./gradlew --init-script buildscripts/init.gradle.kts spotlessCheck`
To format one sample: Navigate to the sample folder and run `./gradlew --init-script buildscripts/init.gradle.kts spotlessApply`
