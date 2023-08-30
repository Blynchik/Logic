package com.logic.game.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Enemy extends Fighter {

    public Enemy() {
    }

    public Enemy(String name, Integer strength,
                 Integer dexterity, Integer constitution) {
        super(name, strength, dexterity, constitution);
    }
}
