package org.example.GUI;

import org.example.Bot.NPC;
import org.example.BusinessLogic.GameController;
import org.example.Dto.ButtonDto;
import org.example.Dto.PlayerDto;
import org.example.Dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayBoard extends JFrame implements ActionListener {
    public final int SPACE = 5;
    public final int WIDTH = 50;
    public final int HEIGHT = 50;

    private GameController gameController;

    private JLabel label;
    private JButton button;
    private JButton bRestart;

    public PlayBoard(Modes mode) {
        super("Ultimate Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 500);
        this.setLayout(null);

        button = new JButton();
        button.setBounds(600, 600, 500, 500);
        button.addActionListener(this);
        this.add(button);

        PlayerDto playerOne = new PlayerDto(1, "X");
        PlayerDto playerTwo = new PlayerDto(2, "O");
        if (mode == Modes.PVC) {
            gameController = new GameController(this, mode, playerOne, playerTwo);
            playerTwo.setNPC(new NPC(gameController));
        } else if (mode == Modes.PVP) {
            gameController = new GameController(this, mode, playerOne, playerTwo);
        }

        createBoard();

        label = new JLabel();
        updateCurrentPlayer(gameController.getCurrentPlayer());
        label.setBounds(500, 20, 100, 20);
        this.add(label);

        bRestart = new JButton("Restart");
        bRestart.setBounds(500, 400, 100, 30);
        bRestart.addActionListener(this);
        this.add(bRestart);

        this.setVisible(true);
    }

    private void createBoard() {
        List<SmallGridDto> grid = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            grid.add(createButtonGrid(i + 1));
        }
        gameController.setGrid(grid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.getGrid().forEach(smallGridDto -> {
            if (smallGridDto != null && !smallGridDto.isWon()) {
                List<ButtonDto> smallField = smallGridDto.getSmallGrid();
                smallField.forEach(button -> {
                    if (e.getSource() == button.getButton()) {
                        gameController.pressButton(smallGridDto, button);
                    }
                });
            }
        });
        if (e.getSource() == button) {
            showWinDialog(new PlayerDto(1, "X"));
        } else if (e.getSource() == bRestart) {
            restart();
        }
    }

    private void restart() {
        deleteOldBoard();
        createBoard();
        gameController.restart();
        updateCurrentPlayer(gameController.getCurrentPlayer());
        this.update(this.getGraphics());
    }


    private SmallGridDto createButtonGrid(int index) {
        JButton[][] grid = new JButton[3][3];
        List<ButtonDto> buttons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new JButton();
                if (index <= 3) {
                    grid[i][j].setBounds(WIDTH * j + (3*WIDTH+SPACE)*(index-1), HEIGHT * i, WIDTH, HEIGHT);
                } else if (index <= 6) {
                    grid[i][j].setBounds(WIDTH * j + (3*WIDTH+SPACE)*(index-4), HEIGHT * i + (3*WIDTH+SPACE), WIDTH, HEIGHT);
                } else {
                    grid[i][j].setBounds(WIDTH * j + (3*WIDTH+SPACE)*(index-7), HEIGHT * i + 2*(3*WIDTH+SPACE), WIDTH, HEIGHT);
                }

                grid[i][j].setEnabled(true);
                grid[i][j].addActionListener(this);
                this.add(grid[i][j]);
                buttons.add(new ButtonDto(grid[i][j]));
            }
        }
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            if (i <= 3) {
                button.setBounds(WIDTH * i + SPACE, HEIGHT * i + SPACE, WIDTH, HEIGHT);
            } else if (i <= 6) {
                button.setBounds(WIDTH * i + SPACE, HEIGHT * i + SPACE, WIDTH, HEIGHT);
            } else {
                button.setBounds(WIDTH * i + SPACE, HEIGHT * i + SPACE, WIDTH, HEIGHT);

            }
        }
        return new SmallGridDto(buttons, true);
    }


    public void showWinDialog(PlayerDto player) {
        restart();
        JOptionPane.showMessageDialog(this, "Player " + player.getPlayerNumber() + " won!");
        this.setEnabled(true);
    }

    private void deleteOldBoard() {
        gameController.getGrid().forEach(smallGridDto -> {
            if (smallGridDto != null && !smallGridDto.isWon()) {
                List<ButtonDto> smallField = smallGridDto.getSmallGrid();
                smallField.forEach(button -> this.remove(button.getButton()));
            }
        });
    }

    public GameController getGameController() {
        return gameController;
    }

    public void updateCurrentPlayer(PlayerDto currentPlayer) {
        label.setText("Current player: " + currentPlayer.getPlayerNumber());
    }
}
