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

    private Integer minDamageIgnore;
    private Integer maxDamageIgnore;

    private Integer minInitiative;
    private Integer maxInitiative;

    private Integer minDamage;
    private Integer maxDamage;

    private Integer armor;

    public Fighter() {
    }

    public Fighter(Fighter fighter){
        this.fighterId = fighter.getFighterId();
        this.name = fighter.getName();
        this.strength = fighter.getStrength();
        this.dexterity = fighter.getDexterity();
        this.constitution = fighter.getDexterity();
        this.armor = fighter.getArmor();

        FighterAttributeCalculator.setAttributes(this);

        this.healthPoint = fighter.getHealthPoint();
    }

    public Fighter(String name, Integer strength,
                   Integer dexterity, Integer constitution){
        this.name = name;

        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;

        this.armor = 0;

        FighterAttributeCalculator.setAttributes(this);
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
        FighterAttributeCalculator.setAttributes(this);
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
        FighterAttributeCalculator.setAttributes(this);
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
        FighterAttributeCalculator.setAttributes(this);
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
        this.setMaxEvasion(this.getMaxEvasion() - armor);
    }
}
