package org.example.BusinessLogic;

import org.example.Dto.ButtonDto;
import org.example.Dto.PlayerDto;
import org.example.GUI.PlayBoard;
import org.example.Dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.BusinessLogic.WinCheck.checkBigWin;
import static org.example.BusinessLogic.WinCheck.checkSmallWin;

public class GameController {

    private List<SmallGridDto> grid = new ArrayList<>();
    private PlayBoard playBoard;
    private PlayerDto playerOne;
    private PlayerDto playerTwo;
    private PlayerDto currentPlayer;
    private Modes mode;
    public GameController() {

    }

    public GameController(PlayBoard playBoard, Modes mode, PlayerDto playerOne, PlayerDto playerTwo) {
        this.playBoard = playBoard;
        this.mode = mode;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
    }



    public void pressButton(SmallGridDto smallGridDto, ButtonDto buttonDto) {
        JButton button = buttonDto.getButton();
        if (currentPlayer == playerOne) {
            if (mode == Modes.PVC) {
                playBoard.setEnabled(false);
            }
        } else {
            if (mode == Modes.PVC) {
                playBoard.setEnabled(true);
            }
        }
        buttonDto.setPlayed(true);
        buttonDto.setPlayer(currentPlayer);
        button.setText("<html><font color = "+currentPlayer.getPlayerColor()+">"+currentPlayer.getPlayerSymbol()+"</font></html>");
        button.setEnabled(false);

        disableButtons();

        if (checkSmallWin(smallGridDto)) {
            smallFieldCompleted(smallGridDto.getSmallGrid(), currentPlayer);

            smallGridDto.setWinner(currentPlayer);
        }

        int field = smallGridDto.getSmallGrid().indexOf(buttonDto);

        enableField(field);

        if (checkBigWin(grid)) {
            disableButtons();
            playBoard.showWinDialog(currentPlayer);
        } else {
            if (currentPlayer == playerOne) {
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }

            playBoard.updateCurrentPlayer(currentPlayer);

            if (mode == Modes.PVC && currentPlayer.isNPC()) {
                currentPlayer.getNpc().makeMove();
            }
        }
    }

    public void setGrid(List<SmallGridDto> grid) {
        this.grid = grid;
    }

    protected void enableAllField() {
        for (int i = 0; i < 9; i++) {
            if (!grid.get(i).isWon()) {
                enableField(i);
            }
        }
    }

    public void enableField(int field) {
        if (grid.get(field) != null && !grid.get(field).isWon()) {
            SmallGridDto smallField = grid.get(field);
            smallField.getSmallGrid().forEach(button -> {
                if (!button.isPlayed()) {
                    button.getButton().setEnabled(true);
                }
            });
            smallField.setActive(true);
        } else {
            enableAllField();
        }
    }

    public void disableButtons() {
        grid.forEach(smallGridDto -> {
            List<ButtonDto> smallField = smallGridDto.getSmallGrid();
            if (smallField != null) {
                smallField.forEach(button -> button.getButton().setEnabled(false));
                smallGridDto.setActive(false);
            }
        });
    }



    private void smallFieldCompleted(List<ButtonDto> smallField, PlayerDto player) {
        smallField.forEach(button -> button.getButton().setText(""));
        if (player.equals(playerTwo)) {
            smallField.get(1).getButton().setBackground(Color.RED);
            smallField.get(3).getButton().setBackground(Color.RED);
            smallField.get(5).getButton().setBackground(Color.RED);
            smallField.get(7).getButton().setBackground(Color.RED);
        } else if (player.equals(playerOne)) {
            smallField.get(0).getButton().setBackground(Color.BLUE);
            smallField.get(2).getButton().setBackground(Color.BLUE);
            smallField.get(4).getButton().setBackground(Color.BLUE);
            smallField.get(6).getButton().setBackground(Color.BLUE);
            smallField.get(8).getButton().setBackground(Color.BLUE);
        }
    }

    public List<SmallGridDto> getGrid() {
        return grid;
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public void restart() {
        currentPlayer = playerOne;
    }

    public PlayerDto getPlayer(int player) {
        if (player == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }
}
