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

@RestController
@RequestMapping("/api/logic")
public class TestController {
    private final FightService fightService;
    private final FighterService fighterService;

    @Autowired
    public TestController(FightService fightService,
                          FighterService fighterService) {
        this.fightService = fightService;
        this.fighterService = fighterService;
    }

    @GetMapping("/hero/{id}")
    public ResponseEntity<Fighter> getHeroById(@PathVariable Long id) {
        Fighter fighter = fighterService.getFromHero(id);
        return ResponseEntity.ok(fighter);
    }

    @GetMapping("/enemy/random")
    public ResponseEntity<Fighter> getRandomEnemy() {
        Fighter fighter = fighterService.getRandomFromEnemy();
        return ResponseEntity.ok(fighter);
    }

    @GetMapping("/fight")
    public ResponseEntity<Fight> getFight(@RequestParam Long heroId) {
        Fighter fighter1 = fighterService.getFromHero(heroId);
        Fighter fighter2 = fighterService.getRandomFromEnemy();
        Fight fight = fightService.start(fighter1, fighter2);

        for (Round r : fight.getRounds()){
            System.out.printf("Раунд %d\n",r.getRoundNum());
            System.out.printf("Инициатива %s - %d, а %s - %d\n", r.getAttacker().getName(), r.getAttackerInitiative(),
                    r.getDefender().getName(), r.getDefenderInitiative());
            for(Turn t: r.getTurns()){
                System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", t.getTurnAttacker().getName(),
                        t.getAttack(), t.getTurnDefender().getName(), t.getEvasion());
                System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", t.getDamage(), t.getDamageIgnore(), t.getRealDamage());
                System.out.printf("Остается у %s - %d, а у противника - %d\n\n",t.getTurnAttacker().getName(),
                        t.getTurnAttacker().getCurrentHp(),
                        t.getTurnDefender().getCurrentHp());
            }
        }
        System.out.printf("Победил %s, проиграл %s\n", fight.getWinner().getName(), fight.getLoser().getName());
        System.out.println("======================");

            return ResponseEntity.ok(fight);
    }

    @GetMapping("/fight/statistic")
    public ResponseEntity<String> getStatistics(@RequestParam(defaultValue = "100000") Integer tests,
                                                     @RequestParam(defaultValue = "1") Integer bonus) {

        int wins = 0;
        int numRounds = 0;

        Fighter baseFighter1 = fighterService.getFromHero(1L);
        Fighter baseFighter2 = fighterService.getRandomFromEnemy();

        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateEquals = (wins / (double) tests) * 100;
        double equalsAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = new Fighter(baseFighter2);

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateStrength = (wins / (double) tests) * 100;
        double strengthAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5 + bonus, 5));
            Fighter fighter2 = new Fighter(baseFighter2);

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateDexterity = (wins / (double) tests) * 100;
        double dexterityAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5, 5 + bonus));
            Fighter fighter2 = new Fighter(baseFighter2);

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateConstitution = (wins / (double) tests) * 100;
        double constitutionAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5, 5 + bonus, 5));

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateStrVsDex = (wins / (double) tests) * 100;
        double strVsDexAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5 + bonus, 5, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5, 5, 5 + bonus));

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateStrVsConst = (wins / (double) tests) * 100;
        double strVsConstAvgNumRounds = numRounds / (double) tests;

        numRounds = 0;
        wins = 0;
        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = fighterService.getWithNewCharacteristics(baseFighter1, new Characteristics(5, 5 + bonus, 5));
            Fighter fighter2 = fighterService.getWithNewCharacteristics(baseFighter2, new Characteristics(5 , 5, 5 + bonus));

            Fight fight = fightService.start(fighter1, fighter2);

            if (fight.getWinner().getName().equals(fighter1.getName())) {
                wins++;
            }
            numRounds = numRounds + fight.getRounds().size();
        }
        double winRateDexVsConst = (wins / (double) tests) * 100;
        double dexVsConstAvgNumRounds = numRounds / (double) tests;

        return ResponseEntity.ok("Equals: " + winRateEquals + "%, avgNumRounds: " + equalsAvgNumRounds + "\n" +
                "Strength +1: " + winRateStrength + "%, avgNumRounds: " + strengthAvgNumRounds + "\n" +
                "Dexterity +1: " + winRateDexterity + "%, avgNumRounds: " + dexterityAvgNumRounds + "\n" +
                "Constitution +1: " + winRateConstitution + "%, avgNumRounds: " + constitutionAvgNumRounds + "\n" +
                "StrVsDex: " + winRateStrVsDex + "%, avgNumRounds: " + strVsDexAvgNumRounds + "\n" +
                "StrVsConst: " + winRateStrVsConst + "%, avgNumRound: " + strVsConstAvgNumRounds + "\n" +
                "DexVsConst: " + winRateDexVsConst + "%, avgNumRound: " + dexVsConstAvgNumRounds + "\n");
    }
}
