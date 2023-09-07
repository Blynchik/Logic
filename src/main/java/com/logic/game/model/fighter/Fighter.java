package com.logic.game.model.fighter;

import com.logic.game.model.db.AppUser;
import lombok.Data;


@Data
public class Fighter {
    private String name;
    private String surname;
    private AppUser appUser;
    private Characteristics characteristics;
    private Attributes attributes;
    private String description;
    private Boolean isAttacker;

    public Fighter(String name,
                   String surname,
                   AppUser appUser,
                   Characteristics characteristics,
                   Attributes attributes){
        this.name = name;
        this.surname = surname;
        this.appUser = appUser;
        this.characteristics = characteristics;
        this.attributes = attributes;
    }

    public Fighter(String name,
                   Characteristics characteristics,
                   Attributes attributes,
                   String description){
        this.name = name;
        this.characteristics = characteristics;
        this.attributes = attributes;
        this.description = description;
    }

    public Fighter(Fighter fighter){
        this.name = fighter.getName();
        this.surname = fighter.getSurname();
        this.appUser = fighter.getAppUser();
        this.characteristics = new Characteristics(fighter.getCharacteristics());
        this.attributes = new Attributes(fighter.getAttributes());
        this.description = fighter.getDescription();
        this.isAttacker = fighter.getIsAttacker();
    }
}
