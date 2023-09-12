package com.logic.game.service.fight;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fight.Round;
import com.logic.game.model.fighter.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class FightCalculator {
    private final RoundCalculator roundCalculator;
    private Fighter currentFighter1;
    private Fighter currentFighter2;
    private Fighter winner;
    private Fighter loser;

    @Autowired
    public FightCalculator(RoundCalculator roundCalculator) {
        this.roundCalculator = roundCalculator;
    }

    public Fight calculate(Fighter fighter1, Fighter fighter2) {
        this.currentFighter1 = fighter1;
        this.currentFighter2 = fighter2;
        Queue<Round> rounds = new LinkedList<>();
        Integer roundNum = 1;

        while (this.currentFighter1.getCurrentHp() > 0 &&
                this.currentFighter2.getCurrentHp() > 0) {
//            System.out.printf("\nРаунд %d\n", roundNum);
            Round round = roundCalculator.calculate(roundNum, this.currentFighter1, this.currentFighter2);
            rounds.add(round);

            updateFighters(round);
            getWinnerAndLoser();
            roundNum++;
        }
//        System.out.printf("Победитель %s\n", winner.getName());

        return new Fight(this.currentFighter1,
                this.currentFighter2,
                rounds,
                this.winner,
                this.loser);
    }

    private void updateFighters(Round round) {
        if (round.getAttacker().getIsAttacker().equals(this.currentFighter1.getIsAttacker())) {
            this.currentFighter1 = round.getAttacker();
            this.currentFighter2 = round.getDefender();
        } else {
            this.currentFighter1 = round.getDefender();
            this.currentFighter2 = round.getAttacker();
        }
    }

    private void getWinnerAndLoser() {
        if (this.currentFighter1.getCurrentHp() <= 0) {
            this.winner = this.currentFighter2;
            this.loser = this.currentFighter1;
        } else if (this.currentFighter2.getCurrentHp() <= 0) {
            this.winner = this.currentFighter1;
            this.loser = this.currentFighter2;
        }
    }
}