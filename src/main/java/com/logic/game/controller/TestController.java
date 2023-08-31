package com.logic.game.controller;

import com.logic.game.model.*;
import com.logic.game.service.DataBaseClient;
import com.logic.game.service.FighterAttributeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logic")
public class TestController {

    private final DataBaseClient dataBaseClient;

    @Autowired
    public TestController(DataBaseClient dataBaseClient) {
        this.dataBaseClient = dataBaseClient;
    }

    @GetMapping("/fight/description")
    public ResponseEntity<Fight> getFightDescription() {

        Fighter fighter1 = new Fighter(dataBaseClient.getHero(0));
        Fighter fighter2 = new Fighter(dataBaseClient.getEnemy(0));

        fighter1.setArmor(0);
        fighter2.setArmor(0);

        Fight fight = new Fight(fighter1, fighter2);
        System.out.println("++++++++++++++++++++++++++++++\n+++++++++++++++++++++++++++++++++");

        for (FightRound fightRound : fight.getRounds()) {
            System.out.println("Раунд " + fightRound.getRoundNum());
            System.out.println("Инициатива " + fightRound.getAttacker().getName() + "- " + fightRound.getAttackerInitiative() +
                    ", а у " + fightRound.getDefender().getName() + "- " + fightRound.getDefenderInitiative());
            System.out.println("Первым ходит " + fightRound.getAttacker().getName() + ", a вторым - " +
                    fightRound.getDefender().getName() + ".\n");
            for (FightTurn fightTurn : fightRound.getTurns()) {
                System.out.println(fightTurn.getAttacker().getName() + " замахивается на "
                        + fightTurn.getDefender().getName() + " с силой " + fightTurn.getAttack()
                        + ", а противник уклоняется на " + fightTurn.getEvasion());

                if (fightTurn.getAttack() > fightTurn.getEvasion()) {
                    System.out.println("Поэтому " + fightTurn.getAttacker().getName() + " попадает, он наносит " + fightTurn.getDamage()
                            + " урона, но противник игнорирует " + fightTurn.getDamageIgnore() + ", блокирует " + fightTurn.getArmor() + " и получает "
                            + fightTurn.getRealDamage() + " урона.");
                } else {
                    System.out.println("Поэтому " + fightTurn.getAttacker().getName() + " промахивается.");
                }

                System.out.println("У " + fightTurn.getDefender().getName() + " остается " +
                        fightTurn.getDefender().getHealthPoint() + " очков здоровья.\n");
            }
            System.out.println(fightRound.getAttacker().getName() + " имеет " + fightRound.getAttacker().getHealthPoint() +
                    " очков здоровья, а " + fightRound.getDefender().getName() +
                    " имеет " + fightRound.getDefender().getHealthPoint());
            System.out.println("======================");
        }
        System.out.println("Победил " + fight.getWinner().getName());

        return ResponseEntity.ok(fight);
    }

    @GetMapping("/fight/statistic")
    public ResponseEntity<String> getStatisticsUsual() {

        int wins = 0;
        int tests = 100_000;
        int numRounds = 0;

        Fighter baseFighter1 = new Fighter("Usual", 5, 5, 5);
        Fighter baseFighter2 = new Fighter("Casual", 5, 5, 5);

        for (int i = 0; i < tests; i++) {
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setStrength(fighter1.getStrength() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setDexterity(fighter1.getDexterity() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setConstitution(fighter1.getConstitution() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setStrength(fighter1.getStrength() + 1);
            fighter2.setDexterity(fighter2.getDexterity() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setStrength(fighter1.getStrength() + 1);
            fighter2.setConstitution(fighter2.getConstitution() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
            Fighter fighter1 = new Fighter(baseFighter1);
            Fighter fighter2 = new Fighter(baseFighter2);

            fighter1.setDexterity(fighter1.getDexterity() + 1);
            fighter2.setConstitution(fighter1.getStrength() + 1);

            Fight fight = new Fight(fighter1, fighter2);

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
