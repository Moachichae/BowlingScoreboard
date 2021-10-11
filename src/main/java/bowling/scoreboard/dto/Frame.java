package bowling.scoreboard.dto;

public class Frame {
    private final int round;
    private Pin pin;
    private int total;

    public Frame(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public Pin getPin() {
        return pin;
    }

    public int getTotal() {
        return total;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
