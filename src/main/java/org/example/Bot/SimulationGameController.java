package org.example.Bot;

import org.example.BusinessLogic.GameController;
import org.example.Dto.ButtonDto;
import org.example.Dto.PlayerDto;
import org.example.Dto.SmallGridDto;
import org.example.Modes;

import java.util.ArrayList;
import java.util.List;

import static org.example.BusinessLogic.WinCheck.checkBigWin;
import static org.example.BusinessLogic.WinCheck.checkSmallWin;

public class SimulationGameController extends GameController {

    PlayerDto currentPlayer;
    PlayerDto playerOne;
    PlayerDto playerTwo;

    public SimulationGameController(List<SmallGridDto> grid, PlayerDto currentPlayer, PlayerDto playerOne, PlayerDto playerTwo) {
        super(null, Modes.PVP, playerOne, playerTwo);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = currentPlayer;
        super.setGrid(copyGrid(grid));
    }

    private List<SmallGridDto> copyGrid(List<SmallGridDto> grid) {
        List<SmallGridDto> newGrid = new ArrayList<>();
        for (SmallGridDto smallGridDto : grid) {
            List<ButtonDto> newSmallGrid = new ArrayList<>();
            smallGridDto.getSmallGrid().forEach(buttonDto -> newSmallGrid.add(new ButtonDto(buttonDto.getPlayer(), buttonDto.isPlayed())));
            newGrid.add(new SmallGridDto(newSmallGrid, smallGridDto.isActive(), smallGridDto.isWon(), smallGridDto.getWinner()));
        }
        return newGrid;
    }

    @Override
    public void pressButton(SmallGridDto smallGridDto, ButtonDto buttonDto) {
        buttonDto.setPlayed(true);
        buttonDto.setPlayer(currentPlayer);

        if (checkSmallWin(smallGridDto)) {
            smallGridDto.setWinner(currentPlayer);
        }

        int field = smallGridDto.getSmallGrid().indexOf(buttonDto);

        enableField(field);

        if (checkBigWin(super.getGrid())) {
            return;
        }

        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    public boolean isGameWon() {
        return checkBigWin(super.getGrid());
    }

    public void enableField(int field) {
        if (super.getGrid().get(field) != null && !super.getGrid().get(field).isWon()) {
            super.getGrid().get(field).setActive(true);
        } else {
            enableAllField();
        }
    }

    @Override
    protected void enableAllField() {
        for (int i = 0; i < 9; i++) {
            if (!super.getGrid().get(i).isWon()) {
                enableField(i);
            }
        }
    }

    @Override
    public void setGrid(List<SmallGridDto> grid) {
        super.setGrid(copyGrid(grid));
    }

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }
}
