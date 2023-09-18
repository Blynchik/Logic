package com.logic.game.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Класс AppUser представляет сущность пользователя приложения.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 */
@Data
@AllArgsConstructor
public class AppUser {
    private Long id;
    private String username;
}

