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
public class AttributeCalculatorUnitTest {
    Hero inputHero;
    Enemy inputEnemy;
    Fighter inputFighter;

    Characteristics inputCh;
    Attributes successAtt;
    @InjectMocks
    AttributeCalculator attributeCalculator;

    @BeforeEach
    void setUp() {
        inputHero = new Hero(1L,
                "Test",
                "Test",
                new AppUser(1L, "TestUser"),
                5, 5, 5,
                12,
                100L,
                LocalDateTime.now());

        inputEnemy = new Enemy(1L,
                "Test",
                "TestDescription",
                5, 5, 5);

        inputFighter = new Fighter("Test",
                new Characteristics(5, 5, 5),
                null,
                null,
                10);

        inputCh = new Characteristics(5, 5, 5);

        successAtt = new Attributes(2, 7, 2, 7, 2, 7, 2, 7, 2, 7, 10, 10);
    }

    @Test
    @DisplayName("Вычисление атрибутов героя")
    public void getAttributes_Hero() {
        //given
        Attributes expectedAtt = successAtt;

        //when
        Attributes actualAtt = attributeCalculator.getAttributes(inputHero);

        //then
        assertEquals(expectedAtt, actualAtt);
    }

    @Test
    @DisplayName("Вычисление атрибутов противника")
    public void getAttributes_Enemy() {
        //given
        Attributes expectedAtt = successAtt;

        //when
        Attributes actualAtt = attributeCalculator.getAttributes(inputEnemy);

        //then
        assertEquals(expectedAtt, actualAtt);
    }

    @Test
    @DisplayName("Вычисление атрибутов бойца")
    public void getAttributes_Fighter() {
        //given
        Attributes expectedAtt = successAtt;

        //when
        Attributes actualAtt = attributeCalculator.getAttributes(inputFighter);

        //then
        assertEquals(expectedAtt, actualAtt);
    }

    @Test
    @DisplayName("Вычисление атрибутов по характеристикам")
    public void getAttributes_Characteristics() {
        //given
        Attributes expectedAtt = successAtt;

        //when
        Attributes actualAtt = attributeCalculator.getAttributes(inputCh);

        //then
        assertEquals(expectedAtt, actualAtt);
    }

    @Test
    @DisplayName("Вычисление минимальной атаки")
    public void getMinAttack() {
        //given
        Integer expectedMinAttack = successAtt.getMinAttack();

        //when
        Integer actualMinAttack = attributeCalculator.getMinAttack(inputCh.getDexterity());

        //then
        assertEquals(expectedMinAttack, actualMinAttack);
    }

    @Test
    @DisplayName("Вычисление максимальной атаки")
    public void getMaxAttack() {
        //given
        Integer expectedMaxAttack = successAtt.getMaxAttack();

        //when
        Integer actualMaxAttack = attributeCalculator.getMaxAttack(inputCh.getStrength());

        //then
        assertEquals(expectedMaxAttack, actualMaxAttack);
    }

    @Test
    @DisplayName("Вычисление минимального уклонения")
    public void getMinEvasion() {
        //given
        Integer expectedMinEvasion = successAtt.getMinEvasion();

        //when
        Integer actualMinEvasion = attributeCalculator.getMinEvasion(inputCh.getDexterity());

        //then
        assertEquals(expectedMinEvasion, actualMinEvasion);
    }

    @Test
    @DisplayName("Вычисление максимального уклонения")
    public void getMaxEvasion() {
        //given
        Integer expectedMaxEvasion = successAtt.getMaxEvasion();

        //when
        Integer actualMaxEvasion = attributeCalculator.getMaxEvasion(inputCh.getDexterity());

        //then
        assertEquals(expectedMaxEvasion, actualMaxEvasion);
    }

    @Test
    @DisplayName("Вычисление минимального игнорирования урона")
    public void getMinDamageIgnore() {
        //given
        Integer expectedMinDamageIgnore = successAtt.getMinDamageIgnore();

        //when
        Integer actualMinDamageIgnore = attributeCalculator.getMinDamageIgnore(inputCh.getConstitution());

        //then
        assertEquals(expectedMinDamageIgnore, actualMinDamageIgnore);
    }

    @Test
    @DisplayName("Вычисление максимального игнорирования урона")
    public void getMaxDamageIgnore() {
        //given
        Integer expectedMaxDamageIgnore = successAtt.getMaxDamageIgnore();

        //when
        Integer actualMaxDamageIgnore = attributeCalculator.getMaxDamageIgnore(inputCh.getConstitution());

        //then
        assertEquals(expectedMaxDamageIgnore, actualMaxDamageIgnore);
    }

    @Test
    @DisplayName("Вычисление минимальной инициативы")
    public void getMinInitiative() {
        //given
        Integer expectedMinInitiative = successAtt.getMinInitiative();

        //when
        Integer actualMinInitiative = attributeCalculator.getMinInitiative(inputCh.getDexterity());

        //then
        assertEquals(expectedMinInitiative, actualMinInitiative);
    }

    @Test
    @DisplayName("Вычисление максимальной инициативы")
    public void getMaxInitiative() {
        //given
        Integer expectedMaxInitiative = successAtt.getMaxInitiative();

        //when
        Integer actualMaxInitiative = attributeCalculator.getMaxInitiative(inputCh.getDexterity());

        //then
        assertEquals(expectedMaxInitiative, actualMaxInitiative);
    }

    @Test
    @DisplayName("Вычисление минимального урона")
    public void getMinDamage() {
        //given
        Integer expectedMinDamage = successAtt.getMinDamage();

        //when
        Integer actualMinDamage = attributeCalculator.getMinDamage(inputCh.getStrength());

        //then
        assertEquals(expectedMinDamage, actualMinDamage);
    }

    @Test
    @DisplayName("Вычисление максимального урона")
    public void getMaxDamage() {
        //given
        Integer expectedMaxDamage = successAtt.getMaxDamage();

        //when
        Integer actualMaxDamage = attributeCalculator.getMaxDamage(inputCh.getStrength());

        //then
        assertEquals(expectedMaxDamage, actualMaxDamage);
    }

    @Test
    @DisplayName("Вычисление максимального HP")
    public void getMaxHp() {
        //given
        Integer expectedMaxHp = successAtt.getMaxHp();

        //when
        Integer actualMaxHp = attributeCalculator.getMaxHp(inputCh.getConstitution());

        //then
        assertEquals(expectedMaxHp, actualMaxHp);
    }

    @Test
    @DisplayName("Вычисление награды опытом")
    public void getXpAward() {
        //given
        Integer expectedXpAward = successAtt.getXpAward();

        //when
        Integer actualXpAward = attributeCalculator.getXpAward();

        //then
        assertEquals(expectedXpAward, actualXpAward);
    }
}
