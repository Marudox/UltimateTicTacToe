package org.example.Bot;

import org.example.BusinessLogic.GameController;
import org.example.Dto.ButtonDto;
import org.example.Dto.PlayerDto;
import org.example.Dto.SmallGridDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    private final SimulationGameController gameController;
    private final List<SmallGridDto> previousGrid;

    public Simulation(GameController gameController, List<SmallGridDto> previousGrid) {
        this.gameController = new SimulationGameController(gameController.getGrid(),
                gameController.getCurrentPlayer(),
                gameController.getPlayer(1),
                gameController.getPlayer(2));
        this.previousGrid = previousGrid;
    }

    public int[] simulate(PlayerDto movePlayer) {
        int[] count = new int[3];
        for (int i = 0; i < 2000; i++) {
            count[makeRandomMove(movePlayer) + 1]++;
            gameController.setGrid(previousGrid);
        }
        return count;
    }

    public int makeRandomMove(PlayerDto movePlayer) {
        while (!gameController.isGameWon()) {
            List<int[]> moves = getListOfMoves(gameController.getGrid());

            if (!moves.isEmpty()) {
                Random random = new Random();
                int randomMove = random.nextInt(moves.size());
                makeMove(moves.get(randomMove));
            } else {
                return 0;
            }
        }
        PlayerDto winner = gameController.getCurrentPlayer();
        if (winner == movePlayer) {
            return 1;
        } else {
            return -1;
        }
    }

    public void makeMove(int[] move) {
        SmallGridDto smallGridDto = gameController.getGrid().get(move[0]);
        ButtonDto buttonDto = smallGridDto.getSmallGrid().get(move[1]);

        gameController.pressButton(smallGridDto, buttonDto);
    }

    public static List<int[]> getListOfMoves(List<SmallGridDto> grid) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<ButtonDto> smallField = grid.get(i).getSmallGrid();
            if (grid.get(i).isActive() && !grid.get(i).isWon()) {
                for (int j = 0; j < 9; j++) {
                    if (!smallField.get(j).isPlayed()) {
                        moves.add(new int[]{i, j});
                    }
                }
            }
        }
        return moves;
    }
}
