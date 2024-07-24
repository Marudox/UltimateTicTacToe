package org.example.dto;

import java.util.List;

public class SmallGridDto {
    private final List<ButtonDto> smallGrid;
    private boolean isActive;
    private boolean won = false;
    private PlayerDto winner;


    public SmallGridDto(List<ButtonDto> smallGrid, boolean isActive) {
        this.smallGrid = smallGrid;
        this.isActive = isActive;
    }

    public SmallGridDto(List<ButtonDto> smallGrid, boolean isActive, boolean won, PlayerDto winner) {
        this.smallGrid = smallGrid;
        this.isActive = isActive;
        this.won = won;
        this.winner = winner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ButtonDto> getSmallGrid() {
        return smallGrid;
    }

    public boolean isWon() {
        return won;
    }

    public PlayerDto getWinner() {
        return winner;
    }

    public void setWinner(PlayerDto player) {
        this.winner = player;
        won = true;
    }
}
