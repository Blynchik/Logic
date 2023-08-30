package com.logic.game.db;

import com.logic.game.model.Enemy;
import com.logic.game.model.Hero;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
public class DataBase {

    private List<Hero> heroes;

    private List<Enemy> enemies;

    public DataBase(){
        heroes = new ArrayList<>();
        enemies = new ArrayList<>();
    }
}
