package org.example.gui;

import org.example.Modes;
import org.example.dto.SmallGridDto;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayBoardTest {
    PlayBoard board = new PlayBoard(Modes.TEST);

    @Test
    void isBoardCreated() {
        board.createBoard();
        board.dispose();
        List<SmallGridDto> grid = board.getGameController().getGrid();
        grid.forEach(smallGridDto -> {
            assertEquals(9, smallGridDto.getSmallGrid().size());
            smallGridDto.getSmallGrid().forEach(buttonDto -> assertNotNull(buttonDto.getButton()));
        });
        assertEquals(9, grid.size());
    }

    @Test
    void isButtonPlacementCorrect() {
        board.createBoard();
        board.dispose();
        List<SmallGridDto> grid = board.getGameController().getGrid();
        int y = 0;
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(new Point(x, y), grid.get(i).getSmallGrid().get(j).getButton().getLocation());
                if (j== 2||j==5) {
                    y += PlayBoard.HEIGHT;
                    x -= 2*PlayBoard.WIDTH;
                } else {
                    x += PlayBoard.WIDTH;
                }
            }
            if (i<2) {
                y=0;
            } else if (i<5) {
                y = 3*PlayBoard.HEIGHT + PlayBoard.SPACE;
            } else {
                y = 6 * PlayBoard.HEIGHT + 2 * PlayBoard.SPACE;
            }
            if (i== 2||i==5) {
                x = 0;
            } else {
                x += PlayBoard.SPACE;
            }
        }
    }
}