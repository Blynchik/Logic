package com.logic.game.service;

import com.logic.game.model.Fighter;

public class FighterAttributeCalculator {

    public static void setAttributes(Fighter fighter) {
        Integer minAttack = getMinAttack(fighter.getDexterity());
        Integer maxAttack = getMaxAttack(fighter.getDexterity());
        fighter.setMinAttack(minAttack);
        fighter.setMaxAttack(maxAttack);

        Integer minEvasion = getMinEvasion(fighter.getDexterity());
        Integer maxEvasion = getMaxEvasion(fighter.getDexterity());
        fighter.setMinEvasion(minEvasion);
        fighter.setMaxEvasion(maxEvasion);

        Integer hp = getHealthPoint(fighter.getConstitution());
        fighter.setHealthPoint(hp);

        Integer minDamageIgnore = getMinDamageIgnore(fighter.getConstitution());
        Integer maxDamageIgnore = getMaxDamageIgnore(fighter.getConstitution());
        fighter.setMinDamageIgnore(minDamageIgnore);
        fighter.setMaxDamageIgnore(maxDamageIgnore);

        Integer minInitiative = getMinInitiative(fighter.getDexterity());
        Integer maxInitiative = getMaxInitiative(fighter.getDexterity());
        fighter.setMinInitiative(minInitiative);
        fighter.setMaxInitiative(maxInitiative);

        Integer minDamage = getMinDamage(fighter.getStrength());
        Integer maxDamage = getMaxDamage(fighter.getStrength());
        fighter.setMinDamage(minDamage);
        fighter.setMaxDamage(maxDamage);

        Integer armor = getArmor(fighter.getArmor());
        fighter.setArmor(armor);
    }

    private static Integer getMinAttack(Integer dexterity) {
        return 0;
    }

    private static Integer getMaxAttack(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private static Integer getMinEvasion(Integer dexterity) {
        return 0;
    }

    private static Integer getMaxEvasion(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private static Integer getHealthPoint(Integer constitution) {
        return 2 * constitution;
    }

    private static Integer getMinDamageIgnore(Integer constitution) {
        return (int) (constitution - Math.ceil(constitution / 2.0));
    }

    private static Integer getMaxDamageIgnore(Integer constitution) {
        return (int) (constitution + Math.floor(constitution / 2.0));
    }

    private static Integer getMinInitiative(Integer dexterity) {
        return 0;
    }

    private static Integer getMaxInitiative(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private static Integer getMinDamage(Integer strength) {
        return (int) (strength - Math.ceil(strength / 2.0));
    }

    private static Integer getMaxDamage(Integer strength) {
        return (int) (strength + Math.floor(strength / 2.0));
    }

    private static Integer getArmor(Integer armor) {
        return armor;
    }

//    private static Integer getConstitutionBonus(Integer constitution) {
//        return (int) (constitution - Math.floor(constitution / 2.0));
//    }
//
//    private static Integer getStrengthBonus(Integer strength) {
//        return (int) (strength - Math.floor(strength / 2.0));
//    }
//
//    private static Integer getDexterityBonus(Integer dexterity) {
//        return (int) (dexterity - Math.floor(dexterity / 2.0));
//    }
}
