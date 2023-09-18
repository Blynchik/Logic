package com.logic.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ThrowUnitTest {
    Integer minValue;
    Integer maxValue;

    @InjectMocks
    Throw throwValue;

    @BeforeEach
    void setUp() {
        minValue = 1;
        maxValue = 10;
    }

    @Test
    @DisplayName("Получение слуйчайной инициативы из диапазона")
    public void throwInitiative() {
        //given
        Integer minInitiative = minValue;
        Integer maxInitiative = maxValue;

        //when
        List<Integer> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            values.add(throwValue.throwInitiative(minInitiative, maxInitiative));
        }

        //then
        for (Integer i : values) {
            assertTrue(i >= minInitiative && i <= maxInitiative);
        }
    }

    @Test
    @DisplayName("Получение случайной атаки из диапазона")
    public void throwAttack() {
        //given
        Integer minAttack = minValue;
        Integer maxAttack = maxValue;

        //when
        List<Integer> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            values.add(throwValue.throwAttack(minAttack, maxAttack));
        }

        //then
        for (Integer i : values) {
            assertTrue(i >= minAttack && i <= maxAttack);
        }
    }

    @Test
    @DisplayName("Получение случайного уклонения из диапазона")
    public void throwEvasion() {
        //given
        Integer minEvasion = minValue;
        Integer maxEvasion = maxValue;

        //when
        List<Integer> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            values.add(throwValue.throwEvasion(minEvasion, maxEvasion));
        }

        //then
        for (Integer i : values) {
            assertTrue(i >= minEvasion && i <= maxEvasion);
        }
    }

    @Test
    @DisplayName("Получение случайного урона из диапазона")
    public void throwDamage() {
        //given
        Integer minDamage = minValue;
        Integer maxDamage = maxValue;

        //when
        List<Integer> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            values.add(throwValue.throwDamage(minDamage, maxDamage));
        }

        //then
        for (Integer i : values) {
            assertTrue(i >= minDamage && i <= maxDamage);
        }
    }

    @Test
    @DisplayName("Получение случайного игнорирования урона из диапазона")
    public void throwDamageIgnore() {
        //given
        Integer minDamageIgnore = minValue;
        Integer maxDamageIgnore = maxValue;

        //when
        List<Integer> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            values.add(throwValue.throwDamageIgnore(minDamageIgnore, maxDamageIgnore));
        }

        //then
        for (Integer i : values) {
            assertTrue(i >= minDamageIgnore && i <= maxDamageIgnore);
        }
    }
}
