package org.example.businessLogic;

import org.example.dto.ButtonDto;
import org.example.dto.PlayerDto;
import org.example.dto.SmallGridDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WinCheckTest {

    @Test
    void checkBigWin() {
        List<SmallGridDto> grid = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerOne));
        }
        assertTrue(SituationCheck.checkBigWin(grid));
    }

    @Test
    void checkBigDiagonalWin() {
        List<SmallGridDto> grid = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 4 || i == 8) {
                grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerOne));
            } else {
                grid.add(new SmallGridDto(new ArrayList<>(), true));
            }
        }
        assertTrue(SituationCheck.checkBigDiagonalWin(grid));

        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 4 || i == 6) {
                grid.set(i, new SmallGridDto(new ArrayList<>(), true, true, playerOne));
            } else {
                grid.set(i,new SmallGridDto(new ArrayList<>(), true));
            }
        }
        assertTrue(SituationCheck.checkBigDiagonalWin(grid));
    }

    @Test
    void checkBigFirstRowWin() {
        bigRowTest(0);
    }

    @Test
    void checkBigSecondRowWin() {
        bigRowTest(1);
    }

    @Test
    void checkBigThirdRowWin() {
        bigRowTest(2);
    }

    private static void bigRowTest(int row) {
        List<SmallGridDto> grid = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        row *= 3;
        for (int i = 0; i < row + 3; i++) {
            if (i == row || i == row + 1 || i == row + 2) {
                grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerOne));
            } else {
                grid.add(new SmallGridDto(new ArrayList<>(), true));
            }
        }
        assertTrue(SituationCheck.checkBigRowWin(grid));
    }

    @Test
    void checkBigFirstColumnWin() {
        BigColumnTest(0);
    }

    @Test
    void checkBigSecondColumnWin() {
        BigColumnTest(1);
    }

    @Test
    void checkBigThirdColumnWin() {
        BigColumnTest(2);
    }

    private static void BigColumnTest(int column) {
        List<SmallGridDto> grid = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            if (i == column || i == column + 3 || i == column + 6) {
                grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerOne));
            } else {
                grid.add(new SmallGridDto(new ArrayList<>(), true));
            }
        }
        assertTrue(SituationCheck.checkBigColumnWin(grid));
    }

    @Test
    void checkSmallWin() {
        List<ButtonDto> buttons = new ArrayList<>();
        SmallGridDto smallGridDto = new SmallGridDto(buttons, true, true, new PlayerDto(1, "X"));
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            buttons.add(new ButtonDto(playerOne, true));
        }
        assertTrue(SituationCheck.checkSmallWin(smallGridDto));
    }

    @Test
    void checkDiagonalSmallWin() {
        List<ButtonDto> buttons = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 4 || i == 8) {
                buttons.add(new ButtonDto(playerOne, true));
            } else {
                buttons.add(new ButtonDto(null, false));
            }
        }
        assertTrue(SituationCheck.checkDiagonalSmallWin(buttons));

        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 4 || i == 6) {
                buttons.set(i, new ButtonDto(playerOne, true));
            } else {
                buttons.set(i, new ButtonDto(null, false));
            }
        }
        assertTrue(SituationCheck.checkDiagonalSmallWin(buttons));
    }

    @Test
    void checkSmallFirstColumnWin() {
        smallColumnTest(0);
    }

    @Test
    void checkSmallSecondColumnWin() {
        smallColumnTest(1);
    }

    @Test
    void checkSmallThirdColumnWin() {
        smallColumnTest(2);
    }

    private static void smallColumnTest(int column) {
        List<ButtonDto> buttons = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        for (int i = 0; i < 9; i++) {
            if (i == column || i == column + 3 || i == column + 6) {
                buttons.add(new ButtonDto(playerOne, true));
            } else {
                buttons.add(new ButtonDto(null, false));
            }
        }
        assertTrue(SituationCheck.checkSmallColumnWin(buttons));
    }

    @Test
    void checkSmallFirstRowWin() {
        smallRowTest(0);
    }

    @Test
    void checkSmallSecondRowWin() {
        smallRowTest(1);
    }

    @Test
    void checkSmallThirdRowWin() {
        smallRowTest(2);
    }

    private static void smallRowTest(int column) {
        List<ButtonDto> buttons = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        column *= 3;
        for (int i = 0; i < column + 3; i++) {
            if (i == column || i == column + 1 || i == column + 2) {
                buttons.add(new ButtonDto(playerOne, true));
            } else {
                buttons.add(new ButtonDto(null, false));
            }
        }
        assertTrue(SituationCheck.checkSmallRowWin(buttons));
    }

    @Test
    void checkTie() {
        List<SmallGridDto> grid = new ArrayList<>();
        PlayerDto playerOne = new PlayerDto(1, "X");
        PlayerDto playerTwo = new PlayerDto(2, "O");
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerOne));
            } else {
                grid.add(new SmallGridDto(new ArrayList<>(), true, true, playerTwo));
            }
        }
        assertTrue(SituationCheck.tieCheck(grid));
    }
}