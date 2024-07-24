package org.example.gui;

import org.example.Difficulty;
import org.example.Modes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultySelection extends JFrame implements ActionListener {
    private final JButton bEasy;
    private final JButton bMedium;
    private final JButton bHard;
    private final JButton bExpert;

    public DifficultySelection() {

        super("Ultimate Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setLayout(null);
        this.setVisible(true);

        bEasy = new JButton("Easy");
        bEasy.setBounds(50, 50, 200, 50);
        bEasy.addActionListener(this);
        this.add(bEasy);

        bMedium = new JButton("Medium");
        bMedium.setBounds(50, 150, 200, 50);
        bMedium.addActionListener(this);
        this.add(bMedium);

        bHard = new JButton("Hard");
        bHard.setBounds(300, 50, 200, 50);
        bHard.addActionListener(this);
        this.add(bHard);

        bExpert = new JButton("Expert");
        bExpert.setBounds(300, 150, 200, 50);
        bExpert.addActionListener(this);
        this.add(bExpert);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bEasy) {
            startGame(Difficulty.EASY);
        }
        if (e.getSource() == bMedium) {
            startGame(Difficulty.MEDIUM);
        }
        if (e.getSource() == bHard) {
            startGame(Difficulty.HARD);
        }
        if (e.getSource() == bExpert) {
            startGame(Difficulty.EXPERT);
        }
    }

    private void startGame(Difficulty difficulty) {
        new PlayBoard(Modes.PVC, difficulty);
        this.dispose();
    }
}
