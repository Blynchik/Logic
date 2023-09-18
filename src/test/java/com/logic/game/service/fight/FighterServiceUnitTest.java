package com.logic.game.service.fight;

import com.logic.game.model.fight.Fight;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FighterServiceUnitTest {
    Fighter fighter1;
    Fighter fighter2;
    Fight successFight;
    @Mock
    FightCalculator fightCalculator;
    @InjectMocks
    FightService fightService;

    @BeforeEach
    void setUp() {
        fighter1 = new Fighter("Test",
                new Characteristics(6, 6, 6),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                10);

        fighter2 = new Fighter("Test",
                new Characteristics(5, 6, 7),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                11);

        successFight = new Fight(fighter1,
                fighter2,
                new LinkedList<>(),
                fighter1,
                fighter2);
    }

    @Test
    @DisplayName("Начало сражения")
    public void start() {
        //given
        Fight expectedFight = successFight;

        when(fightCalculator.calculate(fighter1, fighter2))
                .thenReturn(expectedFight);

        //when
        Fight actualFight = fightService.start(fighter1, fighter2);

        //then
        assertEquals(expectedFight, actualFight);
        assertEquals(fighter1, actualFight.getFighter1());
        assertEquals(fighter2, actualFight.getFighter2());
        verify(fightCalculator, times(1)).calculate(fighter1, fighter2);
    }
}
