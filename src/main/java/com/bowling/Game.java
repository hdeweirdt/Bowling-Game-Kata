package com.bowling;

public class Game {
    private int score;

    public void roll(int numberOfPinsKnockedDown) {
       score += numberOfPinsKnockedDown;
    }

    public int score() {
        return score;
    }
}