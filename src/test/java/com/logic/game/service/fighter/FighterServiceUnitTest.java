package com.logic.game.service.fighter;

import com.logic.game.model.db.AppUser;
import com.logic.game.model.db.Enemy;
import com.logic.game.model.db.Hero;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FighterServiceUnitTest {

    Hero hero;
    Enemy enemy;
    Fighter successFighterFromHero;
    Fighter successFighterFromEnemy;
    Long successId;

    @Mock
    RestClient restClient;
    @Mock
    FighterCalculator fighterCalculator;
    @InjectMocks
    FighterService fighterService;

    @BeforeEach
    void setUp() {
        hero = new Hero(1L,
                "Test",
                "Test",
                new AppUser(1L, "TestUser"),
                5,
                5,
                5,
                10,
                100L,
                LocalDateTime.now());

        enemy = new Enemy(1L,
                "Enemy",
                "Description",
                5,
                5,
                5);

        successFighterFromHero = new Fighter("Test",
                "Test",
                new AppUser(1L, "TestUser"),
                new Characteristics(5, 5, 5),
                new Attributes(2, 7, 2, 7, 2, 7, 2, 7, 2, 7, 10, 10),
                10);

        successFighterFromEnemy = new Fighter("Test",
                null,
                null,
                new Characteristics(5, 5, 5),
                new Attributes(2, 7, 2, 7, 2, 7, 2, 7, 2, 7, 10, 10),
                10);

        successId = 1L;
    }

    @Test
    @DisplayName("Получение бойца из героя: успешное")
    public void getFromHero_Success() throws Exception {
        //given
        Long id = successId;
        Fighter expectedFighter = successFighterFromHero;
        Hero expectedHero = hero;
        ResponseEntity<Hero> expectedResponse = ResponseEntity.ok(expectedHero);

        when(restClient.getHeroById(id))
                .thenReturn(expectedResponse);

        when(fighterCalculator.map(expectedResponse.getBody()))
                .thenReturn(expectedFighter);

        //when
        Fighter actualFighter = fighterService.getFromHero(id);

        //then
        assertEquals(expectedFighter, actualFighter);
        verify(restClient, times(1)).getHeroById(id);
        verify(fighterCalculator, times(1)).map(expectedHero);
    }

    @Test
    @DisplayName("Получение бойца из героя: неуспешное (несуществующий id)")
    public void getFromHero_Fail_WrongId() throws Exception {
        //given
        Long id = 2L;
        HttpStatus expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpServerErrorException expectedException = new HttpServerErrorException(expectedStatus);

        when(restClient.getHeroById(id))
                .thenThrow(expectedException);

        // when & then
        assertThrows(Exception.class, () -> fighterService.getFromHero(id));
        verify(restClient, times(1)).getHeroById(id);
    }

    @Test
    @DisplayName("Получение бойца из случайного противника: успешное")
    public void getRandomFromEnemy_Success() throws Exception {
        Fighter expectedFighter = successFighterFromEnemy;
        Enemy expectedEnemy = enemy;
        ResponseEntity<Enemy> expectedResponse = ResponseEntity.ok(expectedEnemy);

        when(restClient.getRandomEnemy())
                .thenReturn(expectedResponse);

        when(fighterCalculator.map(expectedResponse.getBody()))
                .thenReturn(successFighterFromEnemy);

        //when
        Fighter actualFighter = fighterService.getRandomFromEnemy();

        //then
        assertEquals(expectedFighter, actualFighter);
        verify(restClient, times(1)).getRandomEnemy();
        verify(fighterCalculator, times(1)).map(expectedEnemy);
    }

    @Test
    @DisplayName("Получение бойца из случайного противника: неуспешное")
    public void getRandomFromEnemy_Fail_SomethingWrong() throws Exception {
        //given
        HttpStatus expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpServerErrorException expectedException = new HttpServerErrorException(expectedStatus);

        when(restClient.getRandomEnemy())
                .thenThrow(expectedException);


        //when & then
        assertThrows(Exception.class, () -> fighterService.getRandomFromEnemy());
        verify(restClient, times(1)).getRandomEnemy();
    }

    @Test
    @DisplayName("Получение бойца с обновленным HP")
    public void getWithUpdatedHp_Success() {
        //given
        Integer realDamage = 4;
        Fighter expectedFighter = new Fighter(successFighterFromEnemy,
                successFighterFromEnemy.getCharacteristics(),
                successFighterFromEnemy.getAttributes(),
                successFighterFromEnemy.getCurrentHp() - realDamage);

        when(fighterCalculator.getWithUpdatedHp(successFighterFromEnemy, realDamage))
                .thenReturn(expectedFighter);

        //when
        Fighter actualFighter = fighterCalculator.getWithUpdatedHp(successFighterFromEnemy, realDamage);

        //then
        assertEquals(expectedFighter, actualFighter);
        verify(fighterCalculator, times(1)).getWithUpdatedHp(successFighterFromEnemy, realDamage);
    }

    @Test
    @DisplayName("Получение бойца с новыми характеристиками")
    public void getWithNewCharacteristics_Success(){
        //given
        Characteristics newCharacteristics = new Characteristics(6, 5, 5);
        Fighter expectedFighter = new Fighter(successFighterFromHero, newCharacteristics,
                new Attributes(2, 7, 2, 7, 2,7,2,7,3,9,10,10));

        when(fighterCalculator.getWithNewCharacteristics(successFighterFromHero, newCharacteristics))
                .thenReturn(expectedFighter);

        //when
        Fighter actualFighter = fighterService.getWithNewCharacteristics(successFighterFromHero, newCharacteristics);

        //then
        assertEquals(expectedFighter, actualFighter);
        verify(fighterCalculator, times(1)).getWithNewCharacteristics(successFighterFromHero, newCharacteristics);
    }
}
