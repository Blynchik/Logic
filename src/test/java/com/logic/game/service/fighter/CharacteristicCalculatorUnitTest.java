package com.logic.game.service.fighter;

import com.logic.game.model.db.AppUser;
import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CharacteristicCalculatorUnitTest {
    Enemy inputEnemy;
    Hero inputHero;
    Fighter inputFighter;
    Characteristics successCh;

    @InjectMocks
    CharacteristicCalculator characteristicCalculator;

    @BeforeEach
    void setUp() {
        inputEnemy = new Enemy(1L,
                "Test",
                "TestDescription",
                6, 6, 6);

        inputHero = new Hero(1L,
                "Test",
                "Test",
                new AppUser(1L, "TestUser"),
                6, 6, 6,
                12,
                100L,
                LocalDateTime.now());

        inputFighter = new Fighter("Test",
                new Characteristics(6, 6, 6),
                new Attributes(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
                null,
                10);

        successCh = new Characteristics(inputEnemy.getStrength(),
                inputEnemy.getDexterity(),
                inputEnemy.getConstitution());
    }

    @Test
    @DisplayName("Вычисление харатеристик противника: успешное")
    public void getCharacteristics_FromEnemy() {
        //given
        Characteristics expectedCh = successCh;

        //when
        Characteristics actualCh = characteristicCalculator.getCharacteristics(inputEnemy);

        //then
        assertEquals(expectedCh, actualCh);
    }

    @Test
    @DisplayName("Вычисление характеристик героя")
    public void getCharacteristics_FromHero() {
        //given
        Characteristics expectedCh = successCh;

        //when
        Characteristics actualCh = characteristicCalculator.getCharacteristics(inputHero);

        //then
        assertEquals(expectedCh, actualCh);
    }

    @Test
    @DisplayName("Вычисление харакетристик бойца")
    public void getCharacteristics_FromFighter() {
        //given
        Characteristics expectedCh = successCh;

        //when
        Characteristics actualCh = characteristicCalculator.getCharacteristics(inputFighter);

        //then
        assertEquals(expectedCh, actualCh);
    }

    @Test
    @DisplayName("Вычисление силы")
    public void getStrength() {
        //given
        Integer expectedStrength = successCh.getStrength();

        //when
        Integer actualStrength = characteristicCalculator.getStrength(successCh.getStrength());

        //then
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    @DisplayName("Вычисление ловкости")
    public void getDexterity(){
        //given
        Integer expectedDexterity = successCh.getDexterity();

        //when
        Integer actualDexterity = characteristicCalculator.getDexterity(successCh.getDexterity());

        //then
        assertEquals(expectedDexterity, actualDexterity);
    }

    @Test
    @DisplayName("Вычисление выносливости")
    public void getConstitution(){
        //given
        Integer expectedConstitution = successCh.getConstitution();

        //when
        Integer actualConstitution = characteristicCalculator.getConstitution(successCh.getConstitution());

        //then
        assertEquals(expectedConstitution, actualConstitution);
    }
}
