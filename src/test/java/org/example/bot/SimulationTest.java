package org.example.bot;

import org.example.Difficulty;
import org.example.businessLogic.GameController;
import org.example.businessLogic.SituationCheck;
import org.example.dto.ButtonDto;
import org.example.dto.SmallGridDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {
    GameController gameController = new GameController();
    Simulation simulation = new Simulation(gameController, Difficulty.EASY);

    @Test
    void shouldGiveRightAmountOfPossibleMoves() {
        List<SmallGridDto> grid = new ArrayList<>();
        int anzahlMoves = 0;
        for (int i = 0; i < 9; i++) {
            List<ButtonDto> smallField = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (i%2 == 0) {
                    smallField.add(new ButtonDto(null, false));
                    anzahlMoves ++;
                } else {
                    smallField.add(new ButtonDto(null, true));
                }
            }
            grid.add(new SmallGridDto(smallField, true));
        }
        List<int[]> possibleMoves = Simulation.getListOfMoves(grid);
        assertEquals(anzahlMoves, possibleMoves.size());
    }

    @Test
    void shouldMakeTheRightMove() {
        simulation.makeMove(new int[]{0, 0});
        assertTrue(simulation.getGrid().get(0).getSmallGrid().get(0).isPressed());
    }

    @Test
    void simulateShouldGiveAnArrayOf3Elements() {
        int[] possibleMoves = simulation.simulate(gameController.getCurrentPlayer());
        assertEquals(3, possibleMoves.length);
    }

    @Test
    void shouldMakeRandomMovesTillTheGameIsOver() {
        for (int i = 0; i < 100; i++) {
            simulation.makeRandomMoves(gameController.getCurrentPlayer());
            if (!(SituationCheck.checkBigWin(simulation.getGrid()) || SituationCheck.tieCheck(simulation.getGrid()))) {
                assertTrue(simulation.getGameController().isGameOver());
            }
            simulation = new Simulation(gameController, Difficulty.EASY);
        }
    }
}