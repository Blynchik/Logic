package com.logic.game.model.fight;

import com.logic.game.model.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;

/**
 * Класс Round представляет информацию о раунде боя между двумя бойцами.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 * @AllArgsConstructor - аннотация Lombok, которая автоматически генерирует конструктор со всеми аргументами.
 */
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

