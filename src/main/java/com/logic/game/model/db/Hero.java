package com.logic.game.model.db;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
