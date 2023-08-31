package com.logic.game.service;

import com.logic.game.db.DataBase;
import com.logic.game.model.Enemy;
import com.logic.game.model.Fighter;
import com.logic.game.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseClient {

    private final DataBase dataBase;

    @Autowired
    public DataBaseClient(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public Hero getHero(Integer index){
        return dataBase.getHeroes().get(index);
    }

    public Enemy getEnemy(Integer index){
        return dataBase.getEnemies().get(index);
    }
}
