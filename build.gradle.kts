plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("jvm")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Web Starter
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Spring Data JPA Starter
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")

	// Jakarta Validation API
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	implementation("org.glassfish:jakarta.el:4.0.2")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // for JSON processing
	implementation("javax.servlet:javax.servlet-api:4.0.1")
	implementation("javax.servlet:javax.servlet-api:4.0.1")


	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Kotlin
	implementation(kotlin("stdlib-jdk8"))

	// Sending Emails
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("jakarta.mail:jakarta.mail-api:2.1.0")

	implementation ("com.fasterxml.jackson.core:jackson-databind:2.14.0") // Or latest version
	implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0") // Or latest version

}

configurations.all {
	resolutionStrategy {
		force ("com.fasterxml.jackson.core:jackson-databind:2.14.0")  // or the version you want
		force ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")  // or the version you want
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
