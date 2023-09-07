package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Characteristics;
import org.springframework.stereotype.Component;

@Component
public class CharacteristicCalculator {
    public Characteristics getCharacteristics(Hero hero) {
        return new Characteristics(getStrength(hero.getStrength()),
                getDexterity(hero.getDexterity()),
                getConstitution(hero.getConstitution()));
    }

    public Characteristics getCharacteristics(Enemy enemy) {
        return new Characteristics(getStrength(enemy.getStrength()),
                getDexterity(enemy.getDexterity()),
                getConstitution(enemy.getConstitution()));
    }

    private Integer getStrength(Integer strength) {
        return strength;
    }

    private Integer getDexterity(Integer dexterity) {
        return dexterity;
    }

    private Integer getConstitution(Integer constitution) {
        return constitution;
    }
}
