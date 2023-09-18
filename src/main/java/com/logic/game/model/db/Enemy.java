package com.logic.game.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс Enemy представляет сущность врага.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 */
@Data
@AllArgsConstructor
public class Enemy {
    private Long id;
    private String name;
    private String description;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
}