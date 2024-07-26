package org.example.bot;

import org.example.businessLogic.GameController;
import org.example.Difficulty;
import org.example.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class NPC {
    private final GameController gameController;
    private final Difficulty difficulty;

    public NPC(GameController gameController, Difficulty difficulty) {
        this.gameController = gameController;
        this.difficulty = difficulty;
    }

    public void makeMove() {
        int[] bestMove = giveBestMove();

        if (bestMove == null) {
            return;
        }
        gameController.pressButton(gameController.getGrid().get(bestMove[0]), gameController.getGrid().get(bestMove[0]).getSmallGrid().get(bestMove[1]));
    }

    private int[] giveBestMove() {
        List<int[]> moves = Simulation.getListOfMoves(gameController.getGrid());
        if (!moves.isEmpty()) {
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
                return moves.get(highestValueIndex);
            }
        }
        return null;
    }

    private List<int[]> evaluateMoves(List<int[]> moves) {
        List<int[]> results = new ArrayList<>();
        moves.forEach(move -> results.add(evaluateMove(move)));
        return results;
    }

    private int[] evaluateMove(int[] move) {
        Simulation simulation = new Simulation(gameController, difficulty);
        PlayerDto player = gameController.getCurrentPlayer();
        simulation.makePlanedMove(move);
        return simulation.simulate(player);

    }
}
