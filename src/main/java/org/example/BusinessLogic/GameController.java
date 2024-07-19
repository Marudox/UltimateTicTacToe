package org.example.BusinessLogic;

import org.example.Bot.NPC;
import org.example.Dto.PlayerDto;
import org.example.GUI.PlayBoard;
import org.example.Dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.BusinessLogic.WinCheck.checkBigWin;
import static org.example.BusinessLogic.WinCheck.checkSmallWin;

public class GameController {

    private List<SmallGridDto> grid = new ArrayList<>();
    private final PlayBoard playBoard;
    private PlayerDto currentPlayer;
    private final PlayerDto playerOne = new PlayerDto(1, "X");
    private final PlayerDto playerTwo = new PlayerDto(2, "O");
    private final Modes mode;
    private NPC npc;

    public GameController() {
        this.playBoard = new PlayBoard(Modes.PVC);
        this.mode = Modes.PVC;
    }

    public GameController(PlayBoard playBoard, Modes mode) {
        this.playBoard = playBoard;
        this.mode = mode;
    }

    public void pressButton(SmallGridDto smallGridDto, int row, int column) {
        JButton button = smallGridDto.getSmallGrid()[row][column];
        boolean npcTurn = false;
        if (currentPlayer == playerOne) {
            button.setText("O");
            if (mode == Modes.PVC) {
                npcTurn = true;
                playBoard.setEnabled(false);
            }
        } else {
            button.setText("X");
            if (mode == Modes.PVC) {
                playBoard.setEnabled(true);
            }
        }

        button.setEnabled(false);

        disableButtons();

        if (checkSmallWin(smallGridDto)) {
            smallFieldCompleted(smallGridDto.getSmallGrid(), currentPlayer);
            int fieldIndex = grid.indexOf(smallGridDto);
            grid.get(fieldIndex).setWinner(currentPlayer);
        }

        enableNextField(row, column);

        if (checkBigWin(grid)) {
            playBoard.showWinDialog(currentPlayer);
            disableButtons();
        }

        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }

        if (npcTurn) {
            npc.makeMove(row, column);
        }
    }

    public void setGrid(List<SmallGridDto> grid) {
        this.grid = grid;
    }

    private void enableNextField(int j, int k) {
        if (j < 1) {
            enableField(j + k);
        } else if (j < 2) {
            enableField(j +2 + k);
        } else {
            enableField(j +4 + k);
        }
    }

    private void enableAllField() {
        for (int i = 0; i < 9; i++) {
            enableField(i);
        }
    }

    public void enableField(int field) {
        if (grid.get(field) != null && !grid.get(field).isWon()) {
            JButton[][] smallField = grid.get(field).getSmallGrid();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (fieldIsPlayed(smallField[j][k])) {
                        smallField[j][k].setEnabled(true);
                    }
                }
            }
        } else {
            enableAllField();
        }
    }

    public void disableButtons() {
        grid.forEach(smallGridDto -> {
            JButton[][] smallField = smallGridDto.getSmallGrid();
            if (smallField != null) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        smallField[j][k].setEnabled(false);
                    }
                }
                smallGridDto.setActive(false);
            }
        });
    }



    private void smallFieldCompleted(JButton[][] smallField, PlayerDto player) {
        Arrays.stream(smallField).forEach(field -> Arrays.stream(field).forEach(button -> button.setText("")));
        if (player.equals(playerTwo)) {
            smallField[0][1].setBackground(Color.RED);
            smallField[1][0].setBackground(Color.RED);
            smallField[1][2].setBackground(Color.RED);
            smallField[2][1].setBackground(Color.RED);
        } else if (player.equals(playerOne)) {
            smallField[0][0].setBackground(Color.BLUE);
            smallField[0][2].setBackground(Color.BLUE);
            smallField[1][1].setBackground(Color.BLUE);
            smallField[2][0].setBackground(Color.BLUE);
            smallField[2][2].setBackground(Color.BLUE);
        }
    }

    public static boolean fieldIsPlayed(JButton button) {
        return button.getText().equals("O") || button.getText().equals("X");
    }

    public List<SmallGridDto> getGrid() {
        return grid;
    }

    public void setNPC(NPC npc) {
        this.npc = npc;
    }
}
