package com.logic.game.model.fight;

import com.logic.game.model.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Turn {
    private Fighter turnAttacker;
    private Fighter turnDefender;
    private Integer attack;
    private Integer evasion;
    private Integer damage;
    private Integer damageIgnore;
    private Integer realDamage;
}
