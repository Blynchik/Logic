package com.logic.game.model.fight;

import com.logic.game.model.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;

@Data
@AllArgsConstructor
public class Round {
    private Integer roundNum;
    private Fighter attacker;
    private Fighter defender;
    private Integer attackerInitiative;
    private Integer defenderInitiative;
    private Queue<Turn> turns;
}
