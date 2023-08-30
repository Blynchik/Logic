package com.logic.game.controller;

import com.logic.game.model.*;
import com.logic.game.service.DataBaseClient;
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

    @GetMapping("/test")
    public ResponseEntity<Fight> getTest() {

        Fight fight = new Fight(dataBaseClient.getHero(0), dataBaseClient.getHero(1));
        System.out.println("++++++++++++++++++++++++++++++\n+++++++++++++++++++++++++++++++++");

        for (FightRound fightRound : fight.getRounds()) {
            System.out.println("Раунд " + fightRound.getRoundNum());
            System.out.println("Первым ходит " + fightRound.getAttacker().getName() + ", a вторым - " + fightRound.getDefender().getName());
            FightTurn currentTurn = null;
            for (FightTurn fightTurn : fightRound.getTurns()) {
                currentTurn = fightTurn;
                System.out.println(fightTurn.getAttacker().getName() + " атакует " + fightTurn.getDefender().getName() + " с силой " + fightTurn.getAttack() + ". Но противник уклоняется на " + fightTurn.getEvasion() + " и поэтому получает всего " + fightTurn.getDamage() + " урона. ");
            }
            System.out.println(fightRound.getAttacker().getName() + " имеет " + currentTurn.getAttacker().getHealthPoint() + " очков здоровья, а " + fightRound.getDefender().getName() + " имеет " + currentTurn.getDefender().getHealthPoint());
            System.out.println("======================");
        }
        System.out.println("Победил " + fight.getWinner().getName());

        return ResponseEntity.ok(fight);
    }
}
