package org.example.businessLogic;

import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;

import java.util.List;

public class SituationCheck {

    public static boolean checkBigWin(List<SmallGridDto> grid) {
        return checkBigColumnWin(grid) || checkBigRowWin(grid) || checkBigDiagonalWin(grid);
    }

    public static boolean checkBigDiagonalWin(List<SmallGridDto> grid) {
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

    public static boolean checkBigRowWin(List<SmallGridDto> grid) {
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

    public static boolean checkBigColumnWin(List<SmallGridDto> grid) {
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
        return checkSmallRowWin(smallField) || checkSmallColumnWin(smallField) || checkDiagonalSmallWin(smallField);
    }

    public static boolean checkDiagonalSmallWin(List<ButtonDto> grid) {
        if (grid.get(4).isPressed()) {
            PlayerDto winner = grid.get(4).getPlayer();
            if (grid.get(0).isPressed() && grid.get(0).getPlayer().equals(winner)) {
                return grid.get(8).isPressed() && grid.get(8).getPlayer().equals(winner);
            } else if (grid.get(2).isPressed() && grid.get(2).getPlayer().equals(winner)) {
                return grid.get(6).isPressed() && grid.get(6).getPlayer().equals(winner);
            }
        }
        return false;
    }

    public static boolean checkSmallColumnWin(List<ButtonDto> smallField) {
        return checkSmallColumn(smallField, 0) || checkSmallColumn(smallField, 1) || checkSmallColumn(smallField, 2);
    }

    private static boolean checkSmallColumn(List<ButtonDto> grid, int column) {
        if (grid.get(column + 3).isPressed()) {
            PlayerDto winner = grid.get(column + 3).getPlayer();
            if (grid.get(column).isPressed() && grid.get(column).getPlayer().equals(winner)) {
                return grid.get(column + 6).isPressed() && grid.get(column + 6).getPlayer().equals(winner);
            }
        }
        return false;
    }

    public static boolean checkSmallRowWin(List<ButtonDto> smallField) {
        return checkSmallRow(smallField, 0) || checkSmallRow(smallField, 1) || checkSmallRow(smallField, 2);
    }

    private static boolean checkSmallRow(List<ButtonDto> grid, int row) {
        row *= 3;
        if (grid.get(row + 1).isPressed()) {
            PlayerDto winner = grid.get(row + 1).getPlayer();
            if (grid.get(row).isPressed() && grid.get(row).getPlayer().equals(winner)) {
                return grid.get(row + 2).isPressed() && grid.get(row + 2).getPlayer().equals(winner);
            }
        }
        return false;
    }

    public static boolean tieCheck(List<SmallGridDto> grid) {
        boolean isTie = true;
        for (SmallGridDto smallGridDto : grid) {
            if (!smallGridDto.isWon()) {
                boolean hasUnpressedButton = smallGridDto.getSmallGrid().stream()
                        .anyMatch(buttonDto -> !buttonDto.isPressed());
                if (hasUnpressedButton) {
                    isTie = false;
                    break;
                }
            }
        }
        return isTie;
    }
}
