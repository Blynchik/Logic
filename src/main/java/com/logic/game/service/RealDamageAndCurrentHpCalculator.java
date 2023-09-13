package com.logic.game.service;

import org.springframework.stereotype.Component;

/**
 * Класс RealDamageAndCurrentHpCalculator представляет утилиту для расчета текущего здоровья и реального урона.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class RealDamageAndCurrentHpCalculator {

    /**
     * Метод для расчета текущего здоровья после получения урона.
     *
     * @param currentHp  - целое число, представляющее текущее здоровье.
     * @param realDamage - целое число, представляющее реальный урон, полученный бойцом.
     * @return целое число, представляющее текущее здоровье после получения урона.
     */
    public Integer getCurrentHp(Integer currentHp, Integer realDamage) {
        return currentHp - realDamage;
    }

    /**
     * Метод для расчета реального урона, игнорируя броню.
     *
     * @param damage       - целое число, представляющее базовый урон.
     * @param damageIgnore - целое число, представляющее игнорируемый урон.
     * @return целое число, представляющее реальный урон после игнорируемого урона.
     */
    public Integer getRealDamage(Integer damage, Integer damageIgnore) {
        return Math.max(damage - damageIgnore, 0);
    }
}
