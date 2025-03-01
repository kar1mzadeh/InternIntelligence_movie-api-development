package com.zambeyzz.MovieAPIDevelopment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi moviesApi() {
        return GroupedOpenApi.builder()
                .group("movie-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie API")
                        .description("CRUD operations for movie database")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Rahman Karimov")
                                .email("rkarimov17969@ada.edu.az")));
    }
}
