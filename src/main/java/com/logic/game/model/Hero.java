package com.logic.game.model;

import lombok.*;



@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Fighter {

    private String surname;
    private String nickname;

    public Hero() {
    }

    public Hero(String name, Integer strength,
                Integer dexterity, Integer constitution) {
        super(name, strength, dexterity, constitution);
    }

    public Hero(String name, String surname,
                String nickname, Integer strength,
                Integer dexterity, Integer constitution) {
        super(name, strength, dexterity, constitution);
        this.surname = surname;
        this.nickname = nickname;
    }
}
