package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import org.springframework.stereotype.Component;

@Component
public class AttributeCalculator {
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
                getCurrentHp(hero.getCurrentHp(), hero.getConstitution()),
                getXpAward());
    }

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
                getCurrentHp(null, enemy.getConstitution()),
                getXpAward());
    }

    private Integer getMinAttack(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    private Integer getMaxAttack(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private Integer getMinEvasion(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
    }

    private Integer getMaxEvasion(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private Integer getMinDamageIgnore(Integer constitution) {
        return 0;
    }

    private Integer getMaxDamageIgnore(Integer constitution) {
        return (int) (Math.floor(constitution / 2.0));
    }

    private Integer getMinInitiative(Integer dexterity) {
        return 0;
    }

    private Integer getMaxInitiative(Integer dexterity) {
        return (int) (dexterity + Math.floor(dexterity / 2.0));
    }

    private Integer getMinDamage(Integer strength) {
        return (int) (strength - Math.ceil(strength / 2.0));
    }

    private Integer getMaxDamage(Integer strength) {
        return (int) (strength + Math.floor(strength / 2.0));
    }

    private Integer getMaxHp(Integer constitution) {
        return 2 * constitution;
    }

    private Integer getCurrentHp(Integer currentHp, Integer constitution) {
        if (currentHp == null) {
            return getMaxHp(constitution);
        }
        return currentHp;
    }

    private Integer getXpAward() {
        return 10;
    }
}
