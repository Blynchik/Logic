package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageAndCurrentHpCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FighterCalculator {
    private final CharacteristicCalculator characteristicCalculator;
    private final AttributeCalculator attributeCalculator;
    private final RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;

    @Autowired
    public FighterCalculator(CharacteristicCalculator characteristicCalculator,
                             AttributeCalculator attributeCalculator,
                             RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator) {
        this.characteristicCalculator = characteristicCalculator;
        this.attributeCalculator = attributeCalculator;
        this.realDamageAndCurrentHpCalculator = realDamageAndCurrentHpCalculator;
    }

    public Fighter map(Hero hero) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(hero);
        Attributes attributes = attributeCalculator.getAttributes(hero);
        return new Fighter(hero.getName(),
                hero.getSurname(),
                hero.getAppUser(),
                characteristics,
                attributes,
                hero.getCurrentHp());
    }

    public Fighter map(Enemy enemy) {
        Characteristics characteristics = characteristicCalculator.getCharacteristics(enemy);
        Attributes attributes = attributeCalculator.getAttributes(enemy);
        return new Fighter(enemy.getName(),
                characteristics,
                attributes,
                enemy.getDescription(),
                attributes.getMaxHp());
    }

    public Fighter getWithNewCharacteristics(Fighter fighter, Characteristics characteristics) {
        Attributes attributes = attributeCalculator.getAttributes(characteristics);
        return new Fighter(fighter,
                characteristics,
                attributes);
    }

    public Fighter getWithUpdatedHp(Fighter fighter, Integer realDamage) {
        Integer currentHp = realDamageAndCurrentHpCalculator.getCurrentHp(fighter.getCurrentHp(), realDamage);
        return new Fighter(fighter,
                fighter.getCharacteristics(),
                fighter.getAttributes(),
                currentHp);
    }
}
