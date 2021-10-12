package bowling.scoreboard.dto;

public class Pin {
    private final int firstTurn;
    private final int secondTurn;
    private final int thirdSTurn;
    private boolean strike;
    private boolean twoStrike;
    private boolean spare;


    public Pin(int firstTurn, int secondTurn, int thirdSTurn) {
        this.firstTurn = firstTurn;
        this.secondTurn = secondTurn;
        this.thirdSTurn = thirdSTurn;
        if (firstTurn == 10)
            this.strike = true;
        else if (firstTurn + secondTurn == 10)
            this.spare = true;

        if (firstTurn + secondTurn == 20)
            this.twoStrike = true;
    }

    public int getFirstTurn() {
        return firstTurn;
    }

    public int getSecondTurn() {
        return secondTurn;
    }

    public int getThirdSTurn() {
        return thirdSTurn;
    }

    public boolean isStrike() {
        return strike;
    }

    public boolean isTwoStrike() {
        return twoStrike;
    }


    public boolean isSpare() {
        return spare;
    }


}
