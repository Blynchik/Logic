package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;

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
                getXpAward());
    }

    public Attributes getAttributes(Fighter fighter){
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

    public Attributes getAttributes(Characteristics characteristics){
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
        return (int) (constitution - Math.ceil(constitution / 2.0));
    }

    private Integer getMaxDamageIgnore(Integer constitution) {
        return (int) (constitution + Math.floor(constitution / 2.0));
    }

    private Integer getMinInitiative(Integer dexterity) {
        return (int) (dexterity - Math.ceil(dexterity / 2.0));
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

    private Integer getXpAward() {
        return 10;
    }
}
