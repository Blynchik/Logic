package com.logic.game.service;

import com.logic.game.model.FightRound;
import com.logic.game.model.FightTurn;
import com.logic.game.model.Fighter;

import java.util.LinkedList;
import java.util.Queue;

public class FightRoundCalculator {

    public static void getCalculations(FightRound fightRound, Fighter fighter1, Fighter fighter2) {
        fightRound.setPriority(getPriority(fightRound, fighter1, fighter2));

        Fighter attacker = fightRound.getPriority().poll();
        Fighter defender = fightRound.getPriority().poll();

        System.out.println("Инициатива " + attacker.getName() + "- " + fightRound.getAttackerInitiative() + ", а у " + defender.getName() + "- " + fightRound.getDefenderInitiative());
        System.out.println("Первым ходит " + attacker.getName() + ", a вторым - " + defender.getName());

        FightTurn fightTurn1 = new FightTurn(attacker, defender);
        fightRound.setAttacker(new Fighter(fightTurn1.getAttacker()));
        fightRound.setDefender(new Fighter(fightTurn1.getDefender()));
        fightRound.getTurns().add(fightTurn1);

        if (fightRound.getAttacker().getHealthPoint() > 0 && fightRound.getDefender().getHealthPoint() > 0) {
            FightTurn fightTurn2 = new FightTurn(defender, attacker);
            fightRound.setDefender(new Fighter(fightTurn2.getAttacker()));
            fightRound.setAttacker(new Fighter(fightTurn2.getDefender()));
            fightRound.getTurns().add(fightTurn2);
        }
        System.out.println("======================");
    }

    private static Queue<Fighter> getPriority(FightRound fightRound, Fighter fighter1, Fighter fighter2) {
        Queue<Fighter> priority = new LinkedList<>();

        Integer fighter1Initiative = getInitiative(fighter1.getMinInitiative(), fighter1.getMaxInitiative());
        Integer fighter2Initiative = getInitiative(fighter2.getMinInitiative(), fighter2.getMaxInitiative());

        while (fighter1Initiative.equals(fighter2Initiative)) {
            fighter1Initiative = getInitiative(fighter1.getMinInitiative(), fighter1.getMaxInitiative());
            fighter2Initiative = getInitiative(fighter2.getMinInitiative(), fighter2.getMaxInitiative());
        }

        if (fighter1Initiative > fighter2Initiative) {
            priority.add(fighter1);
            priority.add(fighter2);
            fightRound.setAttackerInitiative(fighter1Initiative);
            fightRound.setDefenderInitiative(fighter2Initiative);
        } else {
            priority.add(fighter2);
            priority.add(fighter1);
            fightRound.setAttackerInitiative(fighter2Initiative);
            fightRound.setDefenderInitiative(fighter1Initiative);
        }

        return priority;
    }

    private static Integer getInitiative(Integer minInitiative, Integer maxInitiative) {
        return minInitiative + (int) (Math.random() * (maxInitiative - minInitiative + 1));
    }
}
