package com.logic.game.config;

import com.logic.game.db.DataBase;
import com.logic.game.model.Enemy;
import com.logic.game.model.Hero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameLogicConfig {

    @Bean
    public DataBase dataBase() {

        DataBase dataBase = new DataBase();
        addHeroes(dataBase);
        addEnemies(dataBase);

        return dataBase;
    }

    private void addHeroes(DataBase dataBase) {
        dataBase.getHeroes().add(new Hero("Герой", "Героич", "Непобедимый", 5, 5, 5));
        dataBase.getHeroes().add(new Hero("Чушпан", "Наглый", "Завистник", 5, 5,5));
    }

    private void addEnemies(DataBase dataBase) {
        dataBase.getEnemies().add(
                new Enemy("Враг Вражич Бессмертный", 5, 5, 5)
        );
    }
}
