package com.logic.game.service;

import com.logic.game.model.FightTurn;
import com.logic.game.model.Fighter;

public class FightTurnCalculator {

    public static void getCalculations(FightTurn fightTurn) {
        Integer attack = getAttack(fightTurn.getAttacker().getMinAttack(), fightTurn.getAttacker().getMaxAttack());
        fightTurn.setAttack(attack);

        Integer evasion = getEvasion(fightTurn.getDefender().getMinEvasion(), fightTurn.getDefender().getMaxEvasion());
        fightTurn.setEvasion(evasion);

        Integer damageIgnore = getDamageIgnore(fightTurn.getDefender().getDamageIgnore());
        fightTurn.setDamageIgnore(damageIgnore);

        Integer damage = getDamage(fightTurn.getAttack(), fightTurn.getEvasion(), fightTurn.getDamageIgnore());
        fightTurn.setDamage(damage);

        Integer attackerHp = getAttackerHp(fightTurn.getAttacker().getHealthPoint());
        fightTurn.getAttacker().setHealthPoint(attackerHp);

        Integer defenderHp = getDefenderHp(fightTurn.getDefender().getHealthPoint(), fightTurn.getDamage());
        fightTurn.getDefender().setHealthPoint(defenderHp);

//        System.out.println(fightTurn.getAttacker().getName() + " атакует " + fightTurn.getDefender().getName() + " с силой " + attack + ". Но противник уклоняется на " + evasion + " и игнорирует " + damageIgnore + " урона, поэтому получает всего " + damage + " урона. " + fightTurn.getAttacker().getName() + " имеет " + attackerHp + " очков здоровья, а " + fightTurn.getDefender().getName() + " имеет " + defenderHp);
    }

    private static Integer getAttack(Integer minAttack, Integer maxAttack) {
        return minAttack + (int) (Math.random() * (maxAttack - minAttack + 1));
    }

    private static Integer getEvasion(Integer minEvasion, Integer maxEvasion) {
        return minEvasion + (int) (Math.random() * (maxEvasion - minEvasion + 1));
    }

    private static Integer getDamage(Integer attack, Integer evasion, Integer damageIgnore) {
        return Math.max(attack - evasion - damageIgnore, 0);
    }

    private static Integer getAttackerHp(Integer attackerHp) {
        return attackerHp;
    }

    private static Integer getDefenderHp(Integer defenderHp, Integer damage) {
        return defenderHp - damage;
    }

    private static Integer getDamageIgnore(Integer damageIgnore) {
        return (int) (Math.random() * damageIgnore);
    }
}
