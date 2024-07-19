package org.example.Dto;

public class PlayerDto {
    private final int player;
    private final String playerSymbol;

    public PlayerDto(int player, String playerSymbol) {
        this.player = player;
        this.playerSymbol = playerSymbol;
    }

    public int getPlayer() {
        return player;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }
}
