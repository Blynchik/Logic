package com.logic.game.service.fight;

import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageCalculator;
import com.logic.game.service.Throw;
import com.logic.game.service.fighter.AttributeCalculator;
import com.logic.game.service.fighter.FighterCalculator;
import com.logic.game.service.fighter.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurnCalculator {
    private final Throw throwValue;
    private final RealDamageCalculator realDamageCalculator;
    private final FighterCalculator fighterCalculator;
    private Fighter turnAttacker;
    private Fighter turnDefender;
    private Integer attack;
    private Integer evasion;
    private Integer damage;
    private Integer damageIgnore;
    private Integer realDamage;

    @Autowired
    public TurnCalculator(Throw throwValue,
                          RealDamageCalculator realDamageCalculator,
                          FighterCalculator fighterCalculator) {
        this.throwValue = throwValue;
        this.realDamageCalculator = realDamageCalculator;
        this.fighterCalculator = fighterCalculator;

    }

    public Turn calculate(Fighter turnAttacker, Fighter turnDefender) {
        this.turnAttacker = turnAttacker;
        this.turnDefender = turnDefender;

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
        this.turnAttacker = updateHp(this.turnAttacker, 0);
        this.turnDefender = updateHp(this.turnDefender, realDamage);


//        System.out.printf("Остается у %s - %d, а у противника - %d\n\n", this.turnAttacker.getName(), this.turnAttacker.getAttributes().getCurrentHp(), this.turnDefender.getAttributes().getCurrentHp());
    }

    private Fighter updateHp(Fighter fighter, Integer realDamage) {
        return fighterCalculator.getWithUpdatedHp(fighter, realDamage);
    }

    private Boolean getHit() {
        this.attack = throwValue.throwAttack(this.turnAttacker.getAttributes().getMinAttack(),
                this.turnAttacker.getAttributes().getMaxAttack());

        this.evasion = throwValue.throwEvasion(this.turnDefender.getAttributes().getMinEvasion(),
                this.turnDefender.getAttributes().getMaxEvasion());

//        System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", this.turnAttacker.getName(), attack, this.turnDefender.getName(), evasion);
        return attack > evasion;
    }

    private void getRealDamage() {
        this.damage = throwValue.throwDamage(this.turnAttacker.getAttributes().getMinDamage(),
                this.turnAttacker.getAttributes().getMaxDamage());

        this.damageIgnore = throwValue.throwDamageIgnore(this.turnDefender.getAttributes().getMinDamageIgnore(),
                this.turnDefender.getAttributes().getMaxDamageIgnore());

        this.realDamage = realDamageCalculator.getRealDamage(this.damage, this.damageIgnore, 0);
//        System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", this.damage, this.damageIgnore, this.realDamage);
    }
}
