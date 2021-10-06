package com.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private static final int FRAMES_IN_GAME = 10;
    private int score;

    private List<Frame> frames = new ArrayList<>(FRAMES_IN_GAME);
    private int currentFrame;

    public Game() {
        currentFrame = 0;
        frames.add(0,new Frame());
    }

    public void roll(int numberOfPinsKnockedDown) {
        getCurrentFrame().addRoll(numberOfPinsKnockedDown);

        getPreviousFrame().ifPresent(frame -> frame.calculateBonus(getCurrentFrame()));

        if(getCurrentFrame().isPlayed()) {
            advanceToNextFrame();
        }
    }

    private void advanceToNextFrame() {
        currentFrame++;
        frames.add(currentFrame, new Frame());
    }

    private Frame getCurrentFrame()  {
        return frames.get(currentFrame);
    }

    private Optional<Frame> getPreviousFrame()  {
        if(currentFrame > 0) {
            return Optional.of(frames.get(currentFrame-1));
        } else {
            return Optional.empty();
        }
    }

    public int score() {
        return frames.stream()
                .map(Frame::getScore)
                .mapToInt(Integer::intValue)
                .sum();
    }
}