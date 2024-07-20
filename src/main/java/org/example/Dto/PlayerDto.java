package org.example.Dto;

import org.example.Bot.NPC;

public class PlayerDto {
    private final int playerNumber;
    private final String playerSymbol;
    private final String playerColor;
    private boolean isNPC;
    private NPC npc;

    public PlayerDto(int playerNumber, String playerSymbol) {
        this.playerNumber = playerNumber;
        this.playerSymbol = playerSymbol;
        playerColor = "X".equals(playerSymbol) ? "blue" : "red";
        this.isNPC = false;
        this.npc = null;
    }

    public PlayerDto(int playerNumber, String playerSymbol, NPC npc) {
        this.playerNumber = playerNumber;
        this.playerSymbol = playerSymbol;
        playerColor = "X".equals(playerSymbol) ? "blue" : "red";
        this.isNPC = true;
        this.npc = npc;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public boolean isNPC() {
        return isNPC;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNPC(NPC npc) {
        isNPC = true;
        this.npc = npc;
    }
}
