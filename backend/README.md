# Dropwizard + Gradle

Minimal example of getting Dropwizard going with Gradle (instead of Maven).

## Eclipse, IntelliJ IDEA

To create Eclipse project files:

	./gradlew eclipse
	
To create IntelliJ project files:

	./gradlew idea
 
## Gradle Tasks

To list the available tasks:

	./gradlew tasks

## OneJar

This example is using the [Gradle OneJar Plugin](https://github.com/rholder/gradle-one-jar) which will create
a JAR file of the project including all dependencies, similar to the [Maven Assembly Plugin](http://maven.apache.org/plugins/maven-assembly-plugin/)
or the [Maven Shade Plugin](http://maven.apache.org/plugins/maven-shade-plugin/).

To create a JAR with all dependencies just run `./gradlew oneJar`. The resulting JAR will be saved as `./build/libs/dropwizard-gradle-standalone.jar`.

You can simply run the application with `java -jar build/libs/dropwizard-gradle-standalone.jar server src/dist/config/helloworld.yml`.

## Gradle Application Plugin

An alternative to creating a fat JAR is using the [Gradle Application Plugin](http://www.gradle.org/docs/current/userguide/application_plugin.html).

To create a distributable ZIP archive including all dependencies for your application just run `./gradlew distZip`. The
resulting archive will be saved as `./build/distributions/dropwizard-gradle.zip`.

## Running and Testing Application

Start the application

	./gradlew run

Testing HelloWorld Resource

	curl http://localhost:8080/hello-world
	
Testing Admin Functions

	curl http://localhost:8081
	curl http://localhost:8081/ping
	curl http://localhost:8081/healthcheck
	curl http://localhost:8081/metrics

