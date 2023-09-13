package com.logic.game.model.db;

import lombok.Data;


/**
 * Класс AppUser представляет сущность пользователя приложения.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 */
@Data
public class AppUser {
    private Long id;
    private String username;
}

