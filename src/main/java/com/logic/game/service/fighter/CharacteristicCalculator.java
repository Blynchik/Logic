package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import org.springframework.stereotype.Component;

/**
 * Класс CharacteristicCalculator представляет утилиту для расчета характеристик бойцов.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class CharacteristicCalculator {

    /**
     * Метод для расчета характеристик бойца на основе объекта типа Hero.
     *
     * @param hero - объект типа Hero, для которого нужно расчитать характеристики.
     * @return объект типа Characteristics, содержащий расчитанные характеристики бойца.
     */
    public Characteristics getCharacteristics(Hero hero) {
        return new Characteristics(getStrength(hero.getStrength()), getDexterity(hero.getDexterity()), getConstitution(hero.getConstitution()));
    }

    /**
     * Метод для расчета характеристик бойца на основе объекта типа Enemy.
     *
     * @param enemy - объект типа Enemy, для которого нужно расчитать характеристики.
     * @return объект типа Characteristics, содержащий расчитанные характеристики бойца.
     */
    public Characteristics getCharacteristics(Enemy enemy) {
        return new Characteristics(getStrength(enemy.getStrength()), getDexterity(enemy.getDexterity()), getConstitution(enemy.getConstitution()));
    }

    /**
     * Метод для расчета характеристик бойца на основе объекта типа Fighter.
     *
     * @param fighter - объект типа Fighter, для которого нужно расчитать характеристики.
     * @return объект типа Characteristics, содержащий расчитанные характеристики бойца.
     */
    public Characteristics getCharacteristics(Fighter fighter) {
        return new Characteristics(getStrength(fighter.getCharacteristics().getStrength()), getDexterity(fighter.getCharacteristics().getDexterity()), getConstitution(fighter.getCharacteristics().getConstitution()));
    }

    /**
     * Приватный метод для получения силы.
     *
     * @param strength - целое число, представляющее силу бойца.
     * @return сила бойца.
     */
    public Integer getStrength(Integer strength) {
        return strength;
    }

    /**
     * Приватный метод для получения ловкости.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return ловкость бойца.
     */
    public Integer getDexterity(Integer dexterity) {
        return dexterity;
    }

    /**
     * Приватный метод для получения выносливости.
     *
     * @param constitution - целое число, представляющее выносливость бойца.
     * @return выносливость бойца.
     */
    public Integer getConstitution(Integer constitution) {
        return constitution;
    }
}