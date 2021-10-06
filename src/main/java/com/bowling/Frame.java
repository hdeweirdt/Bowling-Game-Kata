package com.bowling;

import java.util.Optional;

public class Frame {
    private static final int ALL_PINS_DOWN = 10;

    private Optional<Integer> firstThrow;
    private Optional<Integer> secondThrow;
    private int bonus = 0;

    public Frame() {
        firstThrow = Optional.empty();
        secondThrow = Optional.empty();
    }

    public void addRoll(int numberOfPinsKnockedDown) {
        if (firstThrow.isEmpty()) {
            firstThrow = Optional.of(numberOfPinsKnockedDown);
        } else if (secondThrow.isEmpty()) {
            secondThrow = Optional.of(numberOfPinsKnockedDown);
        } else {
            throw new IllegalStateException("Frame was already played!");
        }
    }

    public void calculateBonus(Frame nextFrame) {
        if (isSpare()) {
            bonus = nextFrame.getFirstThrow();
        }
    }

    private boolean isSpare() {
        return firstThrow.map(score -> score != ALL_PINS_DOWN).orElse(false) && getBaseScore() == ALL_PINS_DOWN;
    }

    public int getFirstThrow() {
        return firstThrow.orElse(0);
    }

    public int getSecondThrow() {
        return secondThrow.orElse(0);
    }

    private boolean isStrike() {
        return firstThrow.map(score -> score == ALL_PINS_DOWN).orElse(false);
    }

    public int getScore() {
        return getBaseScore() + bonus;
    }

    private int getBaseScore() {
        return firstThrow.orElse(0) + secondThrow.orElse(0);
    }

    public boolean isPlayed() {
        return firstThrow.isPresent() && secondThrow.isPresent();
    }
}
