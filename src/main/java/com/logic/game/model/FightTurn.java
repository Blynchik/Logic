package com.logic.game.model;

import com.logic.game.service.FightTurnCalculator;
import lombok.Data;

@Data
public class FightTurn {
    private Fighter attacker;
    private Fighter defender;
    private Integer attack;
    private Integer evasion;
    private Integer damageIgnore;

    private Integer damage;

    public FightTurn(){}

    public FightTurn(Fighter attacker, Fighter defender){
        this.attacker = attacker;
        this.defender = defender;

        FightTurnCalculator.getCalculations(this);
    }
}
