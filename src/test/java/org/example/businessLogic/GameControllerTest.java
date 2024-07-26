package org.example.businessLogic;

import org.example.dto.SmallGridDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private final GameController gameController = new GameController();

    @BeforeEach
    void setUp() {
        gameController.createGrid();
    }

    @Test
    void enableField() {
        int field = 3;
        gameController.getGrid().get(field).setActive(false);
        assertFalse(gameController.getGrid().get(field).isActive());
        gameController.enableField(field);
        assertTrue(gameController.getGrid().get(field).isActive());
    }

    @Test
    void disableButtons() {
        gameController.disableButtons();
        gameController.getGrid().forEach(smallGridDto -> assertFalse(smallGridDto.isActive()));
    }

    @Test
    void createGrid() {
        assertEquals(9, gameController.getGrid().size());
    }

    @Test
    void setGameController() {
        List<SmallGridDto> grid = new ArrayList<>();
        gameController.setGrid(grid);
        assertEquals(grid, gameController.getGrid());
    }
}