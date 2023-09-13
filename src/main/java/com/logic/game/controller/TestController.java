package com.logic.game.controller;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fight.Round;
import com.logic.game.model.fight.Turn;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.fight.FightService;
import com.logic.game.service.fighter.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Класс TestController представляет контроллер для обработки запросов связанных с логикой боевых действий.
 * Используется аннотация @RestController для определения этого класса как контроллера REST.
 * Используется аннотация @RequestMapping для указания базового адреса для всех методов в данном контроллере.
 */
@RestController
@RequestMapping("/api/logic")
public class TestController {

    private final FightService fightService;
    private final FighterService fighterService;

    /**
     * Конструктор класса TestController, который внедряет зависимости через аннотацию @Autowired.
     *
     * @param fightService   сервис для работы с боевыми действиями
     * @param fighterService сервис для работы с бойцами
     */
    @Autowired
    public TestController(FightService fightService, FighterService fighterService) {
        this.fightService = fightService;
        this.fighterService = fighterService;
    }

    /**
     * Метод getHeroById() отвечает за обработку GET-запроса для получения информации о герое по его ID.
     *
     * @param id ID героя
     * @return объект ResponseEntity, содержащий информацию о герое в формате JSON
     */
    @GetMapping("/hero/{id}")
    public ResponseEntity<Fighter> getHeroById(@PathVariable Long id) {
        Fighter fighter = fighterService.getFromHero(id);
        return ResponseEntity.ok(fighter);
    }

    /**
     * Метод getRandomEnemy() отвечает за обработку GET-запроса для получения случайного противника.
     *
     * @return объект ResponseEntity, содержащий информацию о случайном противнике в формате JSON
     */
    @GetMapping("/enemy/random")
    public ResponseEntity<Fighter> getRandomEnemy() {
        Fighter fighter = fighterService.getRandomFromEnemy();
        return ResponseEntity.ok(fighter);
    }

    /**
     * Метод getFight() отвечает за обработку GET-запроса для запуска боя между героем и случайным противником.
     *
     * @param heroId ID героя
     * @return объект ResponseEntity, содержащий информацию о бое в формате JSON
     */
    @GetMapping("/fight")
    public ResponseEntity<Fight> getFight(@RequestParam Long heroId) {
        Fighter fighter1 = fighterService.getFromHero(heroId);
        Fighter fighter2 = fighterService.getRandomFromEnemy();
        Fight fight = fightService.start(fighter1, fighter2);

        // Здесь код, который печатает информацию о бое в консоль
        for (Round r : fight.getRounds()) {
            System.out.printf("Раунд %d\n", r.getRoundNum());
            System.out.printf("Инициатива %s - %d, а %s - %d\n", r.getAttacker().getName(), r.getAttackerInitiative(),
                    r.getDefender().getName(), r.getDefenderInitiative());
            for (Turn t : r.getTurns()) {
                System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", t.getTurnAttacker().getName(),
                        t.getAttack(), t.getTurnDefender().getName(), t.getEvasion());
                System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", t.getDamage(), t.getDamageIgnore(), t.getRealDamage());
                System.out.printf("Остается у %s - %d, а у противника - %d\n\n", t.getTurnAttacker().getName(),
                        t.getTurnAttacker().getCurrentHp(),
                        t.getTurnDefender().getCurrentHp());
            }
        }
        System.out.printf("Победил %s, проиграл %s\n", fight.getWinner().getName(), fight.getLoser().getName());
        System.out.println("======================");

        return ResponseEntity.ok(fight);
    }

    /**
     * Метод getStatistics() отвечает за обработку GET-запроса для получения статистики боев с различными
     * комбинациями характеристик бойцов.
     *
     * @param tests количество тестов
     * @param bonus бонус для характеристик бойца
     * @return объект ResponseEntity, содержащий статистику боев в формате строки
     */
    @GetMapping("/fight/statistic")
    public ResponseEntity<String> getStatistics(@RequestParam(defaultValue = "100000") Integer tests,
                                                @RequestParam(defaultValue = "1") Integer bonus) {
        int wins = 0;
        int numRounds = 0;

        // Получение базового героя и случайного противника
        Fighter baseFighter1 = fighterService.getFromHero(1L);
        Fighter baseFighter2 = fighterService.getRandomFromEnemy();

        // Статистика для характеристики "Равные значения"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateEquals = (wins / (double) tests) * 100;
        double equalsAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для характеристики "Сила"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = new Fighter(baseFighter2);
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateStrength = (wins / (double) tests) * 100;
        double strengthAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для характеристики "Ловкость"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5 + bonus, 5));
            Fighter fighter2 = new Fighter(baseFighter2);
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateDexterity = (wins / (double) tests) * 100;
        double dexterityAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для характеристики "Выносливость"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5, 5 + bonus));
            Fighter fighter2 = new Fighter(baseFighter2);
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateConstitution = (wins / (double) tests) * 100;
        double constitutionAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для комбинации характеристик "Сила vs Ловкость"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5, 5 + bonus, 5));
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateStrVsDex = (wins / (double) tests) * 100;
        double strVsDexAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для комбинации характеристик "Сила vs Выносливость"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5, 5, 5 + bonus));
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds += fight.getRounds().size();
        }
        double winRateStrVsConst = (wins / (double) tests) * 100;
        double strVsConstAvgNumRounds = numRounds / (double) tests;

        // Сброс счетчиков
        numRounds = 0;
        wins = 0;

        // Статистика для комбинации характеристик "Ловкость vs Выносливость"
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5 + bonus, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5, 5, 5 + bonus));
            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateDexVsConst = (wins / (double) tests) * 100;
        double dexVsConstAvgNumRounds = numRounds / (double) tests;

        // Формирование результата статистики в формате строки
        String statistics = "Статистика боев:" +
                "\n\nХарактеристика: Равные значения" +
                "\nПроцент побед: " + winRateEquals +
                "\nСреднее количество раундов: " + equalsAvgNumRounds +
                "\n\nХарактеристика: Сила" +
                "\nПроцент побед: " + winRateStrength +
                "\nСреднее количество раундов: " + strengthAvgNumRounds +
                "\n\nХарактеристика: Ловкость" +
                "\nПроцент побед: " + winRateDexterity +
                "\nСреднее количество раундов: " + dexterityAvgNumRounds +
                "\n\nХарактеристика: Выносливость" +
                "\nПроцент побед: " + winRateConstitution +
                "\nСреднее количество раундов: " + constitutionAvgNumRounds +
                "\n\nХарактеристика: Сила vs Ловкость" +
                "\nПроцент побед: " + winRateStrVsDex +
                "\nСреднее количество раундов: " + strVsDexAvgNumRounds +
                "\n\nХарактеристика: Сила vs Выносливость" +
                "\nПроцент побед: " + winRateStrVsConst +
                "\nСреднее количество раундов: " + strVsConstAvgNumRounds +
                "\n\nХарактеристика: Ловкость vs Выносливость" +
                "\nПроцент побед: " + winRateDexVsConst +
                "\nСреднее количество раундов: " + dexVsConstAvgNumRounds;

        return ResponseEntity.ok(statistics);
    }
}
