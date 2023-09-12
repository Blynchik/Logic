package com.logic.game.service;

import org.springframework.stereotype.Component;

@Component
public class RealDamageCalculator {

    public Integer getRealDamage(Integer damage, Integer damageIgnore, Integer armor) {
        return Math.max(damage - armor - damageIgnore, 0);
    }
}
