package org.example.businessLogic;

import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.gui.PlayBoard;
import org.example.dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.util.List;

import static org.example.businessLogic.SituationCheck.*;

public class GameController extends Controller {

    public GameController(PlayBoard playBoard, Modes mode, PlayerDto playerOne, PlayerDto playerTwo) {
        super(playBoard, mode, playerOne, playerTwo);
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
}
