package org.example;

public enum Difficulty {
    EASY(100),
    MEDIUM(1000),
    HARD(5000),
    EXPERT(10000);

    private final int simulations;

    Difficulty(int i) {
        this.simulations = i;
    }

    public int getSimulations() {
        return simulations;
    }
}
