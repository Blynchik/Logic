package com.logic.game.service.fight;

import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.HpAndRealDamageCalculator;
import com.logic.game.service.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurnCalculator {
    private final Throw throwValue;
    private final HpAndRealDamageCalculator hpAndRealDamageCalculator;
    private Fighter turnAttacker;
    private Fighter turnDefender;
    private Integer attack;
    private Integer evasion;
    private Integer damage;
    private Integer damageIgnore;
    private Integer realDamage;

    @Autowired
    public TurnCalculator(Throw throwValue,
                          HpAndRealDamageCalculator hpAndRealDamageCalculator) {
        this.throwValue = throwValue;
        this.hpAndRealDamageCalculator = hpAndRealDamageCalculator;
    }

    public Turn calculate(Fighter turnAttacker, Fighter turnDefender) {
        this.turnAttacker = new Fighter(turnAttacker);
        this.turnDefender = new Fighter(turnDefender);

        this.damage = 0;
        this.damageIgnore = 0;
        this.realDamage = 0;

        if (getHit()) {
            getRealDamage();
        }

        updateFighters(this.realDamage);

        return new Turn(this.turnAttacker,
                this.turnDefender,
                this.attack,
                this.evasion,
                damage,
                damageIgnore,
                realDamage);
    }

    private void updateFighters(Integer realDamage) {
        Integer attackerHp = hpAndRealDamageCalculator.getAttackerHp(
                this.turnAttacker.getAttributes().getCurrentHp());

        Integer defenderHp = hpAndRealDamageCalculator.getDefenderHp(
                this.turnDefender.getAttributes().getCurrentHp(), realDamage);

        this.turnAttacker.getAttributes().setCurrentHp(attackerHp);
        this.turnDefender.getAttributes().setCurrentHp(defenderHp);
//        System.out.printf("Остается у %s - %d, а у противника - %d\n\n", this.turnAttacker.getName(), attackerHp, defenderHp);
    }

    private Boolean getHit() {
        this.attack = throwValue.throwAttack(this.turnAttacker.getAttributes().getMinAttack(),
                this.turnAttacker.getAttributes().getMaxAttack());

        this.evasion = throwValue.throwEvasion(this.turnDefender.getAttributes().getMinEvasion(),
                this.turnDefender.getAttributes().getMaxEvasion());

//        System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", this.turnAttacker.getName(), attack, this.turnDefender.getName(), evasion);
        return attack > evasion;
    }

    private void getRealDamage(){
        this.damage = throwValue.throwDamage(this.turnAttacker.getAttributes().getMinDamage(),
                this.turnAttacker.getAttributes().getMaxDamage());

        this.damageIgnore = throwValue.throwDamageIgnore(this.turnDefender.getAttributes().getMinDamageIgnore(),
                this.turnDefender.getAttributes().getMaxDamageIgnore());

        this.realDamage = hpAndRealDamageCalculator.getRealDamage(this.damage, this.damageIgnore, 0);
//        System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", this.damage, this.damageIgnore, this.realDamage);
    }
}
