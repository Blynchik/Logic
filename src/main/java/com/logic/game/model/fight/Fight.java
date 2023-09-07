package com.logic.game.model.fight;

import com.logic.game.model.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;

@Data
@AllArgsConstructor
public class Fight {
    private Fighter fighter1;
    private Fighter fighter2;
    private Queue<Round> rounds;
    private Fighter winner;
    private Fighter loser;
}
