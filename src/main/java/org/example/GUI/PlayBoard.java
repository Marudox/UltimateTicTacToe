package org.example.GUI;

import org.example.BusinessLogic.GameController;
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

    private final GameController gameController;

    public PlayBoard(Modes mode) {
        super("Ultimate Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        gameController = new GameController(this, mode);

        createBoard();

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
                JButton[][] smallField = smallGridDto.getSmallGrid();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (e.getSource() == smallField[j][k]) {
                            gameController.pressButton(smallGridDto, j, k);
                        }
                    }
                }
            }
        });
    }


    private SmallGridDto createButtonGrid(int index) {
        JButton[][] grid = new JButton[3][3];
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
            }
        }
        return new SmallGridDto(grid, true);
    }


    public void showWinDialog(PlayerDto player) {
        JOptionPane.showMessageDialog(this, "Player " + player.getPlayerSymbol() + " won!");
        deleteOldBoard();
        this.update(this.getGraphics());
        createBoard();
        this.update(this.getGraphics());
    }

    private void deleteOldBoard() {
        gameController.getGrid().forEach(smallGridDto -> {
            if (smallGridDto != null && !smallGridDto.isWon()) {
                JButton[][] smallField = smallGridDto.getSmallGrid();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        this.remove(smallField[j][k]);
                    }
                }
            }
        });
    }

    public GameController getGameController() {
        return gameController;
    }
}
