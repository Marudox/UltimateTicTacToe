package org.example.dto;

import javax.swing.*;

public class ButtonDto {
    private final JButton button;
    private PlayerDto player;
    private boolean played;

    public ButtonDto(JButton button) {
        this.button = button;
        this.player = null;
        this.played = false;
    }

    public ButtonDto(PlayerDto player, boolean played) {
        this.played = played;
        this.player = player;
        this.button = null;
    }

    public JButton getButton() {
        return button;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
}
