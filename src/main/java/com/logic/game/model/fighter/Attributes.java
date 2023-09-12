package com.logic.game.model.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attributes {
    private Integer minAttack;
    private Integer maxAttack;
    private Integer minEvasion;
    private Integer maxEvasion;
    private Integer minDamageIgnore;
    private Integer maxDamageIgnore;
    private Integer minInitiative;
    private Integer maxInitiative;
    private Integer minDamage;
    private Integer maxDamage;
    private Integer maxHp;
    private Integer xpAward;

    public Attributes(Attributes attributes){
        this.minAttack = attributes.getMinAttack();
        this.maxAttack = attributes.getMaxAttack();
        this.minEvasion = attributes.getMinEvasion();
        this.maxEvasion = attributes.getMaxEvasion();
        this.minDamageIgnore = attributes.getMinDamageIgnore();
        this.maxDamageIgnore = attributes.getMaxDamageIgnore();
        this.minInitiative = attributes.getMinInitiative();
        this.maxInitiative = attributes.getMaxInitiative();
        this.minDamage = attributes.getMinDamage();
        this.maxDamage = attributes.getMaxDamage();
        this.maxHp = attributes.getMaxHp();
        this.xpAward = attributes.getXpAward();
    }
}
