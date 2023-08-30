package com.logic.game.service;

import com.logic.game.model.FightRound;
import com.logic.game.model.FightTurn;
import com.logic.game.model.Fighter;

import java.util.LinkedList;
import java.util.Queue;

public class FightRoundCalculator {

    public static void getCalculations(FightRound fightRound, Fighter fighter1, Fighter fighter2) {
        fightRound.setPriority(getPriority(fighter1, fighter2));

        Fighter attacker  = fightRound.getPriority().poll();
        Fighter defender = fightRound.getPriority().poll();

//        System.out.println("Первым ходит " + attacker.getName() + ", a вторым - " + defender.getName());

        FightTurn fightTurn1 = new FightTurn(attacker, defender);
        fightRound.setAttacker(new Fighter(fightTurn1.getAttacker()));
        fightRound.setDefender(new Fighter(fightTurn1.getDefender()));
        fightRound.getTurns().add(fightTurn1);

        if (fightRound.getAttacker().getHealthPoint() > 0 && fightRound.getDefender().getHealthPoint() > 0) {
            FightTurn fightTurn2 = new FightTurn(fightRound.getDefender(), fightRound.getAttacker());
            fightRound.setDefender(new Fighter(fightTurn2.getAttacker()));
            fightRound.setAttacker(new Fighter(fightTurn2.getDefender()));
            fightRound.getTurns().add(fightTurn2);
        }


//        System.out.println("======================");
    }

    private static Queue<Fighter> getPriority(Fighter fighter1, Fighter fighter2) {
        Queue<Fighter> priority = new LinkedList<>();

        if (Math.random() < 0.5) {
            priority.add(fighter1);
            priority.add(fighter2);
        } else {
            priority.add(fighter2);
            priority.add(fighter1);
        }

        return priority;
    }
}
