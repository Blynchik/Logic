package com.logic.game.service;

import com.logic.game.model.Fighter;

public class FighterAttributeCalculator {

    public static void setAttributes(Fighter fighter) {
        Integer minAttack = getMinAttack(fighter.getStrength());
        Integer maxAttack = getMaxAttack(fighter.getStrength());
        fighter.setMinAttack(minAttack);
        fighter.setMaxAttack(maxAttack);

        Integer minEvasion = getMinEvasion(fighter.getDexterity());
        Integer maxEvasion = getMaxEvasion(fighter.getDexterity());
        fighter.setMinEvasion(minEvasion);
        fighter.setMaxEvasion(maxEvasion);

        Integer hp = getHealthPoint(fighter.getConstitution());
        fighter.setHealthPoint(hp);
    }

    private static Integer getMinAttack(Integer strength) {
        return (int) (strength - Math.ceil(strength / 2.0));
    }

    private static Integer getMaxAttack(Integer strength) {
        return (int) (strength + Math.floor(strength / 2.0));
    }

    private static Integer getMinEvasion(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    private static Integer getMaxEvasion(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private static Integer getHealthPoint(Integer constitution) {
        return constitution;
    }
}
