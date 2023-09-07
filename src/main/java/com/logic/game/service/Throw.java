package com.logic.game.service;

import org.springframework.stereotype.Component;

@Component
public class Throw {

    public Integer throwInitiative(Integer minInitiative, Integer maxInitiative) {
        return minInitiative + (int) (Math.random() * (maxInitiative - minInitiative + 1));
    }

    public Integer throwAttack(Integer minAttack, Integer maxAttack) {
        return minAttack + (int) (Math.random() * (maxAttack - minAttack + 1));
    }

    public Integer throwEvasion(Integer minEvasion, Integer maxEvasion) {
        return minEvasion + (int) (Math.random() * (maxEvasion - minEvasion + 1));
    }

    public Integer throwDamage(Integer minDamage, Integer maxDamage) {
        return minDamage + (int) (Math.random() * (maxDamage - minDamage + 1));
    }

    public Integer throwDamageIgnore(Integer minDamageIgnore, Integer maxDamageIgnore) {
        return minDamageIgnore + (int) (Math.random() * (maxDamageIgnore - minDamageIgnore + 1));
    }
}
