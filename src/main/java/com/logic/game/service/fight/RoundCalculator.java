package com.logic.game.service.fight;

import com.logic.game.model.fight.Round;
import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class RoundCalculator {

    private final TurnCalculator turnCalculator;
    private final Throw throwValue;
    private Fighter attacker;
    private Fighter defender;
    private Integer attackerInitiative;
    private Integer defenderInitiative;

    @Autowired
    public RoundCalculator(TurnCalculator turnCalculator,
                           Throw throwValue) {
        this.turnCalculator = turnCalculator;
        this.throwValue = throwValue;
    }

    public Round calculate(Integer roundNum, Fighter fighter1, Fighter fighter2) {
        this.attacker = null;
        this.defender = null;
        Queue<Turn> turns = new LinkedList<>();

        throwInitiative(fighter1, fighter2);

        Turn turn1 = turnCalculator.calculate(this.attacker, this.defender);
        turns.add(turn1);
        firstTurnUpdate(turn1);

        if (this.attacker.getAttributes().getCurrentHp() > 0 &&
                this.defender.getAttributes().getCurrentHp() > 0) {
            Turn turn2 = this.turnCalculator.calculate(this.defender, this.attacker);
            turns.add(turn2);
            secondTurnUpdate(turn2);
        }

        return new Round(roundNum,
                this.attacker,
                this.defender,
                this.attackerInitiative,
                this.defenderInitiative,
                turns);
    }

    private void throwInitiative(Fighter fighter1, Fighter fighter2) {
        do {
            Integer fighter1Initiative = throwValue.throwInitiative(fighter1.getAttributes().getMinInitiative(),
                    fighter1.getAttributes().getMaxInitiative());

            Integer fighter2Initiative = throwValue.throwInitiative(fighter2.getAttributes().getMinInitiative(),
                    fighter2.getAttributes().getMaxInitiative());

            if (fighter1Initiative > fighter2Initiative) {
                this.attackerInitiative = fighter1Initiative;
                this.defenderInitiative = fighter2Initiative;
                this.attacker = fighter1;
                this.defender = fighter2;
            } else {
                this.attackerInitiative = fighter2Initiative;
                this.defenderInitiative = fighter1Initiative;
                this.attacker = fighter2;
                this.defender = fighter1;
            }
            attacker.setIsAttacker(true);
            defender.setIsAttacker(false);
//                    System.out.printf("Атакующий %s, защищающийся %s\n", attacker.getName(), defender.getName());
        } while (this.attackerInitiative.equals(this.defenderInitiative));
//        System.out.printf("Инициатива %s - %d, а %s - %d\n", fighter1.getName(), attackerInitiative, fighter2.getName(), defenderInitiative);
    }

    private void firstTurnUpdate(Turn turn) {
        this.attacker = turn.getTurnAttacker();
        this.defender = turn.getTurnDefender();
    }

    private void secondTurnUpdate(Turn turn) {
        this.attacker = turn.getTurnDefender();
        this.defender = turn.getTurnAttacker();
    }
}
