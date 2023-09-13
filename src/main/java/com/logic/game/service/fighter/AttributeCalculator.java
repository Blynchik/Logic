package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import org.springframework.stereotype.Component;

/**
 * Класс AttributeCalculator представляет утилиту для расчета атрибутов бойцов.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class AttributeCalculator {

    /**
     * Метод для расчета атрибутов бойца на основе объекта типа Hero.
     *
     * @param hero - объект типа Hero, для которого нужно расчитать атрибуты.
     * @return объект типа Attributes, содержащий расчитанные атрибуты бойца.
     */
    public Attributes getAttributes(Hero hero) {
        return new Attributes(getMinAttack(hero.getDexterity()),
                getMaxAttack(hero.getDexterity()),
                getMinEvasion(hero.getDexterity()),
                getMaxEvasion(hero.getDexterity()),
                getMinDamageIgnore(hero.getConstitution()),
                getMaxDamageIgnore(hero.getConstitution()),
                getMinInitiative(hero.getDexterity()),
                getMaxInitiative(hero.getDexterity()),
                getMinDamage(hero.getStrength()),
                getMaxDamage(hero.getStrength()),
                getMaxHp(hero.getConstitution()),
                getXpAward());
    }

    /**
     * Метод для расчета атрибутов бойца на основе объекта типа Enemy.
     *
     * @param enemy - объект типа Enemy, для которого нужно расчитать атрибуты.
     * @return объект типа Attributes, содержащий расчитанные атрибуты бойца.
     */
    public Attributes getAttributes(Enemy enemy) {
        return new Attributes(getMinAttack(enemy.getDexterity()),
                getMaxAttack(enemy.getDexterity()),
                getMinEvasion(enemy.getDexterity()),
                getMaxEvasion(enemy.getDexterity()),
                getMinDamageIgnore(enemy.getConstitution()),
                getMaxDamageIgnore(enemy.getConstitution()),
                getMinInitiative(enemy.getDexterity()),
                getMaxInitiative(enemy.getDexterity()),
                getMinDamage(enemy.getStrength()),
                getMaxDamage(enemy.getStrength()),
                getMaxHp(enemy.getConstitution()),
                getXpAward());
    }

    /**
     * Метод для расчета атрибутов бойца на основе объекта типа Fighter.
     *
     * @param fighter - объект типа Fighter, для которого нужно расчитать атрибуты.
     * @return объект типа Attributes, содержащий расчитанные атрибуты бойца.
     */
    public Attributes getAttributes(Fighter fighter) {
        return new Attributes(getMinAttack(fighter.getCharacteristics().getDexterity()),
                getMaxAttack(fighter.getCharacteristics().getDexterity()),
                getMinEvasion(fighter.getCharacteristics().getDexterity()),
                getMaxEvasion(fighter.getCharacteristics().getDexterity()),
                getMinDamageIgnore(fighter.getCharacteristics().getConstitution()),
                getMaxDamageIgnore(fighter.getCharacteristics().getConstitution()),
                getMinInitiative(fighter.getCharacteristics().getDexterity()),
                getMaxInitiative(fighter.getCharacteristics().getDexterity()),
                getMinDamage(fighter.getCharacteristics().getStrength()),
                getMaxDamage(fighter.getCharacteristics().getStrength()),
                getMaxHp(fighter.getCharacteristics().getConstitution()),
                getXpAward());
    }

    /**
     * Метод для расчета атрибутов бойца на основе объекта типа Characteristics.
     *
     * @param characteristics - объект типа Characteristics, содержащий характеристики бойца.
     * @return объект типа Attributes, содержащий расчитанные атрибуты бойца.
     */
    public Attributes getAttributes(Characteristics characteristics) {

        return new Attributes(getMinAttack(characteristics.getDexterity()),
                getMaxAttack(characteristics.getDexterity()),
                getMinEvasion(characteristics.getDexterity()),
                getMaxEvasion(characteristics.getDexterity()),
                getMinDamageIgnore(characteristics.getConstitution()),
                getMaxDamageIgnore(characteristics.getConstitution()),
                getMinInitiative(characteristics.getDexterity()),
                getMaxInitiative(characteristics.getDexterity()),
                getMinDamage(characteristics.getStrength()),
                getMaxDamage(characteristics.getStrength()),
                getMaxHp(characteristics.getConstitution()),
                getXpAward());
    }

    /**
     * Приватный метод для расчета минимального значения атаки.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return минимальное значение атаки бойца.
     */
    private Integer getMinAttack(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения атаки.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return максимальное значение атаки бойца.
     */
    private Integer getMaxAttack(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета минимального значения уклонения.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return минимальное значение уклонения бойца.
     */
    private Integer getMinEvasion(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения уклонения.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return максимальное значение уклонения бойца.
     */
    private Integer getMaxEvasion(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета минимального значения игнорирования урона.
     *
     * @param constitution - целое число, представляющее выносливость бойца.
     * @return минимальное значение игнорирования урона бойца.
     */
    private Integer getMinDamageIgnore(Integer constitution) {
        return (int) (constitution - Math.ceil(constitution / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения игнорирования урона.
     *
     * @param constitution - целое число, представляющее выносливость бойца.
     * @return максимальное значение игнорирования урона бойца.
     */
    private Integer getMaxDamageIgnore(Integer constitution) {
        return (int) (constitution + Math.floor(constitution / 2.0));
    }

    /**
     * Приватный метод для расчета минимального значения инициативы.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return минимальное значение инициативы бойца.
     */
    private Integer getMinInitiative(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения инициативы.
     *
     * @param dexterity - целое число, представляющее ловкость бойца.
     * @return максимальное значение инициативы бойца.
     */
    private Integer getMaxInitiative(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    /**
     * Приватный метод для расчета минимального значения урона.
     *
     * @param strength - целое число, представляющее силу бойца.
     * @return минимальное значение урона бойца.
     */
    private Integer getMinDamage(Integer strength) {
        return (int) (strength - Math.ceil(strength / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения урона.
     *
     * @param strength - целое число, представляющее силу бойца.
     * @return максимальное значение урона бойца.
     */
    private Integer getMaxDamage(Integer strength) {
        return (int) (strength + Math.floor(strength / 2.0));
    }

    /**
     * Приватный метод для расчета максимального значения здоровья.
     *
     * @param constitution - целое число, представляющее выносливость бойца.
     * @return максимальное значение здоровья бойца.
     */
    private Integer getMaxHp(Integer constitution) {
        return 2 * constitution;
    }

    /**
     * Приватный метод для получения награды опыта.
     *
     * @return награда опыта.
     */
    private Integer getXpAward() {
        return 10;
    }
}
