package org.example.businessLogic;

import org.example.Modes;
import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;
import org.example.gui.PlayBoard;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller {
    protected List<SmallGridDto> grid = new ArrayList<>();
    protected final PlayBoard playBoard;
    protected final PlayerDto playerOne;
    protected final PlayerDto playerTwo;
    protected PlayerDto currentPlayer;
    protected final Modes mode;

    public Controller(PlayBoard playBoard, Modes mode, PlayerDto playerOne, PlayerDto playerTwo) {
        this.playBoard = playBoard;
        this.mode = mode;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
        createGrid();
    }

    public void disableButtons() {
        grid.forEach(smallGridDto -> {
            List<ButtonDto> smallField = smallGridDto.getSmallGrid();
            if (smallField != null) {
                smallField.forEach(button -> {
                    if (button.getButton() != null) {
                        button.getButton().setEnabled(false);
                    }
                });
                smallGridDto.setActive(false);
            }
        });
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
                if (!button.isPressed() && button.getButton() != null) {
                    button.getButton().setEnabled(true);
                }
            });
            smallField.setActive(true);
        } else {
            enableAllField();
        }
    }

    public void createGrid() {
        grid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<ButtonDto> smallField = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                smallField.add(new ButtonDto(null, false));
            }
            grid.add(new SmallGridDto(smallField, true));
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

    public void setGrid(List<SmallGridDto> grid) {
        this.grid = grid;
    }
}
