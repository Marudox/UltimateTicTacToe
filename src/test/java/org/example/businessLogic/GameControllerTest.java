package org.example.businessLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private final GameController gameController = new GameController();


    @Test
    void enableField() {
        int field = 3;
        gameController.enableField(field);
        assertTrue(gameController.getGrid().get(field).isActive());
    }

    @Test
    void disableButtons() {
        gameController.disableButtons();
        gameController.getGrid().forEach(smallGridDto -> assertFalse(smallGridDto.isActive()));
    }

    @Test
    void checkBigWin() {

    }

    @Test
    void checkSmallWin() {
    }
}