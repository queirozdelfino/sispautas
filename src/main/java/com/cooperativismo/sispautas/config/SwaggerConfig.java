package com.cooperativismo.sispautas.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig {

    private String apiVersion = "v1";

    @Bean
    public OpenAPI openApiSpec() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SisPautas API")
                                .description("API gerenciamento de votos da cooperativa")
                                .version(this.apiVersion));
    }
}
