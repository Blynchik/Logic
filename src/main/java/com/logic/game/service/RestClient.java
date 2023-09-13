package com.logic.game.service;

import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Класс RestClient представляет сущность клиента для взаимодействия с удаленным REST API.
 *
 * @Component - аннотация Spring, указывающая, что класс является компонентом и должен быть управляем Spring-контейнером.
 */
@Component
public class RestClient {
    private final RestTemplate restTemplate;

    /**
     * Поле url, содержащее базовый URL для выполнения API запросов.
     */
    @Value("${api.rest.url}")
    public String url;

    /**
     * Конструктор класса RestClient с параметром типа RestTemplate.
     *
     * @param restTemplate - объект класса RestTemplate для выполнения HTTP запросов.
     * @Autowired - аннотация Spring, которая автоматически связывает (инъектирует) объект класса RestTemplate.
     */
    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Метод для получения информации о герое по его идентификатору.
     *
     * @param id - идентификатор героя типа Long.
     * @return объект типа ResponseEntity<Hero>, содержащий ответ от сервера.
     */
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

    /**
     * Метод для получения случайного противника.
     *
     * @return объект типа ResponseEntity<Enemy>, содержащий ответ от сервера.
     */
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
