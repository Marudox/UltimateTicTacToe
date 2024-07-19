package org.example.Dto;

import javax.swing.*;

public class SmallGridDto {
    private JButton[][] smallGrid;
    private boolean isActive;
    private boolean won = false;
    private PlayerDto winner;


    public SmallGridDto(JButton[][] smallGrid, boolean isActive) {
        this.smallGrid = smallGrid;
        this.isActive = isActive;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public JButton[][] getSmallGrid() {
        return smallGrid;
    }

    public void setSmallGrid(JButton[][] smallGrid) {
        this.smallGrid = smallGrid;
    }

    public boolean isWon() {
        return won;
    }

    public String getWinnerSymbole() {
        return winner.getPlayerSymbol();
    }

    public void setWinner(PlayerDto player) {
        this.winner = player;
        won = true;
    }
}
