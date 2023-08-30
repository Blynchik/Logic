package com.logic.game.model;

import com.logic.game.service.FighterAttributeCalculator;
import lombok.Data;


@Data
public class Fighter {
    private Integer fighterId;
    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;

    private Integer minAttack;
    private Integer maxAttack;
    private Integer minEvasion;
    private Integer maxEvasion;
    private Integer healthPoint;
    private Integer damageIgnore;

    public Fighter() {
    }

    public Fighter(Fighter fighter){
        this.fighterId = fighter.getFighterId();
        this.name = fighter.getName();
        this.strength = fighter.getStrength();
        this.dexterity = fighter.getDexterity();
        this.constitution = fighter.getDexterity();

        FighterAttributeCalculator.setAttributes(this);

        this.healthPoint = fighter.getHealthPoint();
    }

    public Fighter(String name, Integer strength,
                   Integer dexterity, Integer constitution){
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;

        FighterAttributeCalculator.setAttributes(this);
    }
}
