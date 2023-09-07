package com.logic.game.controller;

import com.logic.game.model.fight.Fight;
import com.logic.game.model.fight.Round;
import com.logic.game.model.fight.Turn;
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
        Fighter fighter = fighterService.getFighterFromHero(id);
        return ResponseEntity.ok(fighter);
    }

    @GetMapping("/enemy/random")
    public ResponseEntity<Fighter> getRandomEnemy() {
        Fighter fighter = fighterService.getRandomFighterFromEnemy();
        return ResponseEntity.ok(fighter);
    }

    @GetMapping("/fight")
    public ResponseEntity<Fight> getFight(@RequestParam Long heroId) {
        Fighter fighter1 = fighterService.getFighterFromHero(heroId);
        Fighter fighter2 = fighterService.getRandomFighterFromEnemy();
        Fight fight = fightService.start(fighter1, fighter2);

//        for (Round r : fight.getRounds()){
//            System.out.printf("Раунд %d\n",r.getRoundNum());
//            System.out.printf("Инициатива %s - %d, а %s - %d\n", r.getAttacker().getName(), r.getAttackerInitiative(),
//                    r.getDefender().getName(), r.getDefenderInitiative());
//            for(Turn t: r.getTurns()){
//                System.out.printf("%s атакует - %d, а %s уворачивается - %d\n", t.getTurnAttacker().getName(),
//                        t.getAttack(), t.getTurnDefender().getName(), t.getEvasion());
//                System.out.printf("Наносится урона %d, игнорируется %d, всего %d\n", t.getDamage(), t.getDamageIgnore(), t.getRealDamage());
//                System.out.printf("Остается у %s - %d, а у противника - %d\n\n",t.getTurnAttacker().getName(),
//                        t.getTurnAttacker().getAttributes().getCurrentHp(),
//                        t.getTurnDefender().getAttributes().getCurrentHp());
//            }
//        }
//        System.out.printf("Победил %s, проиграл %s\n", fight.getWinner().getName(), fight.getLoser().getName());
//        System.out.println("======================");

            return ResponseEntity.ok(fight);
    }

//    @GetMapping("/fight/description")
//    public ResponseEntity<Fight> getFightDescription(@RequestParam(defaultValue = "0") Integer armor) {
//
//        Fighter fighter1 = new Fighter(restClient.getHero(0));
//        Fighter fighter2 = new Fighter(restClient.getEnemy(0));
//
//        fighter1.setArmor(armor);
//        fighter2.setArmor(0);
//
//        Fight fight = new Fight(fighter1, fighter2);
//        System.out.println("++++++++++++++++++++++++++++++\n+++++++++++++++++++++++++++++++++");
//
//        for (FightRound fightRound : fight.getRounds()) {
//            System.out.println("Раунд " + fightRound.getRoundNum());
//            System.out.println("Инициатива " + fightRound.getAttacker().getName() + "- " + fightRound.getAttackerInitiative() +
//                    ", а у " + fightRound.getDefender().getName() + "- " + fightRound.getDefenderInitiative());
//            System.out.println("Первым ходит " + fightRound.getAttacker().getName() + ", a вторым - " +
//                    fightRound.getDefender().getName() + ".\n");
//            for (FightTurn fightTurn : fightRound.getTurns()) {
//                System.out.println(fightTurn.getAttacker().getName() + " замахивается на "
//                        + fightTurn.getDefender().getName() + " с силой " + fightTurn.getAttack()
//                        + ", а противник уклоняется на " + fightTurn.getEvasion());
//
//                if (fightTurn.getAttack() >= fightTurn.getEvasion()) {
//                    System.out.println("Поэтому " + fightTurn.getAttacker().getName() + " попадает, он наносит " + fightTurn.getDamage()
//                            + " урона, но противник игнорирует " + fightTurn.getDamageIgnore() + ", блокирует " + fightTurn.getArmor() + " и получает "
//                            + fightTurn.getRealDamage() + " урона.");
//                } else {
//                    System.out.println("Поэтому " + fightTurn.getAttacker().getName() + " промахивается.");
//                }
//
//                System.out.println("У " + fightTurn.getDefender().getName() + " остается " +
//                        fightTurn.getDefender().getHealthPoint() + " очков здоровья.\n");
//            }
//            System.out.println(fightRound.getAttacker().getName() + " имеет " + fightRound.getAttacker().getHealthPoint() +
//                    " очков здоровья, а " + fightRound.getDefender().getName() +
//                    " имеет " + fightRound.getDefender().getHealthPoint());
//            System.out.println("======================");
//        }
//        System.out.println("Победил " + fight.getWinner().getName());
//
//        return ResponseEntity.ok(fight);
//    }
//
//    @GetMapping("/fight/statistic")
//    public ResponseEntity<String> getStatistics(@RequestParam(defaultValue = "100000") Integer tests,
//                                                     @RequestParam(defaultValue = "1") Integer bonus,
//                                                     @RequestParam(defaultValue = "0") Integer armor) {
//
//        int wins = 0;
//        int numRounds = 0;
//
//        Fighter baseFighter1 = new Fighter("Usual", 5, 5, 5);
//        Fighter baseFighter2 = new Fighter("Casual", 5, 5, 5);
//
//        baseFighter1.setArmor(armor);
//        baseFighter2.setArmor(0);
//
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter2.setArmor(fighter1.getArmor());
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateEquals = (wins / (double) tests) * 100;
//        double equalsAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter2.setArmor(fighter1.getArmor());
//
//            fighter1.setStrength(fighter1.getStrength() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateStrength = (wins / (double) tests) * 100;
//        double strengthAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter2.setArmor(fighter1.getArmor());
//
//            fighter1.setDexterity(fighter1.getDexterity() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateDexterity = (wins / (double) tests) * 100;
//        double dexterityAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter2.setArmor(fighter1.getArmor());
//
//            fighter1.setConstitution(fighter1.getConstitution() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateConstitution = (wins / (double) tests) * 100;
//        double constitutionAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter1.setStrength(fighter1.getStrength() + bonus);
//            fighter2.setDexterity(fighter2.getDexterity() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateStrVsDex = (wins / (double) tests) * 100;
//        double strVsDexAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter1.setStrength(fighter1.getStrength() + bonus);
//            fighter2.setConstitution(fighter2.getConstitution() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateStrVsConst = (wins / (double) tests) * 100;
//        double strVsConstAvgNumRounds = numRounds / (double) tests;
//
//        numRounds = 0;
//        wins = 0;
//        for (int i = 0; i < tests; i++) {
//            Fighter fighter1 = new Fighter(baseFighter1);
//            Fighter fighter2 = new Fighter(baseFighter2);
//
//            fighter1.setDexterity(fighter1.getDexterity() + bonus);
//            fighter2.setConstitution(fighter1.getStrength() + bonus);
//
//            Fight fight = new Fight(fighter1, fighter2);
//
//            if (fight.getWinner().getName().equals(fighter1.getName())) {
//                wins++;
//            }
//            numRounds = numRounds + fight.getRounds().size();
//        }
//        double winRateDexVsConst = (wins / (double) tests) * 100;
//        double dexVsConstAvgNumRounds = numRounds / (double) tests;
//
//        return ResponseEntity.ok("Equals: " + winRateEquals + "%, avgNumRounds: " + equalsAvgNumRounds + "\n" +
//                "Strength +1: " + winRateStrength + "%, avgNumRounds: " + strengthAvgNumRounds + "\n" +
//                "Dexterity +1: " + winRateDexterity + "%, avgNumRounds: " + dexterityAvgNumRounds + "\n" +
//                "Constitution +1: " + winRateConstitution + "%, avgNumRounds: " + constitutionAvgNumRounds + "\n" +
//                "StrVsDex: " + winRateStrVsDex + "%, avgNumRounds: " + strVsDexAvgNumRounds + "\n" +
//                "StrVsConst: " + winRateStrVsConst + "%, avgNumRound: " + strVsConstAvgNumRounds + "\n" +
//                "DexVsConst: " + winRateDexVsConst + "%, avgNumRound: " + dexVsConstAvgNumRounds + "\n");
//    }
}
