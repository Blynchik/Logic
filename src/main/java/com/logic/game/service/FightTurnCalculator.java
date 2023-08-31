package com.logic.game.service;

import com.logic.game.model.FightTurn;

public class FightTurnCalculator {

    public static void getCalculations(FightTurn fightTurn) {
        Integer attack = getAttack(fightTurn.getAttacker().getMinAttack(), fightTurn.getAttacker().getMaxAttack());
        fightTurn.setAttack(attack);

        Integer evasion = getEvasion(fightTurn.getDefender().getMinEvasion(), fightTurn.getDefender().getMaxEvasion());
        fightTurn.setEvasion(evasion);

        if (fightTurn.getAttack() >= fightTurn.getEvasion()) {
//            System.out.println(fightTurn.getAttacker().getName() + " атакует " + fightTurn.getDefender().getName() + " и попадает.");

            Integer damage = getDamage(fightTurn.getAttacker().getMinDamage(), fightTurn.getAttacker().getMaxDamage());
            fightTurn.setDamage(damage);

            Integer damageIgnore = getDamageIgnore(fightTurn.getDefender().getMinDamageIgnore(), fightTurn.getDefender().getMaxDamageIgnore());
            fightTurn.setDamageIgnore(damageIgnore);

            Integer armor = getArmor(fightTurn.getDefender().getArmor());
            fightTurn.setArmor(armor);

            Integer realDamage = getRealDamage(fightTurn.getDamage(), fightTurn.getDamageIgnore(), fightTurn.getArmor());
            fightTurn.setRealDamage(realDamage);
        } else {
//            System.out.println(fightTurn.getAttacker().getName() + " атакует " + fightTurn.getDefender().getName() + " и не попадает.");

            fightTurn.setDamageIgnore(0);
            fightTurn.setDamage(0);
            fightTurn.setRealDamage(0);
        }

        Integer attackerHp = getAttackerHp(fightTurn.getAttacker().getHealthPoint());
        fightTurn.getAttacker().setHealthPoint(attackerHp);

        Integer defenderHp = getDefenderHp(fightTurn.getDefender().getHealthPoint(), fightTurn.getRealDamage());
        fightTurn.getDefender().setHealthPoint(defenderHp);

//        System.out.println(fightTurn.getAttacker().getName() + " атакует " + fightTurn.getDefender().getName() + " с силой " + fightTurn.getDamage());
//        System.out.println(fightTurn.getDefender().getName() + " игнорирует " + fightTurn.getDamageIgnore() + " блокирует " + fightTurn.getArmor());
//        System.out.println(fightTurn.getDefender().getName() + " получает " + fightTurn.getRealDamage());
//        System.out.println("У " + fightTurn.getAttacker().getName() + "- " + fightTurn.getAttacker().getHealthPoint() + " хп");
//        System.out.println("У " + fightTurn.getDefender().getName() + "- " + fightTurn.getDefender().getHealthPoint() + " хп");
//        System.out.println();
    }

    private static Integer getAttack(Integer minAttack, Integer maxAttack) {
        return minAttack + (int) (Math.random() * (maxAttack - minAttack + 1));
    }

    private static Integer getEvasion(Integer minEvasion, Integer maxEvasion) {
        return minEvasion + (int) (Math.random() * (maxEvasion - minEvasion + 1));
    }

    private static Integer getDamage(Integer minDamage, Integer maxDamage) {
        return minDamage + (int) (Math.random() * (maxDamage - minDamage + 1));
    }

    private static Integer getRealDamage(Integer damage, Integer damageIgnore, Integer armor) {
        return Math.max(damage - armor - damageIgnore, 0);
    }

    private static Integer getAttackerHp(Integer attackerHp) {
        return attackerHp;
    }

    private static Integer getDefenderHp(Integer defenderHp, Integer realDamage) {
        return defenderHp - realDamage;
    }

    private static Integer getDamageIgnore(Integer minDamageIgnore, Integer maxDamageIgnore) {
        return minDamageIgnore + (int) (Math.random() * (maxDamageIgnore - minDamageIgnore + 1));
    }

    private static Integer getArmor(Integer armor){
        return armor;
    }
}
