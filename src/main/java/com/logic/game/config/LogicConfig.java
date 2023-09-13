package com.logic.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Класс LogicConfig представляет конфигурацию для логики сражения.
 * Используется аннотация @Configuration для определения этого класса как конфигурационного.
 */
@Configuration
public class LogicConfig {

    /**
     * Метод restTemplate() создает и возвращает новый экземпляр класса RestTemplate.
     * Используется аннотация @Bean для указания, что этот метод должен быть интерпретирован Spring как создание бина.
     *
     * @return экземпляр класса RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
