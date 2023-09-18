package com.logic.game.service.fight;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fight.Round;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FightCalculatorUnitTest {
    Fighter fighter1;
    Fighter fighter2;
    Fighter finalFighter2;
    Fight successFight;
    @InjectMocks
    FightCalculator fightCalculator;
    @Mock
    RoundCalculator roundCalculator;

    @BeforeEach
    void setUp() {
        fighter1 = new Fighter("Test",
                new Characteristics(6, 6, 6),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                10);
        fighter1.setIsAttacker(true);

        fighter2 = new Fighter("Test",
                new Characteristics(5, 6, 7),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                11);
        fighter2.setIsAttacker(false);

        finalFighter2 = new Fighter(fighter2);
        finalFighter2.setCurrentHp(0);
        finalFighter2.setIsAttacker(false);

        Queue<Round> rounds = new LinkedList<>();
        rounds.add(new Round(1, fighter1, fighter2, 4, 5, new LinkedList<>()));
        rounds.add(new Round(2, finalFighter2, fighter1, 5, 9, new LinkedList<>()));

        successFight = new Fight(fighter1,
                finalFighter2,
                rounds,
                null,
                null);
    }

    @Test
    @DisplayName("Расчет сражения")
    public void calculate() {
        //given
        Fight expectedFight = successFight;
        expectedFight.setWinner(fighter1);
        expectedFight.setLoser(finalFighter2);

        when(roundCalculator.calculate(1, fighter1, fighter2))
                .thenReturn(new Round(1, fighter1, fighter2, 4, 5, new LinkedList<>()));

        when(roundCalculator.calculate(2, fighter1, fighter2))
                .thenReturn(new Round(2, finalFighter2, fighter1, 5, 9, new LinkedList<>()));

        //when
        Fight actualFight = fightCalculator.calculate(fighter1, fighter2);

        //then
        assertEquals(expectedFight, actualFight);
        assertEquals(expectedFight.getRounds().size(), actualFight.getRounds().size());
        assertEquals(expectedFight.getWinner(), actualFight.getWinner());
        assertEquals(expectedFight.getLoser(), actualFight.getLoser());
        verify(roundCalculator, times(1)).calculate(1, fighter1, fighter2);
        verify(roundCalculator, times(1)).calculate(2, fighter1, fighter2);
        verify(roundCalculator, times(0)).calculate(3, fighter1, fighter2);
    }

    @Test
    @DisplayName("Обновление бойцов после раунда")
    public void updateFighters() {
        //given
        Fighter expectedFighter1 = fighter1;
        Fighter expectedFighter2 = finalFighter2;

        when(roundCalculator.calculate(1, fighter1, fighter2))
                .thenReturn(new Round(1, fighter1, fighter2, 4, 5, new LinkedList<>()));

        when(roundCalculator.calculate(2, fighter1, fighter2))
                .thenReturn(new Round(2, finalFighter2, fighter1, 5, 9, new LinkedList<>()));

        //when
        Fight actualFight = fightCalculator.calculate(fighter1, fighter2);

        //then
        assertEquals(expectedFighter1, actualFight.getFighter1());
        assertEquals(expectedFighter2, actualFight.getFighter2());
    }

    @Test
    @DisplayName("Определение победителя при <=0")
    public void getWinnerAndLoser_0(){
        //given
        Fighter expectedFighter1 = fighter1;
        Fighter expectedFighter2 = finalFighter2;

        when(roundCalculator.calculate(1, fighter1, fighter2))
                .thenReturn(new Round(1, fighter1, fighter2, 4, 5, new LinkedList<>()));

        when(roundCalculator.calculate(2, fighter1, fighter2))
                .thenReturn(new Round(2, finalFighter2, fighter1, 5, 9, new LinkedList<>()));

        //when
        Fight actualFight = fightCalculator.calculate(fighter1, fighter2);

        //then
        assertEquals(expectedFighter1, actualFight.getWinner());
        assertEquals(expectedFighter2, actualFight.getLoser());
    }
}
