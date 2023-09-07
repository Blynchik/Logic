package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FighterService {

    private final RestClient restClient;
    private final CurrentFighterCalculator currentFighterCalculator;

    @Autowired
    public FighterService(RestClient restClient,
                          CurrentFighterCalculator currentFighterCalculator) {
        this.restClient = restClient;
        this.currentFighterCalculator = currentFighterCalculator;
    }

    public Fighter getFighterFromHero(Long id) {
        ResponseEntity<Hero> response = restClient.getHeroById(id);
        Hero hero = response.getBody();
        return currentFighterCalculator.mapFighter(hero);
    }

    public Fighter getRandomFighterFromEnemy() {
        ResponseEntity<Enemy> response = restClient.getRandomEnemy();
        Enemy enemy = response.getBody();
        return currentFighterCalculator.mapFighter(enemy);
    }
}
