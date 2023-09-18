package com.logic.game.service.fighter;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Класс FighterService представляет сервис для работы с боевыми юнитами.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class FighterService {
    private final RestClient restClient;
    private final FighterCalculator fighterCalculator;

    /**
     * Конструктор класса FighterService с параметрами типа RestClient и FighterCalculator.
     *
     * @param restClient        - объект класса RestClient для выполнения API запросов.
     * @param fighterCalculator - объект класса FighterCalculator для расчета характеристик бойца.
     * @Autowired - аннотация Spring, которая автоматически связывает (инъектирует) объекты классов RestClient и FighterCalculator.
     */
    @Autowired
    public FighterService(RestClient restClient, FighterCalculator fighterCalculator) {
        this.restClient = restClient;
        this.fighterCalculator = fighterCalculator;
    }

    /**
     * Метод для получения бойца из героя по его идентификатору.
     *
     * @param id - идентификатор героя типа Long.
     * @return объект типа Fighter, представляющий бойца, полученного из героя.
     */
    public Fighter getFromHero(Long id) throws Exception {
        ResponseEntity<Hero> response = restClient.getHeroById(id);
        Hero hero = response.getBody();
        return fighterCalculator.map(hero);
    }

    /**
     * Метод для получения случайного бойца из противника.
     *
     * @return объект типа Fighter, представляющий бойца, полученного из противника.
     */
    public Fighter getRandomFromEnemy() throws Exception {
        ResponseEntity<Enemy> response = restClient.getRandomEnemy();
        Enemy enemy = response.getBody();
        return fighterCalculator.map(enemy);
    }

    /**
     * Метод для получения бойца с новыми характеристиками.
     *
     * @param fighter         - объект типа Fighter, представляющий бойца.
     * @param characteristics - объект типа Characteristics, содержащий новые характеристики бойца.
     * @return объект типа Fighter, представляющий бойца с обновленными характеристиками.
     */
    public Fighter getWithNewCharacteristics(Fighter fighter, Characteristics characteristics) {
        return fighterCalculator.getWithNewCharacteristics(fighter, characteristics);
    }

    /**
     * Метод для получения бойца с обновленными HP после получения реального урона.
     *
     * @param fighter    - объект типа Fighter, представляющий бойца.
     * @param realDamage - целочисленное значение, представляющее реальный урон, полученный бойцом.
     * @return объект типа Fighter, представляющий бойца с обновленными HP.
     */
    public Fighter getWithUpdatedHp(Fighter fighter, Integer realDamage) {
        return fighterCalculator.getWithUpdatedHp(fighter, realDamage);
    }
}
