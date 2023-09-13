package com.logic.game.service;

import org.springframework.stereotype.Component;

/**
 * Класс Throw представляет утилиту для получения случайных значений атрибутов для сражения из диапазона.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class Throw {

    /**
     * Метод для выпадения случайного значения инициативы.
     *
     * @param minInitiative - целое число, представляющее минимальное значение инициативы.
     * @param maxInitiative - целое число, представляющее максимальное значение инициативы.
     * @return целое число, представляющее случайное значение инициативы в заданном диапазоне.
     */
    public Integer throwInitiative(Integer minInitiative, Integer maxInitiative) {
        return minInitiative + (int) (Math.random() * (maxInitiative - minInitiative + 1));
    }

    /**
     * Метод для выпадения случайного значения атаки.
     *
     * @param minAttack - целое число, представляющее минимальное значение атаки.
     * @param maxAttack - целое число, представляющее максимальное значение атаки.
     * @return целое число, представляющее случайное значение атаки в заданном диапазоне.
     */
    public Integer throwAttack(Integer minAttack, Integer maxAttack) {
        return minAttack + (int) (Math.random() * (maxAttack - minAttack + 1));
    }

    /**
     * Метод для выпадения случайного значения уклонения.
     *
     * @param minEvasion - целое число, представляющее минимальное значение уклонения.
     * @param maxEvasion - целое число, представляющее максимальное значение уклонения.
     * @return целое число, представляющее случайное значение уклонения в заданном диапазоне.
     */
    public Integer throwEvasion(Integer minEvasion, Integer maxEvasion) {
        return minEvasion + (int) (Math.random() * (maxEvasion - minEvasion + 1));
    }

    /**
     * Метод для выпадения случайного значения урона.
     *
     * @param minDamage - целое число, представляющее минимальное значение урона.
     * @param maxDamage - целое число, представляющее максимальное значение урона.
     * @return целое число, представляющее случайное значение урона в заданном диапазоне.
     */
    public Integer throwDamage(Integer minDamage, Integer maxDamage) {
        return minDamage + (int) (Math.random() * (maxDamage - minDamage + 1));
    }

    /**
     * Метод для выпадения случайного значения игнорирования урона.
     *
     * @param minDamageIgnore - целое число, представляющее минимальное значение игнорирования урона.
     * @param maxDamageIgnore - целое число, представляющее максимальное значение игнорирования урона.
     * @return целое число, представляющее случайное значение игнорирования урона в заданном диапазоне.
     */
    public Integer throwDamageIgnore(Integer minDamageIgnore, Integer maxDamageIgnore) {
        return minDamageIgnore + (int) (Math.random() * (maxDamageIgnore - minDamageIgnore + 1));
    }
}
