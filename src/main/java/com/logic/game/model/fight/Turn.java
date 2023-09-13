package com.logic.game.model.fight;

import com.logic.game.model.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс Turn представляет информацию о ходе в рамках раунда боя.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 * @AllArgsConstructor - аннотация Lombok, которая автоматически генерирует конструктор со всеми аргументами.
 */
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
