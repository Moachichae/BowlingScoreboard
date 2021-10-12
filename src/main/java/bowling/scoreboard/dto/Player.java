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
            frames.add(new Frame());
        }
    }

    public String getName() {
        return name;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame getFrame(int round) {
        return frames.get(round);
    }

    public void setPinOfRoundInFrame(Pin pin, int round){
        Frame frame = getFrame(round);
        frame.setPin(pin);
    }

    public void setTotalOfRoundInFrame(int total, int round){
        Frame frame = getFrame(round);
        frame.setTotal(total);
    }
    public int getTotalOfRoundInFrame(int round){
        return getFrame(round).getTotal();
    }

    public Pin getPinOfRoundInFrame(int round){
        return getFrame(round).getPin();
    }


}
