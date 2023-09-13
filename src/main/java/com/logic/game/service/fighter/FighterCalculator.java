package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageAndCurrentHpCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс FighterCalculator представляет утилиту для преобразования объектов типа Hero и Enemy в объекты типа Fighter.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class FighterCalculator {
    private final CharacteristicCalculator characteristicCalculator;
    private final AttributeCalculator attributeCalculator;
    private final RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;

    /**
     * Конструктор класса FighterCalculator со всеми необходимыми параметрами.
     *
     * @param characteristicCalculator         - объект класса CharacteristicCalculator для расчета характеристик бойца.
     * @param attributeCalculator              - объект класса AttributeCalculator для расчета атрибутов бойца.
     * @param realDamageAndCurrentHpCalculator - объект класса RealDamageAndCurrentHpCalculator для расчета текущего здоровья после получения реального урона бойцом.
     * @Autowired - аннотация Spring, которая автоматически связывает (инъектирует) объекты классов CharacteristicCalculator, AttributeCalculator и RealDamageAndCurrentHpCalculator.
     */
    @Autowired
    public FighterCalculator(CharacteristicCalculator characteristicCalculator,
                             AttributeCalculator attributeCalculator,
                             RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator) {
        this.characteristicCalculator = characteristicCalculator;
        this.attributeCalculator = attributeCalculator;
        this.realDamageAndCurrentHpCalculator = realDamageAndCurrentHpCalculator;
    }

    /**
     * Метод для преобразования объекта типа Hero в объект типа Fighter.
     *
     * @param hero - объект типа Hero, который нужно преобразовать.
     * @return объект типа Fighter, представляющий преобразованный боец.
     */
    public Fighter map(Hero hero) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(hero);
        Attributes attributes = attributeCalculator.getAttributes(hero);
        return new Fighter(hero.getName(), hero.getSurname(), hero.getAppUser(), characteristics, attributes, hero.getCurrentHp());
    }

    /**
     * Метод для преобразования объекта типа Enemy в объект типа Fighter.
     *
     * @param enemy - объект типа Enemy, который нужно преобразовать.
     * @return объект типа Fighter, представляющий преобразованный боец.
     */
    public Fighter map(Enemy enemy) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(enemy);
        Attributes attributes = attributeCalculator.getAttributes(enemy);
        return new Fighter(enemy.getName(), characteristics, attributes, enemy.getDescription(), attributes.getMaxHp());
    }

    /**
     * Метод для получения объекта Fighter с обновленными характеристиками.
     *
     * @param fighter         - объект типа Fighter, для которого нужно получить объект с новыми характеристиками.
     * @param characteristics - объект типа Characteristics, которые нужно применить к бойцу.
     * @return объект типа Fighter с обновленными характеристиками.
     */
    public Fighter getWithNewCharacteristics(Fighter fighter, Characteristics characteristics) {
        Attributes attributes = attributeCalculator.getAttributes(characteristics);
        return new Fighter(fighter, characteristics, attributes);
    }

    /**
     * Метод для получения объекта Fighter с обновленным текущим здоровьем после получения реального урона.
     *
     * @param fighter    - объект типа Fighter, для которого нужно получить объект с обновленным текущим здоровьем.
     * @param realDamage - целое число, представляющее реальный урон, полученный бойцом.
     * @return объект типа Fighter с обновленным текущим здоровьем.
     */
    public Fighter getWithUpdatedHp(Fighter fighter, Integer realDamage) {
        Integer currentHp = realDamageAndCurrentHpCalculator.getCurrentHp(fighter.getCurrentHp(), realDamage);
        return new Fighter(fighter, fighter.getCharacteristics(), fighter.getAttributes(), currentHp);
    }
}
