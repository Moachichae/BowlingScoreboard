package bowling.scoreboard.dto;

public class Pin {
    private final String firstTurn;
    private final String secondTurn;
    private final String thirdSTurn;

    public Pin(String firstTurn, String secondTurn, String thirdSTurn) {
        this.firstTurn = firstTurn;
        this.secondTurn = secondTurn;
        this.thirdSTurn = thirdSTurn;
    }

    public String getFirstTurn() {
        return firstTurn;
    }

    public String getSecondTurn() {
        return secondTurn;
    }

    public String getThirdSTurn() {
        return thirdSTurn;
    }
}
