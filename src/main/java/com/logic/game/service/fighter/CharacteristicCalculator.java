package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
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

    public Characteristics getCharacteristics(Fighter fighter){
        return new Characteristics(getStrength(fighter.getCharacteristics().getStrength()),
                getDexterity(fighter.getCharacteristics().getDexterity()),
                getConstitution(fighter.getCharacteristics().getConstitution()));
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
