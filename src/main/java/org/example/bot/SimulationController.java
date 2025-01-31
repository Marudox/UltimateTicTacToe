package org.example.bot;

import org.example.businessLogic.GameController;
import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;
import org.example.Modes;

import java.util.ArrayList;
import java.util.List;

import static org.example.businessLogic.SituationCheck.*;

public class SimulationController extends GameController {

    public SimulationController(List<SmallGridDto> grid, PlayerDto currentPlayer, PlayerDto playerOne, PlayerDto playerTwo) {
        super(null, Modes.PVP, playerOne, playerTwo);
        super.currentPlayer = currentPlayer;
        super.setGrid(copyGrid(grid));
    }

    public static List<SmallGridDto> copyGrid(List<SmallGridDto> grid) {
        List<SmallGridDto> newGrid = new ArrayList<>();
        for (SmallGridDto smallGridDto : grid) {
            List<ButtonDto> newSmallGrid = new ArrayList<>();
            smallGridDto.getSmallGrid().forEach(buttonDto -> newSmallGrid.add(new ButtonDto(buttonDto.getPlayer(), buttonDto.isPressed())));
            newGrid.add(new SmallGridDto(newSmallGrid, smallGridDto.isActive(), smallGridDto.isWon(), smallGridDto.getWinner()));
        }
        return newGrid;
    }

    @Override
    public void pressButton(SmallGridDto smallGridDto, ButtonDto buttonDto) {
        if (checkSmallWin(smallGridDto)) {
            smallGridDto.setWinner(currentPlayer);
        }

        int field = smallGridDto.getSmallGrid().indexOf(buttonDto);

        enableField(field);

        if (checkBigWin(super.getGrid()) || tieCheck(super.getGrid())) {
            return;
        }

        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    public boolean isGameOver() {
        return checkBigWin(super.getGrid()) || tieCheck(super.getGrid());
    }
}
