package com.logic.game.controller;

import com.logic.game.controller.TestController;
import com.logic.game.model.db.AppUser;
import com.logic.game.model.fight.Fight;
import com.logic.game.model.fighter.Attributes;
import com.logic.game.model.fighter.Characteristics;
import com.logic.game.model.fighter.Fighter;
import com.logic.game.service.fight.FightService;
import com.logic.game.service.fighter.FighterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestControllerUnitTest {
    Long successId;
    Fighter successFighterFromHero;
    Fighter successFighterFromEnemy;
    @Mock
    FighterService fighterService;
    @Mock
    FightService fightService;
    @InjectMocks
    TestController testController;

    @BeforeEach
    void setUp() {
        successId = 1L;

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
    }

    @Test
    @DisplayName("Получение бойца из героя из контроллера: успешное")
    void getHeroById_Success() throws Exception {
        //given
        Long id = successId;
        Fighter expectedFighter = successFighterFromHero;
        ResponseEntity<Fighter> expectedResponse = ResponseEntity.ok(expectedFighter);

        when(fighterService.getFromHero(id))
                .thenReturn(expectedFighter);

        //when
        ResponseEntity<Fighter> actualResponse = testController.getHeroById(id);

        //then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFighter, actualResponse.getBody());
        verify(fighterService, times(1)).getFromHero(id);
    }

    @Test
    @DisplayName("Получение бойца из героя из контроллера: неуспешное")
    void getHeroById_Fail_SomethingWrong() throws Exception {
        //given
        Long id = 2L;

        when(fighterService.getFromHero(id))
                .thenThrow(new Exception());

        //when & then
        assertThrows(Exception.class, () -> testController.getHeroById(id));
        verify(fighterService, times(1)).getFromHero(id);
    }

    @Test
    @DisplayName("Получение бойца из противника из контроллера: успешное")
    void getRandomEnemy_Success() throws Exception {
        //given
        Fighter expectedFighter = successFighterFromEnemy;
        ResponseEntity<Fighter> expectedResponse = ResponseEntity.ok(expectedFighter);

        when(fighterService.getRandomFromEnemy())
                .thenReturn(expectedFighter);

        //when
        ResponseEntity<Fighter> actualResponse = testController.getRandomEnemy();

        //then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFighter, actualResponse.getBody());
        verify(fighterService, times(1)).getRandomFromEnemy();
    }

    @Test
    @DisplayName("Получение бойца из противника из контроллера: неуспешное")
    void getRandomEnemy_Fail_SomethingWrong() throws Exception {
        //given
        when(fighterService.getRandomFromEnemy())
                .thenThrow(new Exception());

        //when & then
        assertThrows(Exception.class, () -> testController.getRandomEnemy());
        verify(fighterService, times(1)).getRandomFromEnemy();
    }

    @Test
    @DisplayName("Получение сражения из контроллера: успешное")
    void getFight_Success() throws Exception {
        //given
        Long id = successId;
        Fighter expectedFighter1 = successFighterFromEnemy;
        Fighter expectedFighter2 = successFighterFromHero;
        Fight expectedFight = new Fight(expectedFighter1, expectedFighter2, new LinkedList<>(), expectedFighter2, expectedFighter1);
        ResponseEntity<Fight> expectedResponse = ResponseEntity.ok(expectedFight);

        when(fighterService.getFromHero(id))
                .thenReturn(expectedFighter1);

        when(fighterService.getRandomFromEnemy())
                .thenReturn(expectedFighter2);

        when(fightService.start(expectedFighter1, expectedFighter2))
                .thenReturn(expectedFight);

        //when
        ResponseEntity<Fight> actualResponse = testController.getFight(id);

        //then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFight, actualResponse.getBody());
        verify(fighterService, times(1)).getFromHero(id);
        verify(fighterService, times(1)).getRandomFromEnemy();
        verify(fightService, times(1)).start(expectedFighter1, expectedFighter2);
    }

    @Test
    @DisplayName("Получение сражения из контроллера: неуспешное (не получен первый боец)")
    void getFight_Fail_Fighter1Exception() throws Exception {
        //given
        Long id = 2L;
        Fighter expectedFighter1 = successFighterFromEnemy;
        Fighter expectedFighter2 = successFighterFromHero;

        when(fighterService.getFromHero(2L))
                .thenThrow(new Exception());

        //when & then
        assertThrows(Exception.class, () -> testController.getFight(id));
        verify(fighterService, times(1)).getFromHero(id);
        verify(fighterService, times(0)).getRandomFromEnemy();
        verify(fightService, times(0)).start(expectedFighter1, expectedFighter2);
    }

    @Test
    @DisplayName("Получение сражения из контроллера: неуспешное (не получен второй боец)")
    void getFight_Fail_Fighter2Exception() throws Exception {
        //given
        Long id = successId;
        Fighter expectedFighter1 = successFighterFromEnemy;
        Fighter expectedFighter2 = successFighterFromHero;

        when(fighterService.getFromHero(id))
                .thenReturn(expectedFighter1);

        when(fighterService.getRandomFromEnemy())
                .thenThrow(new Exception());

        //when & then
        assertThrows(Exception.class, () -> testController.getFight(id));
        verify(fighterService, times(1)).getFromHero(id);
        verify(fighterService, times(1)).getRandomFromEnemy();
        verify(fightService, times(0)).start(expectedFighter1, expectedFighter2);
    }
}
