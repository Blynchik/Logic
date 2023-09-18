package com.logic.game.service.fighter;

import com.logic.game.model.db.AppUser;
import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageAndCurrentHpCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FighterCalculatorUnitTest {
    Hero heroForMapping;
    Enemy enemyForMapping;
    Fighter successFighterFromHero;
    Fighter successFighterFromEnemy;
    Characteristics successCh;
    Attributes successAtt;
    @Mock
    CharacteristicCalculator characteristicCalculator;
    @Mock
    AttributeCalculator attributeCalculator;
    @Mock
    RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;
    @InjectMocks
    FighterCalculator fighterCalculator;

    @BeforeEach
    void setUp() {
        heroForMapping = new Hero(1L,
                "Test",
                "Test",
                new AppUser(1L, "TestUser"),
                5,
                5,
                5,
                10,
                100L,
                LocalDateTime.now());

        enemyForMapping = new Enemy(1L,
                "Test",
                null,
                5,
                5,
                5);

        successCh = new Characteristics(heroForMapping.getStrength(), heroForMapping.getDexterity(), heroForMapping.getConstitution());

        successAtt = new Attributes(2, 7, 2, 7, 2, 7, 2, 7, 2, 7, 10, 10);

        successFighterFromHero = new Fighter(heroForMapping.getName(),
                heroForMapping.getSurname(),
                heroForMapping.getAppUser(),
                successCh,
                successAtt,
                heroForMapping.getCurrentHp());

        successFighterFromEnemy = new Fighter(enemyForMapping.getName(),
                successCh,
                successAtt,
                enemyForMapping.getDescription(),
                10);
    }

    @Test
    @DisplayName("Маппинг героя в бойца: успешно")
    public void map_Hero_Success() {
        Fighter expectedFighter = successFighterFromHero;
        Characteristics expectedCh = successCh;
        Attributes expectedAtt = successAtt;


        when(characteristicCalculator.getCharacteristics(heroForMapping))
                .thenReturn(expectedCh);

        when(attributeCalculator.getAttributes(heroForMapping))
                .thenReturn(expectedAtt);

        //when
        Fighter actualFighter = fighterCalculator.map(heroForMapping);

        //then
        assertEquals(expectedFighter, actualFighter);
        assertEquals(expectedCh, actualFighter.getCharacteristics());
        assertEquals(expectedAtt, actualFighter.getAttributes());
        verify(characteristicCalculator, times(1)).getCharacteristics(heroForMapping);
        verify(attributeCalculator, times(1)).getAttributes(heroForMapping);
    }

    @Test
    @DisplayName("Маппинг противника в бойца: успешно")
    public void map_Enemy_Success() {
        Fighter expectedFighter = successFighterFromEnemy;
        Characteristics expectedCh = successCh;
        Attributes expectedAtt = successAtt;

        when(characteristicCalculator.getCharacteristics(enemyForMapping))
                .thenReturn(expectedCh);

        when(attributeCalculator.getAttributes(enemyForMapping))
                .thenReturn(expectedAtt);

        //when
        Fighter actualFighter = fighterCalculator.map(enemyForMapping);

        //then
        assertEquals(expectedFighter, actualFighter);
        assertEquals(expectedCh, actualFighter.getCharacteristics());
        assertEquals(expectedAtt, actualFighter.getAttributes());
        verify(characteristicCalculator, times(1)).getCharacteristics(enemyForMapping);
        verify(attributeCalculator, times(1)).getAttributes(enemyForMapping);
    }

    @Test
    @DisplayName("Получение нового бойца по характеристикам: успешно")
    public void getWithNewCharacteristics_Success() {
        //given
        Fighter expectedFighter = new Fighter(successFighterFromEnemy);

        Characteristics expectedCh = new Characteristics(6, 6, 6);
        expectedFighter.setCharacteristics(expectedCh);

        Attributes expectedAtt = new Attributes(7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7);
        expectedFighter.setAttributes(expectedAtt);

        when(attributeCalculator.getAttributes(expectedCh))
                .thenReturn(expectedAtt);

        //when
        Fighter actualFighter = fighterCalculator.getWithNewCharacteristics(successFighterFromEnemy, expectedCh);

        //then
        assertEquals(expectedFighter, actualFighter);
        assertEquals(expectedCh, actualFighter.getCharacteristics());
        assertEquals(expectedAtt, actualFighter.getAttributes());
        verify(attributeCalculator, times(1)).getAttributes(expectedCh);
    }

    @Test
    @DisplayName("Получение нового героя с обновленным HP: успешное")
    public void getWithUpdatedHp_Success() {
        //given
        Integer realDamage = 5;
        Fighter expectedFighter = successFighterFromHero;
        Integer expectedHp = expectedFighter.getCurrentHp() - realDamage;
        expectedFighter.setCurrentHp(expectedHp);

        when(realDamageAndCurrentHpCalculator.getCurrentHp(expectedFighter.getCurrentHp(), realDamage))
                .thenReturn(expectedHp);

        //when
        Fighter actualFighter = fighterCalculator.getWithUpdatedHp(successFighterFromHero, realDamage);

        //then
        assertEquals(expectedFighter, actualFighter);
        assertEquals(expectedHp, actualFighter.getCurrentHp());
        verify(realDamageAndCurrentHpCalculator, times(1))
                .getCurrentHp(successFighterFromHero.getCurrentHp(), realDamage);
    }
}
