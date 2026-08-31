plugins {
    java
    war
    id("org.springframework.boot") version "4.1.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.inkblogdb.ddd"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /** Servlet */
    // Source: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web")
    /** DB */
    // Source: https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:4.1.0")
    /** Flyway */
    // Source: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-flyway
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    // Source: https://mvnrepository.com/artifact/org.flywaydb/flyway-mysql
    implementation("org.flywaydb:flyway-mysql")
    // Source: https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    runtimeOnly("com.mysql:mysql-connector-j")
    /** h2 */
    // Source: https://mvnrepository.com/artifact/com.h2database/h2
    implementation("com.h2database:h2")
    /** Lombok */
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    /** Test */
    // Source: https://mvnrepository.com/artifact/org.junit/junit-bom
    implementation(platform("org.junit:junit-bom:6.1.3"))
    // Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    // Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    // Source: https://mvnrepository.com/artifact/org.junit.platform/junit-platform-launcher
    testImplementation("org.junit.platform:junit-platform-launcher")
    // Source: https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
    testImplementation("org.mockito:mockito-junit-jupiter:5.23.0")
    // Source: https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:5.23.0")
}

tasks.test {
    useJUnitPlatform()
}
