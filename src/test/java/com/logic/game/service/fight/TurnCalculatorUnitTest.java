package com.logic.game.service.fight;

import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageAndCurrentHpCalculator;
import com.logic.game.service.Throw;
import com.logic.game.service.fighter.FighterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TurnCalculatorUnitTest {
    Fighter attacker;
    Fighter defender;
    Turn successTurn;
    @InjectMocks
    TurnCalculator turnCalculator;
    @Mock
    RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;
    @Mock
    FighterService fighterService;
    @Mock
    Throw throwValue;

    @BeforeEach
    void setUp() {
        attacker = new Fighter("Test",
                new Characteristics(5, 6, 7),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                11);
        attacker.setIsAttacker(true);

        defender = new Fighter("Test",
                new Characteristics(6, 6, 6),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                10);
        defender.setIsAttacker(false);

        Fighter defenderAfterTurn = new Fighter(defender);
        defenderAfterTurn.setCurrentHp(defenderAfterTurn.getCurrentHp() - 1);

        successTurn = new Turn(attacker,
                defenderAfterTurn,
                6,
                5,
                6,
                5,
                1);
    }

    @Test
    @DisplayName("Расчет хода")
    public void calculate() {
        //given
        Turn expectedTurn = successTurn;
        Fighter expectedDefender  = new Fighter(defender);
        expectedDefender.setCurrentHp(defender.getCurrentHp() - expectedTurn.getRealDamage());

        when(throwValue.throwAttack(attacker.getAttributes().getMinAttack(), attacker.getAttributes().getMaxAttack()))
                .thenReturn(expectedTurn.getAttack());

        when(throwValue.throwEvasion(defender.getAttributes().getMinEvasion(), defender.getAttributes().getMaxEvasion()))
                .thenReturn(expectedTurn.getEvasion());

        when(throwValue.throwDamage(attacker.getAttributes().getMinDamage(), attacker.getAttributes().getMaxDamage()))
                .thenReturn(expectedTurn.getDamage());

        when(throwValue.throwDamageIgnore(defender.getAttributes().getMinDamageIgnore(), defender.getAttributes().getMaxDamageIgnore()))
                .thenReturn(expectedTurn.getDamageIgnore());

        when(realDamageAndCurrentHpCalculator.getRealDamage(expectedTurn.getDamage(), expectedTurn.getDamageIgnore()))
                .thenReturn(expectedTurn.getRealDamage());

        when(fighterService.getWithUpdatedHp(attacker, 0))
                .thenReturn(attacker);

        when(fighterService.getWithUpdatedHp(defender, expectedTurn.getRealDamage()))
                .thenReturn(expectedDefender);

        //when
        Turn actualTurn = turnCalculator.calculate(attacker, defender);

        //then
        assertEquals(expectedTurn, actualTurn);
        assertEquals(expectedTurn.getTurnDefender(), actualTurn.getTurnDefender());
    }

    @Test
    @DisplayName("Обновление HP")
    public void updateHp(){
        //given
        Turn expectedTurn = successTurn;
        Fighter expectedDefender  = new Fighter(defender);

        when(fighterService.getWithUpdatedHp(defender, expectedTurn.getRealDamage()))
                .thenReturn(expectedDefender);

        //when
        Fighter actualFighter = turnCalculator.updateHp(defender, 1);

        //then
        assertEquals(expectedDefender, actualFighter);
    }
}
