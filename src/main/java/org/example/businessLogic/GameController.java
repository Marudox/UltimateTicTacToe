package org.example.businessLogic;

import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.gui.PlayBoard;
import org.example.dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.businessLogic.SituationCheck.*;

public class GameController {

    private List<SmallGridDto> grid = new ArrayList<>();
    private final PlayBoard playBoard;
    private final PlayerDto playerOne;
    private final PlayerDto playerTwo;
    private PlayerDto currentPlayer;
    private final Modes mode;

    public GameController(PlayBoard playBoard, Modes mode, PlayerDto playerOne, PlayerDto playerTwo) {
        this.playBoard = playBoard;
        this.mode = mode;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
    }

    public GameController() {
        this(null, Modes.TEST, new PlayerDto(1, "X"), new PlayerDto(2, "O"));
    }


    public void pressButton(SmallGridDto smallGridDto, ButtonDto buttonDto) {
        JButton button = buttonDto.getButton();
        if (!currentPlayer.isNPC()) {
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
        button.setText(currentPlayer.getPlayerSymbol());
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
        } else if (tieCheck(grid)) {
            playBoard.showTieDialog();
        } else {
            if (currentPlayer == playerOne) {
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }

            playBoard.updateCurrentPlayer(currentPlayer);

            if (currentPlayer.isNPC()) {
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
                if (!button.isPressed()) {
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
            for (int i = 0; i < 9; i++) {
                if (i % 2 != 0) {
                    smallField.get(i).getButton().setBackground(player.getPlayerColor());
                }
            }
        } else if (player.equals(playerOne)) {
            for (int i = 0; i < 9; i++) {
                if (i % 2 == 0) {
                    smallField.get(i).getButton().setBackground(player.getPlayerColor());
                }
            }
        }
    }

    public List<SmallGridDto> getGrid() {
        return grid;
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public PlayerDto getPlayer(int player) {
        if (player == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public void createGrid() {
        grid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            grid.add(new SmallGridDto(new ArrayList<>(), true));
        }
    }
}
