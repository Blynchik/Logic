package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FighterCalculator {
    private final CharacteristicCalculator characteristicCalculator;
    private final AttributeCalculator attributeCalculator;

    @Autowired
    public FighterCalculator(CharacteristicCalculator characteristicCalculator,
                             AttributeCalculator attributeCalculator) {
        this.characteristicCalculator = characteristicCalculator;
        this.attributeCalculator = attributeCalculator;
    }

    public Fighter map(Hero hero) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(hero);
        Attributes attributes = attributeCalculator.getAttributes(hero);
        return new Fighter(hero.getName(),
                hero.getSurname(),
                hero.getAppUser(),
                characteristics,
                attributes);
    }

    public Fighter map(Enemy enemy) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(enemy);
        Attributes attributes = attributeCalculator.getAttributes(enemy);
        return new Fighter(enemy.getName(),
                characteristics,
                attributes,
                enemy.getDescription());
    }

    public Fighter getWithNewCharacteristics(Fighter fighter, Characteristics characteristics) {
        Attributes attributes = attributeCalculator.getAttributes(characteristics, fighter.getAttributes().getCurrentHp());
        return new Fighter(fighter,
                characteristics,
                attributes);
    }

    public Fighter getWithUpdatedHp(Fighter fighter, Integer realDamage) {
        Attributes attributes = attributeCalculator.getAttributesWithUpdatedHp(fighter.getCharacteristics(),
                fighter.getAttributes().getCurrentHp(), realDamage);
        return new Fighter(fighter,
                fighter.getCharacteristics(),
                attributes);
    }
}
