package org.example.dto;

import org.example.bot.NPC;

import java.awt.*;

public class PlayerDto {
    private final int playerNumber;
    private final String playerSymbol;
    private final Color playerColor;
    private boolean isNPC;
    private NPC npc;

    public PlayerDto(int playerNumber, String playerSymbol) {
        this.playerNumber = playerNumber;
        this.playerSymbol = playerSymbol;
        playerColor = "X".equals(playerSymbol) ? Color.BLUE: Color.RED;
        this.isNPC = false;
        this.npc = null;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public Color getPlayerColor() {
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
