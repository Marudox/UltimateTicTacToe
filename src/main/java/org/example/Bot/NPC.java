package org.example.Bot;

import org.example.BusinessLogic.GameController;
import org.example.Dto.PlayerDto;
import org.example.Dto.SmallGridDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPC {
    GameController gameController;
    public NPC(GameController gameController) {
        this.gameController = gameController;
    }

    public void makeMove() {
        int[] bestMove = giveBestMove();

        assert bestMove != null;
        gameController.pressButton(gameController.getGrid().get(bestMove[0]), gameController.getGrid().get(bestMove[0]).getSmallGrid().get(bestMove[1]));
    }

    private int[] giveBestMove() {
        List<int[]> moves = Simulation.getListOfMoves(gameController.getGrid());
        List<int[]> movesResults = evaluateMoves(moves);
        if (!movesResults.isEmpty()) {
            int highestValue = -1;
            int highestValueIndex = -1;
            for (int i = 0; i < movesResults.size(); i++) {
                if (movesResults.get(i)[2] > highestValue) {
                    highestValue = movesResults.get(i)[2];
                    highestValueIndex = i;
                }
            }
            movesResults.forEach(move -> System.out.println(Arrays.toString(move)));
            moves.forEach(move -> System.out.println(Arrays.toString(move)));
            return moves.get(highestValueIndex);
        }
        return null;
    }

    private List<int[]> evaluateMoves(List<int[]> moves) {
        List<SmallGridDto> originalGrid = gameController.getGrid();
        List<int[]> results = new ArrayList<>();
        moves.forEach(move -> results.add(evaluateMove(originalGrid, move)));
        return results;
    }

    private int[] evaluateMove(List<SmallGridDto> originalGrid, int[] move) {
        Simulation simulation = new Simulation(gameController, originalGrid);
        PlayerDto player = gameController.getCurrentPlayer();
        simulation.makeMove(move);
        return simulation.simulate(player);

    }
}
