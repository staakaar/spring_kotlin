import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("com.arenagod.gradle.MybatisGenerator") version "1.4"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1")
	implementation("mysql:mysql-connector-java:8.0.23")
	mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

mybatisGenerator {
	verbose = true
	configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
}