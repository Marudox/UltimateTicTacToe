package org.example.Bot;

import org.example.BusinessLogic.GameController;
import org.example.Dto.SmallGridDto;
import org.example.GUI.PlayBoard;

import java.util.Random;

public class NPC {
    GameController gameController;
    public NPC(GameController gameController) {
        this.gameController = gameController;
        gameController.setNPC(this);
    }

    public void makeMove(int j, int k) {
        if (j < 1) {
            makeMove(j + k);
        } else if (j < 2) {
            makeMove(j + 2 + k);
        } else {
            makeMove(j + 4 + k);
        }

    }

    public void makeMove(int grid) {
        int row = new Random().nextInt(3);
        int column = new Random().nextInt(3);

        gameController.pressButton(gameController.getGrid().get(grid), row, column);
    }
}
