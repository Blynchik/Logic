package com.logic.game.service;

import com.logic.game.model.Fight;
import com.logic.game.model.FightRound;
import com.logic.game.model.Fighter;

public class FightCalculator {

    public static void getCalculations(Fight fight) {
        int round = 1;
        fight.getFighter1().setFighterId(1);
        fight.getFighter2().setFighterId(2);

        while (fight.getFighter1().getHealthPoint() > 0 && fight.getFighter2().getHealthPoint() > 0) {
//            System.out.println("Раунд " + round);

            FightRound fightRound = new FightRound(round, fight.getFighter1(), fight.getFighter2());
            fight.getRounds().add(fightRound);

            Fighter attackerAfterRound = fightRound.getTurns().peek().getAttacker();
            Fighter defenderAfterRound = fightRound.getTurns().peek().getDefender();

            if(attackerAfterRound.getFighterId() == 1){
                fight.setFighter1(new Fighter(attackerAfterRound));
                fight.setFighter2(new Fighter(defenderAfterRound));
            } else if(attackerAfterRound.getFighterId() == 2){
                fight.setFighter2(new Fighter(attackerAfterRound));
                fight.setFighter1(new Fighter(defenderAfterRound));
            }

            Fighter winner = getWinner(fight.getFighter1(), fight.getFighter2());

            if (winner != null) {
                fight.setWinner(winner);
            }

            round++;
        }
//        System.out.println("Победил " + fight.getWinner().getName());
    }

    private static Fighter getWinner(Fighter fighter1, Fighter fighter2) {
        if (fighter1.getHealthPoint() <= 0) {
            return fighter2;
        } else if (fighter2.getHealthPoint() <= 0) {
            return fighter1;
        }
        return null;
    }
}
