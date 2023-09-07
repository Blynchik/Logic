package com.logic.game.model.db;

import lombok.Data;

@Data
public class Enemy {
    private Long id;
    private String name;
    private String description;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
}
