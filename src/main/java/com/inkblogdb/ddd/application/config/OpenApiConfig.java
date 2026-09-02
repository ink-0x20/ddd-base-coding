package com.inkblogdb.ddd.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "DDDベースコーディング用のダミーAPI",
        description = "Spring Bootから自動生成されたAPIドキュメントです",
        version = "1.0.0"
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/api/v1",
            description = "開発環境"
        )
    }
)
public class OpenApiConfig {

}
