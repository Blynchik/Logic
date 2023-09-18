package com.logic.game.service.fight;

import com.logic.game.model.fight.Round;
import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoundCalculatorUnitTest {
    Fighter fighter1;
    Fighter fighter2;
    Integer fighter1Initiative;
    Integer fighter2Initiative;
    Round successRound;
    Queue<Turn> turns;
    @InjectMocks
    RoundCalculator roundCalculator;
    @Mock
    TurnCalculator turnCalculator;
    @Mock
    Throw throwValue;

    @BeforeEach
    void setUp(){
        fighter1 = new Fighter("Test",
                new Characteristics(6, 6, 6),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                10);
        fighter1.setIsAttacker(false);

        fighter2 = new Fighter("Test",
                new Characteristics(5, 6, 7),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                11);
        fighter2.setIsAttacker(true);

        fighter1Initiative = 1;
        fighter2Initiative = 10;

        turns = new LinkedList<>();
        turns.add(new Turn(fighter2, fighter1, 5, 4, 5, 5, 0));
        turns.add(new Turn(fighter1, fighter2, 5, 2, 4,4,0));

        successRound = new Round(1,
                fighter2,
                fighter1,
                fighter2Initiative,
                fighter1Initiative,
                turns);

    }

    @Test
    @DisplayName("Расчет раунда")
    public void calculate() {
        //given
        Round expectedRound = successRound;

        when(turnCalculator.calculate(fighter2, fighter1))
                .thenReturn(new Turn(fighter2, fighter1, 5, 4, 5, 5, 0));

        when(turnCalculator.calculate(fighter1, fighter2))
                .thenReturn(new Turn(fighter1, fighter2, 5, 2, 4,4,0));

        when(throwValue.throwInitiative(fighter2.getAttributes().getMinInitiative(),
                fighter2.getAttributes().getMaxInitiative()))
                .thenReturn(1)
                .thenReturn(10);

        //when
        Round actualRound = roundCalculator.calculate(1, fighter1, fighter2);

        //then
        assertEquals(expectedRound, actualRound);
        assertEquals(turns, actualRound.getTurns());
        assertEquals(fighter2, actualRound.getAttacker());
        assertEquals(fighter1, actualRound.getDefender());
    }
}
