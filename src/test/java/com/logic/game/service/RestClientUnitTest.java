package com.logic.game.service;

import com.logic.game.model.db.AppUser;
import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestClientUnitTest {

    Hero successHero;
    Long successId;
    Enemy successEnemy;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    RestClient restClient;
    @Value("${api.rest.url}")
    public String url;

    @BeforeEach
    void setUp() {
        successHero = new Hero(1L,
                "Hero",
                "Hero",
                new AppUser(1L, "TestUser"),
                5,
                5,
                5,
                10,
                100L,
                LocalDateTime.now());

        successEnemy = new Enemy(1L,
                "Enemy",
                "Description",
                5,
                5,
                5);

        successId = 1L;
    }

    @Test
    @DisplayName("Получение героя из рест-приложения: успешное")
    public void getHeroById_Success() throws Exception {
        //given
        Long id = successId;
        Hero expectedHero = successHero;
        ResponseEntity<Hero> expectedResponse = ResponseEntity.ok(expectedHero);
        when(restTemplate.exchange(url + "/hero/" + id, HttpMethod.GET, null, Hero.class))
                .thenReturn(expectedResponse);

        //when
        ResponseEntity<Hero> actualResponse = restClient.getHeroById(id);

        //then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedHero, actualResponse.getBody());
        verify(restTemplate, times(1)).exchange(url + "/hero/" + id, HttpMethod.GET, null, Hero.class);
    }

    @Test
    @DisplayName("Получение героя из рест-приложеня: неуспешное (несуществующий id)")
    public void getHeroById_Fail_WrongId() {
        //given
        Long id = 2L;
        HttpStatus expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpServerErrorException expectedException = new HttpServerErrorException(expectedStatus);
        when(restTemplate.exchange(url + "/hero/" + id, HttpMethod.GET, null, Hero.class))
                .thenThrow(expectedException);

        // when & then
        assertThrows(Exception.class, () -> restClient.getHeroById(id));
        verify(restTemplate, times(1)).exchange(url + "/hero/" + id, HttpMethod.GET, null, Hero.class);
    }

    @Test
    @DisplayName("Получение случайного противника из рест-приложения: успешное")
    public void getRandomEnemy_Success() throws Exception {
        //given
        Enemy expectedEnemy = successEnemy;
        ResponseEntity<Enemy> expectedResponse = ResponseEntity.ok(expectedEnemy);
        when(restTemplate.exchange(url + "/enemy/random", HttpMethod.GET, null, Enemy.class))
                .thenReturn(expectedResponse);

        //when
        ResponseEntity<Enemy> actualResponse = restClient.getRandomEnemy();

        //then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedEnemy, actualResponse.getBody());
        verify(restTemplate, times(1)).exchange(url + "/enemy/random", HttpMethod.GET, null, Enemy.class);
    }

    @Test
    @DisplayName("Получение случайного противника из рест-приложения: неуспешное")
    public void getRandomEnemy_Fail_SomethingWrong() {
        //given
        HttpStatus expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpServerErrorException expectedException = new HttpServerErrorException(expectedStatus);

        when(restTemplate.exchange(url + "/enemy/random", HttpMethod.GET, null, Enemy.class))
                .thenThrow(expectedException);

        // when & then
        assertThrows(Exception.class, () -> restClient.getRandomEnemy());
        verify(restTemplate, times(1)).exchange(url + "/enemy/random", HttpMethod.GET, null, Enemy.class);
    }
}
