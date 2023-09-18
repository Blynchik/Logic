package com.logic.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RealDamageAndCurrentHpCalculatorUnitTest {
    Integer hpBefore;
    Integer realDamage;
    Integer hpAfter;
    Integer damage;
    Integer damageIgnore;
    @InjectMocks
    RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;

    @BeforeEach
    void setUp() {
        hpBefore = 10;
        damage = 9;
        damageIgnore = 2;
        realDamage = damage - damageIgnore;
        hpAfter = hpBefore - realDamage;
    }

    @Test
    @DisplayName("Вычисление HP после удара")
    public void getCurrentHp() {
        //given
        Integer expectedHp = hpAfter;

        //when
        Integer actualHp = realDamageAndCurrentHpCalculator.getCurrentHp(hpBefore, realDamage);

        //then
        assertEquals(expectedHp, actualHp);
    }

    @Test
    @DisplayName("Вычисление фактического урона")
    public void getRealDamage(){
        //given
        Integer expectedRealDamage = realDamage;

        //when
        Integer actualRealDamage = realDamageAndCurrentHpCalculator.getRealDamage(damage, damageIgnore);

        //then
        assertEquals(expectedRealDamage, actualRealDamage);
    }
}
