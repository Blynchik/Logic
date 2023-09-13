package com.logic.game.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс OpenApiConfig представляет конфигурацию для OpenAPI.
 * Используется аннотация @Configuration для определения этого класса как конфигурационного.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Метод api() создает и возвращает экземпляр класса GroupedOpenApi с определенными параметрами.
     * Используется аннотация @Bean для указания, что этот метод должен быть интерпретирован Spring как создание бина.
     *
     * @return экземпляр класса GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("Logic API")// Группа, к которой будет относиться API
                .pathsToMatch("/api/**")// Пути, для которых будет применяться данная конфигурация
                .build();
    }
}
