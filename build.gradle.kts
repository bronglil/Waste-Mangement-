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
	mavenCentral() // Ensure this is added
}

dependencies {
	// Spring Boot Web Starter
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Spring Data JPA Starter
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// H2 Database for Runtime
	runtimeOnly("com.h2database:h2")

	// Jakarta Validation API
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	implementation("org.glassfish:jakarta.el:4.0.2")

	// Lombok dependency
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // for JSON processing
	implementation("javax.servlet:javax.servlet-api:4.0.1")
	implementation("javax.servlet:javax.servlet-api:4.0.1")




	//swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
//	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
//	implementation("org.springdoc:springdoc-openapi-ui:1.7.0") // Use the latest version
//	implementation("org.springdoc:springdoc-openapi-kotlin:1.7.0")

	// Testing Dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation(kotlin("stdlib-jdk8"))

	//sending emails
	implementation ("org.springframework.boot:spring-boot-starter-mail")
	implementation ("jakarta.mail:jakarta.mail-api:2.1.0")

	implementation ("com.fasterxml.jackson.core:jackson-databind:2.14.0")
	implementation ("com.fasterxml.jackson.core:jackson-core:2.14.0")
	implementation ("com.fasterxml.jackson.core:jackson-annotations:2.14.0")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
