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
    }
