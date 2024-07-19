package org.example.BusinessLogic;

import org.example.Dto.SmallGridDto;

import javax.swing.*;
import java.util.List;

import static org.example.BusinessLogic.GameController.fieldIsPlayed;

public class WinCheck {

    public static boolean checkBigWin(List<SmallGridDto> grid) {
        return checkBigColumnWin(grid) || checkBigRowWin(grid) || checkBigDiagonalWin(grid);
    }

    private static boolean checkBigDiagonalWin(List<SmallGridDto> grid) {
        if (grid.get(4).isWon()) {
            String winnerSymbole = grid.get(4).getWinnerSymbole();
            if (grid.get(0).isWon() && grid.get(0).getWinnerSymbole().equals(winnerSymbole)) {
                return grid.get(8).isWon() && grid.get(8).getWinnerSymbole().equals(winnerSymbole);
            } else if (grid.get(2).isWon() && grid.get(2).getWinnerSymbole().equals(winnerSymbole)) {
                return grid.get(6).isWon() && grid.get(6).getWinnerSymbole().equals(winnerSymbole);
            }
        }
        return false;
    }

    private static boolean checkBigRowWin(List<SmallGridDto> grid) {
        return checkBigRow(grid, 0) || checkBigRow(grid, 1) || checkBigRow(grid, 2);
    }

    private static boolean checkBigRow(List<SmallGridDto> grid, int row) {
        row *= 3;
        if (grid.get(row + 1).isWon()) {
            String winnerSymbole = grid.get(row + 1).getWinnerSymbole();
            if (grid.get(row).isWon() && grid.get(row).getWinnerSymbole().equals(winnerSymbole)) {
                return grid.get(row + 2).isWon() && grid.get(row + 2).getWinnerSymbole().equals(winnerSymbole);
            }
        }
        return false;
    }

    private static boolean checkBigColumnWin(List<SmallGridDto> grid) {
        return checkBigColumn(grid, 0) || checkBigColumn(grid, 1) || checkBigColumn(grid, 2);
    }

    private static boolean checkBigColumn(List<SmallGridDto> grid, int column) {
        if (grid.get(column + 3).isWon()) {
            String winnerSymbole = grid.get(column + 3).getWinnerSymbole();
            if (grid.get(column).isWon() && grid.get(column).getWinnerSymbole().equals(winnerSymbole)) {
                return grid.get(column + 6).isWon() && grid.get(column + 6).getWinnerSymbole().equals(winnerSymbole);
            }
        }
        return false;
    }



    public static boolean checkSmallWin(SmallGridDto smallGridDto) {
        JButton[][] smallField = smallGridDto.getSmallGrid();
        return checkRowWin(smallField) || checkColumnWin(smallField) || checkDiagonalSmallWin(smallField);
    }

    private static boolean checkDiagonalSmallWin(JButton[][] smallField) {
        if (fieldIsPlayed(smallField[1][1])) {
            String firstFieldText = smallField[1][1].getText();
            if (fieldIsPlayed(smallField[0][0]) && firstFieldText.equals(smallField[0][0].getText())) {
                return fieldIsPlayed(smallField[2][2]) && firstFieldText.equals(smallField[2][2].getText());
            } else if (fieldIsPlayed(smallField[2][0]) && firstFieldText.equals(smallField[2][0].getText())) {
                return fieldIsPlayed(smallField[0][2]) && firstFieldText.equals(smallField[0][2].getText());
            }
        }
        return false;
    }

    private static boolean checkColumnWin(JButton[][] smallField) {
        return checkColumn(smallField[0]) || checkColumn(smallField[1]) || checkColumn(smallField[2]);
    }

    private static boolean checkColumn(JButton[] jButtons) {
        if (fieldIsPlayed(jButtons[1])) {
            String firstFieldText = jButtons[1].getText();
            if (fieldIsPlayed(jButtons[0]) && firstFieldText.equals(jButtons[0].getText())) {
                return fieldIsPlayed(jButtons[2]) && firstFieldText.equals(jButtons[2].getText());
            }
        }
        return false;
    }

    private static boolean checkRowWin(JButton[][] smallField) {
        return checkRow(smallField, 0) || checkRow(smallField, 1) || checkRow(smallField, 2);
    }

    private static boolean checkRow(JButton[][] jButtons, int row) {
        if (fieldIsPlayed(jButtons[1][row])) {
            String firstFieldText = jButtons[1][row].getText();
            if (fieldIsPlayed(jButtons[0][row]) && firstFieldText.equals(jButtons[0][row].getText())) {
                return fieldIsPlayed(jButtons[2][row]) && firstFieldText.equals(jButtons[2][row].getText());
            }
        }
        return false;
    }
}
