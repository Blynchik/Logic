package com.logic.game.service;

import org.springframework.stereotype.Component;

@Component
public class HpAndRealDamageCalculator {

    public Integer getRealDamage(Integer damage, Integer damageIgnore, Integer armor) {
        return Math.max(damage - armor - damageIgnore, 0);
    }

    public Integer getAttackerHp(Integer attackerHp) {
        return attackerHp;
    }

    public Integer getDefenderHp(Integer defenderHp, Integer realDamage) {
        return defenderHp - realDamage;
    }
}
