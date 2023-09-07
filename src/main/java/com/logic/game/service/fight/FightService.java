package com.logic.game.service.fight;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fighter.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FightService {

    private final FightCalculator fightCalculator;

    @Autowired
    public FightService(FightCalculator fightCalculator){
        this.fightCalculator = fightCalculator;
    }

    public Fight start(Fighter fighter1, Fighter fighter2){
        return fightCalculator.calculate(fighter1, fighter2);
    }
}
