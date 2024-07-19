package org.example.GUI;

import org.example.Bot.NPC;
import org.example.Modes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeSelection extends JFrame implements ActionListener {
    private final JButton bPvP;
    private final JButton bPvC;

    public ModeSelection() {
        super("Ultimate Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(null);
        this.setVisible(true);

        bPvP = new JButton("Player vs Player");
        bPvP.setBounds(50, 50, 200, 50);
        bPvP.addActionListener(this);
        this.add(bPvP);

        bPvC = new JButton("Player vs Computer");
        bPvC.setBounds(50, 150, 200, 50);
        bPvC.addActionListener(this);
        this.add(bPvC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bPvP) {
            new PlayBoard(Modes.PVP);
        }
        if (e.getSource() == bPvC) {
            PlayBoard playBoard = new PlayBoard(Modes.PVC);
            new NPC(playBoard.getGameController());
        }
    }
}
