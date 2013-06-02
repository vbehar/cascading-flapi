# cascading-flapi

The goal of this project is to provide a fluent API for [Cascading](http://www.cascading.org/), generated using [Flapi](https://github.com/UnquietCode/Flapi).

[![Build Status](https://buildhive.cloudbees.com/job/vbehar/job/cascading-flapi/badge/icon)](https://buildhive.cloudbees.com/job/vbehar/job/cascading-flapi/)

## Usage

Fluent API for building a Cascading Pipe :

```java
Pipe pipe = PipeBuilder.start()
                       .each().select("data").filterOut().nullValues()
                       .each().insertField("processingDate").withValue(System.currentTimeMillis())
                       .each().select("url").applyFunction(new ExpressionFunction(new Fields("isSecureUrl"), "url.startsWith(\"https\")", String.class))
                       .retain("url", "domain", "isSecureUrl", "processingDate")
                       .groupBy().withSortOnFields("url").onFields("domain")
                       .count("domains")
                       .pipe();
```

See the [tests](https://github.com/vbehar/cascading-flapi/tree/master/src/test/java/cascading/flapi/pipe) for more.

### Using with Maven

The artifacts are available on the [conjars repository](http://conjars.org/) : http://conjars.org/cascading-flapi

## Build

This project use [Gradle](http://www.gradle.org/).

To build, just run `./gradlew` : it will download the right version of Gradle, and execute the "build" target.
The generated jars will be located in the `build/libs` directory.

### IDE support

To generate a project for your IDE, just run :
* `./gradlew eclipse` for Eclipse
* `./gradlew idea` for IntelliJ IDEA

### API code generation

Flapi does some Java code generation. If you want to change the Cascading Fluent-API DSL, you need to :
* edit the Generator class in `src/generator/java`
* execute the `generateApi` gradle task : `./gradlew generateApi`. It will regenerate the API code.
* refresh the project in your IDE and fix the compilation error(s) in the *Helper classes (the API implementation).

## License

Copyright 2013 the original author or authors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

