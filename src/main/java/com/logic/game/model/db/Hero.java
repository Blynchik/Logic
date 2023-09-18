package com.logic.game.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс Hero представляет сущность героя.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 */
@Data
@AllArgsConstructor
public class Hero {
    private Long id;
    private String name;
    private String surname;
    private AppUser appUser;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer currentHp;
    private Long xp;
    private LocalDateTime createdAt;
}
