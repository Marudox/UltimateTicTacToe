package org.example.bot;

import org.example.businessLogic.GameController;
import org.example.Difficulty;
import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.businessLogic.SituationCheck.tieCheck;

public class Simulation {

    private final SimulationGameController gameController;
    private List<SmallGridDto> previousGrid;
    private final Difficulty difficulty;

    public Simulation(GameController gameController, Difficulty difficulty) {
        this.previousGrid = SimulationGameController.copyGrid(gameController.getGrid());
        this.gameController = new SimulationGameController(this.previousGrid,
                gameController.getCurrentPlayer(),
                gameController.getPlayer(1),
                gameController.getPlayer(2));
        this.difficulty = difficulty;
    }

    public int[] simulate(PlayerDto movePlayer) {
        if (gameController.isGameOver()) {
            return new int[]{-1, -1, (int) Double.POSITIVE_INFINITY};
        }
        int[] count = new int[3];
        for (int i = 0; i < difficulty.getSimulations(); i++) {
            count[makeRandomMoves(movePlayer) + 1]++;
            gameController.setGrid(previousGrid);
        }
        return count;
    }

    public int makeRandomMoves(PlayerDto movePlayer) {
        while (!gameController.isGameOver()) {
            List<int[]> moves = getListOfMoves(gameController.getGrid());

            if (!moves.isEmpty()) {
                Random random = new Random();
                int randomMove = random.nextInt(moves.size());
                makeMove(moves.get(randomMove));
            }
        }
        if (tieCheck(gameController.getGrid())) {
            return 0;
        }else {
            PlayerDto winner = gameController.getCurrentPlayer();
            if (winner == movePlayer) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public void makeMove(int[] move) {
        SmallGridDto smallGridDto = gameController.getGrid().get(move[0]);
        ButtonDto buttonDto = smallGridDto.getSmallGrid().get(move[1]);

        this.gameController.pressButton(smallGridDto, buttonDto);
    }

    public void makePlanedMove(int[] move) {
        makeMove(move);
        this.previousGrid =  SimulationGameController.copyGrid(this.gameController.getGrid());
    }

    public static List<int[]> getListOfMoves(List<SmallGridDto> grid) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<ButtonDto> smallField = grid.get(i).getSmallGrid();
            if (grid.get(i).isActive() && !grid.get(i).isWon()) {
                for (int j = 0; j < 9; j++) {
                    if (!smallField.get(j).isPressed()) {
                        moves.add(new int[]{i, j});
                    }
                }
            }
        }
        return moves;
    }

    public List<SmallGridDto> getGrid() {
        return gameController.getGrid();
    }

    public SimulationGameController getGameController() {
        return gameController;
    }
}
