package com.logic.game;

import com.logic.game.db.DataBase;
import com.logic.game.service.DataBaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogicApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogicApplication.class, args);
	}
}
