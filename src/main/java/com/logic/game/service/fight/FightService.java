package com.logic.game.service.fight;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fighter.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс FightService представляет сервис для проведения боев между бойцами.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class FightService {
    private final FightCalculator fightCalculator;

    /**
     * Конструктор класса FightService с параметром типа FightCalculator.
     *
     * @param fightCalculator - объект класса FightCalculator для проведения расчетов боя между бойцами.
     * @Autowired - аннотация Spring, которая автоматически связывает (инъектирует) объект класса FightCalculator.
     */
    @Autowired
    public FightService(FightCalculator fightCalculator){
        this.fightCalculator = fightCalculator;
    }

    /**
     * Метод для начала боя между двумя бойцами.
     *
     * @param fighter1 - объект типа Fighter, представляющий первого бойца.
     * @param fighter2 - объект типа Fighter, представляющий второго бойца.
     * @return объект типа Fight, представляющий проведенный бой и его результаты.
     */
    public Fight start(Fighter fighter1, Fighter fighter2){
        return fightCalculator.calculate(fighter1, fighter2);
    }
}

