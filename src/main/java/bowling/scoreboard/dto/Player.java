package bowling.scoreboard.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Frame> frames;

    public Player(String playerName) {
        this.name = playerName;
        this.frames = new ArrayList<>();
      }

      public void addFrame(Frame frame){
        frames.add(frame);
      }

    public String getName() {
        return name;
    }

    public Frame getFrame(int round){
        return frames.get(round);
    }

    public int getFramesCount(){
        return frames.size();
    }
}
