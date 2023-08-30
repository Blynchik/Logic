package com.logic.game.model;

import com.logic.game.service.FightCalculator;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class Fight {
    private Fighter fighter1;
    private Fighter fighter2;
    private Queue<FightRound> rounds;
    private Fighter winner;

    public Fight(){
    }

    public Fight(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        rounds = new LinkedList<>();

        FightCalculator.getCalculations(this);
    }
}
