package org.example.businessLogic;

import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;

import java.util.List;

public class SituationCheck {

    public static boolean checkBigWin(List<SmallGridDto> grid) {
        return checkBigColumnWin(grid) || checkBigRowWin(grid) || checkBigDiagonalWin(grid);
    }

    private static boolean checkBigDiagonalWin(List<SmallGridDto> grid) {
        if (grid.get(4).isWon()) {
            PlayerDto winner = grid.get(4).getWinner();
            if (grid.get(0).isWon() && grid.get(0).getWinner().equals(winner)) {
                return grid.get(8).isWon() && grid.get(8).getWinner().equals(winner);
            } else if (grid.get(2).isWon() && grid.get(2).getWinner().equals(winner)) {
                return grid.get(6).isWon() && grid.get(6).getWinner().equals(winner);
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
            PlayerDto winner = grid.get(row + 1).getWinner();
            if (grid.get(row).isWon() && grid.get(row).getWinner().equals(winner)) {
                return grid.get(row + 2).isWon() && grid.get(row + 2).getWinner().equals(winner);
            }
        }
        return false;
    }

    private static boolean checkBigColumnWin(List<SmallGridDto> grid) {
        return checkBigColumn(grid, 0) || checkBigColumn(grid, 1) || checkBigColumn(grid, 2);
    }

    private static boolean checkBigColumn(List<SmallGridDto> grid, int column) {
        if (grid.get(column + 3).isWon()) {
            PlayerDto winner = grid.get(column + 3).getWinner();
            if (grid.get(column).isWon() && grid.get(column).getWinner().equals(winner)) {
                return grid.get(column + 6).isWon() && grid.get(column + 6).getWinner().equals(winner);
            }
        }
        return false;
    }



    public static boolean checkSmallWin(SmallGridDto smallGridDto) {
        List<ButtonDto> smallField = smallGridDto.getSmallGrid();
        return checkRowWin(smallField) || checkColumnWin(smallField) || checkDiagonalSmallWin(smallField);
    }

    private static boolean checkDiagonalSmallWin(List<ButtonDto> grid) {
        if (grid.get(4).isPlayed()) {
            PlayerDto winner = grid.get(4).getPlayer();
            if (grid.get(0).isPlayed() && grid.get(0).getPlayer().equals(winner)) {
                return grid.get(8).isPlayed() && grid.get(8).getPlayer().equals(winner);
            } else if (grid.get(2).isPlayed() && grid.get(2).getPlayer().equals(winner)) {
                return grid.get(6).isPlayed() && grid.get(6).getPlayer().equals(winner);
            }
        }
        return false;
    }

    private static boolean checkColumnWin(List<ButtonDto> smallField) {
        return checkColumn(smallField, 0) || checkColumn(smallField, 1) || checkColumn(smallField, 2);
    }

    private static boolean checkColumn(List<ButtonDto> grid, int column) {
        if (grid.get(column + 3).isPlayed()) {
            PlayerDto winner = grid.get(column + 3).getPlayer();
            if (grid.get(column).isPlayed() && grid.get(column).getPlayer().equals(winner)) {
                return grid.get(column + 6).isPlayed() && grid.get(column + 6).getPlayer().equals(winner);
            }
        }
        return false;
    }

    private static boolean checkRowWin(List<ButtonDto> smallField) {
        return checkRow(smallField, 0) || checkRow(smallField, 1) || checkRow(smallField, 2);
    }

    private static boolean checkRow(List<ButtonDto> grid, int row) {
        row *= 3;
        if (grid.get(row + 1).isPlayed()) {
            PlayerDto winner = grid.get(row + 1).getPlayer();
            if (grid.get(row).isPlayed() && grid.get(row).getPlayer().equals(winner)) {
                return grid.get(row + 2).isPlayed() && grid.get(row + 2).getPlayer().equals(winner);
            }
        }
        return false;
    }

    public static boolean tieCheck(List<SmallGridDto> grid) {
        for (SmallGridDto smallGridDto : grid) {
            if (!smallGridDto.isWon()) {
                return false;
            }
        }
        return true;
    }
}
