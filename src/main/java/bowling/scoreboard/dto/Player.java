package bowling.scoreboard.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Frame> frames;

    public Player(String playerName) {
        this.name = playerName;
        this.frames = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            frames.add(new Frame(i));
        }
    }

    public String getName() {
        return name;
    }

    public Frame getFrame(int round) {
        return frames.get(round);
    }

    public int getFramesCount() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
