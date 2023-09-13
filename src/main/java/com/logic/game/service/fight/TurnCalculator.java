package com.logic.game.service.fight;

import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RealDamageAndCurrentHpCalculator;
import com.logic.game.service.Throw;
import com.logic.game.service.fighter.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс TurnCalculator представляет утилиту для расчета информации о ходе боя.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейером.
 */
@Component
public class TurnCalculator {
    private final Throw throwValue;
    private final RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator;
    private final FighterService fighterService;
    private Fighter turnAttacker;
    private Fighter turnDefender;
    private Integer attack;
    private Integer evasion;
    private Integer damage;
    private Integer damageIgnore;
    private Integer realDamage;

    /**
     * Конструктор класса TurnCalculator.
     *
     * @param throwValue                       - объект типа Throw, используемый для генерации случайных значений в
     *                                         рамках возможных.
     * @param realDamageAndCurrentHpCalculator - объект типа RealDamageAndCurrentHpCalculator для расчета
     *                                         фактического урона и HP бойца после удара.
     * @param fighterService                   - объект для работы с бойцами
     */
    @Autowired
    public TurnCalculator(Throw throwValue,
                          RealDamageAndCurrentHpCalculator realDamageAndCurrentHpCalculator,
                          FighterService fighterService) {
        this.throwValue = throwValue;
        this.realDamageAndCurrentHpCalculator = realDamageAndCurrentHpCalculator;
        this.fighterService = fighterService;

    }

    /**
     * Метод для расчета информации о ходе боя.
     *
     * @param turnAttacker - объект Fighter, представляющий атакующего бойца.
     * @param turnDefender - объект Fighter, представляющий защищающегося бойца.
     * @return объект Turn, представляющий информацию о ходе боя.
     */
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

    /**
     * Приватный метод для обновления бойцов после хода.
     *
     * @param realDamage - объект типа Integer, представляющий фактический урон, нанесенный в данном ходе.
     */
    private void updateFighters(Integer realDamage) {
        this.turnAttacker = updateHp(this.turnAttacker, 0);
        this.turnDefender = updateHp(this.turnDefender, realDamage);


//        System.out.printf("Остается у %s - %d, а у противника - %d\n\n", this.turnAttacker.getName(), this.turnAttacker.getAttributes().getCurrentHp(), this.turnDefender.getAttributes().getCurrentHp());
    }

    /**
     * Приватный метод для обновления бойцов после хода с обновленным HP.
     *
     * @param fighter    - объект типа Fighter, пресдтавляющий бойца, которому нанесли урон.
     * @param realDamage - объект типа Integer, представляющий фактический урон, нанесенный в данном ходе.
     * @return объект Fighter - копию с обновленным HP.
     */
    private Fighter updateHp(Fighter fighter, Integer realDamage) {
        return fighterService.getWithUpdatedHp(fighter, realDamage);
    }

    /**
     * Приватный метод для учета попадания.
     *
     * @return объект Boolean, отражающий было попадание или нет.
     */
    private Boolean getHit() {
        this.attack = throwValue.throwAttack(this.turnAttacker.getAttributes().getMinAttack(),
                this.turnAttacker.getAttributes().getMaxAttack());

        this.evasion = throwValue.throwEvasion(this.turnDefender.getAttributes().getMinEvasion(),
                this.turnDefender.getAttributes().getMaxEvasion());

//        System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", this.turnAttacker.getName(), attack, this.turnDefender.getName(), evasion);
        return attack > evasion;
    }

    /**
     * Приватный метод для расчета нанесенного фактического урона.
     */
    private void getRealDamage() {
        this.damage = throwValue.throwDamage(this.turnAttacker.getAttributes().getMinDamage(),
                this.turnAttacker.getAttributes().getMaxDamage());

        this.damageIgnore = throwValue.throwDamageIgnore(this.turnDefender.getAttributes().getMinDamageIgnore(),
                this.turnDefender.getAttributes().getMaxDamageIgnore());

        this.realDamage = realDamageAndCurrentHpCalculator.getRealDamage(this.damage, this.damageIgnore);
//        System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", this.damage, this.damageIgnore, this.realDamage);
    }
}
