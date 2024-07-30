package org.example.gui;

import org.example.bot.NPC;
import org.example.businessLogic.GameController;
import org.example.Difficulty;
import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;
import org.example.Modes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayBoard extends JFrame implements ActionListener {
    public static final int SPACE = 5;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private GameController gameController;

    private final JLabel label;
    private final JButton button;
    private final JButton bRestart;

    public PlayBoard(Modes modes) {
        this(modes, Difficulty.EASY);
    }

    public PlayBoard(Modes mode, Difficulty difficulty) {
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
            playerTwo.setNPC(new NPC(gameController, difficulty));
        } else if (mode == Modes.PVP || mode == Modes.TEST) {
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

    public void createBoard() {
        List<SmallGridDto> grid = gameController.getGrid();

        for (int i = 0; i < 9; i++) {
            grid.set(i, createButtonGrid(i+1));
        }
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

    private SmallGridDto createButtonGrid(int index) {
        JButton[][] grid = new JButton[3][3];
        List<ButtonDto> buttons = gameController.getGrid().get(index-1).getSmallGrid();
        buttons.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new JButton();
                if (index <= 3) {
                    grid[i][j].setBounds(WIDTH * j + (3 * WIDTH + SPACE) * (index - 1), HEIGHT * i, WIDTH, HEIGHT);
                } else if (index <= 6) {
                    grid[i][j].setBounds(WIDTH * j + (3 * WIDTH + SPACE) * (index - 4), HEIGHT * i + (3 * WIDTH + SPACE), WIDTH, HEIGHT);
                } else {
                    grid[i][j].setBounds(WIDTH * j + (3 * WIDTH + SPACE) * (index - 7), HEIGHT * i + 2 * (3 * WIDTH + SPACE), WIDTH, HEIGHT);
                }

                grid[i][j].setEnabled(true);
                grid[i][j].addActionListener(this);
                this.add(grid[i][j]);
                buttons.add(new ButtonDto(grid[i][j]));
            }
        }
        return new SmallGridDto(buttons, true);
    }

    private void restart() {
        this.dispose();
        new ModeSelection();
    }

    public void showWinDialog(PlayerDto player) {
        JOptionPane.showMessageDialog(this, "Player " + player.getPlayerNumber() + " won!");
        restart();
    }

    public void updateCurrentPlayer(PlayerDto currentPlayer) {
        label.setText("Current player: " + currentPlayer.getPlayerNumber());
    }

    public void showTieDialog() {
        JOptionPane.showMessageDialog(this, "Tie!");
        restart();
    }

    public GameController getGameController() {
        return gameController;
    }
}
