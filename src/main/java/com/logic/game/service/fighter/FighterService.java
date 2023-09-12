package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FighterService {

    private final RestClient restClient;
    private final FighterCalculator fighterCalculator;

    @Autowired
    public FighterService(RestClient restClient,
                          FighterCalculator fighterCalculator) {
        this.restClient = restClient;
        this.fighterCalculator = fighterCalculator;
    }

    public Fighter getFromHero(Long id) {
        ResponseEntity<Hero> response = restClient.getHeroById(id);
        Hero hero = response.getBody();
        return fighterCalculator.map(hero);
    }

    public Fighter getRandomFromEnemy() {
        ResponseEntity<Enemy> response = restClient.getRandomEnemy();
        Enemy enemy = response.getBody();
        return fighterCalculator.map(enemy);
    }

    public Fighter getUpdated(Fighter fighter, Integer realDamage){
        return fighterCalculator.update(fighter, realDamage);
    }

    public Fighter getByCharacteristics(Fighter fighter, Characteristics characteristics){
        return fighterCalculator.getByCharacteristics(fighter, characteristics);
    }
}
