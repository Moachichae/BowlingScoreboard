package bowling.scoreboard.dto;

public class Frame {
    private Pin pin;
    private int total;

    Pin getPin() {
        return pin;
    }

    int getTotal() {
        return total;
    }

    void setPin(Pin pin) {
        this.pin = pin;
    }

    void setTotal(int total) {
        this.total = total;
    }
}
