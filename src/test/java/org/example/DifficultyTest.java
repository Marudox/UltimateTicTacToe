package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifficultyTest {

    @Test
    void getSimulations() {
        assertEquals(100, Difficulty.EASY.getSimulations());
        assertEquals(1000, Difficulty.MEDIUM.getSimulations());
        assertEquals(5000, Difficulty.HARD.getSimulations());
        assertEquals(10000, Difficulty.EXPERT.getSimulations());
    }
}