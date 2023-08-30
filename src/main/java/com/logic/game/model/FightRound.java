package com.logic.game.model;

import com.logic.game.service.FightRoundCalculator;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class FightRound {

    private Integer roundNum;
    private Queue<FightTurn> turns;

    private Fighter attacker;
    private Fighter defender;
    private Queue<Fighter> priority;

    public FightRound(){}

    public FightRound(Integer roundNum, Fighter fighter1, Fighter fighter2){
        turns = new LinkedList<>();
        this.roundNum = roundNum;

        FightRoundCalculator.getCalculations(this, fighter1, fighter2);
    }
}
