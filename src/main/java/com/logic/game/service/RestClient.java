package com.logic.game.service;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    private final RestTemplate restTemplate;
    @Value("${api.rest.url}")
    public String url;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Hero> getHeroById(Long id) {
        ResponseEntity<Hero> response = null;
        try {
            response = restTemplate.exchange(url + "/hero/" + id, HttpMethod.GET, null, Hero.class);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return response;
    }

    public ResponseEntity<Enemy> getRandomEnemy() {
        ResponseEntity<Enemy> response = null;
        try {
            response = restTemplate.exchange(url + "/enemy/random", HttpMethod.GET, null, Enemy.class);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return response;
    }
}
