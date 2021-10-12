package bowling.scoreboard.service;

import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;

public class ScoreCalculator {
    int round;
    Player player;
    int firstPreviousRound;
    int secondPreviousRound;
    int thirdPreviousRound;

    public final int TEN = 10;
    public final int ZERO = 0;

    void setScoreCalculate(Player player, int round) {
        this.player = player;
        this.round = round;
        this.firstPreviousRound = round - 1;
        this.secondPreviousRound = round - 2;
        this.thirdPreviousRound = round - 3;
    }

    public void setScore(Player player, int round, int[] score) {
        setScoreCalculate(player, round);
        setTotal(score);
    }

    private void setTotal(int[] score) {
        setPin(score);
        checkPreviousRoundTotal();
        Pin nowPin = player.getPinOfRoundInFrame(round);
        if (nowPin.isSpare() || nowPin.isStrike()) return; // 현재 라운드에서 스페어나 스트라이크시 다음 라운드에 점수 계산

        int total = nowPin.getFirstTurn() + nowPin.getSecondTurn();
        if (round > 0)
            total += player.getTotalOfRoundInFrame(firstPreviousRound);
        player.setTotalOfRoundInFrame(total, round);
    }

    void setPin(int[] score) {
        player.setPinOfRoundInFrame(new Pin(score[0], score[1], score[2]), round);
    }

    void checkPreviousRoundTotal() {
        if (round >= 2)
            checkSecondPreviousRoundTotal();
        if (round >= 1)
            checkFirstPreviousRoundTotal();
    }

    private void checkFirstPreviousRoundTotal() {
        int previousTotal = player.getTotalOfRoundInFrame(firstPreviousRound);
        if (previousTotal > 0) return;
        setFirstPreviousRoundTotal();
    }

    private void checkSecondPreviousRoundTotal() {
        if (player.getTotalOfRoundInFrame(secondPreviousRound) > 0) return;
        setSecondPreviousRoundTotal();
    }

    private void setFirstPreviousRoundTotal() {
        Pin nowPin = player.getPinOfRoundInFrame(round);
        Pin firstPreviousPin = player.getPinOfRoundInFrame(firstPreviousRound);

        int previousTotal = 0;
        if (firstPreviousPin.isSpare())
            previousTotal = calculateSpareScore();
        else if (firstPreviousPin.isStrike())
            previousTotal += calculateStrikeScore();

        player.setTotalOfRoundInFrame(previousTotal, firstPreviousRound);
    }

    private void setSecondPreviousRoundTotal() { //두 차례전의 total이 0이면 터키이상이다.
        Pin nowPin = player.getPinOfRoundInFrame(round);
        Pin firstBeforePin = player.getPinOfRoundInFrame(firstPreviousRound);
        Pin secondBeforePin = player.getPinOfRoundInFrame(secondPreviousRound);
        int secondPreviousTotal = 0;
        if (firstBeforePin.isStrike() && secondBeforePin.isStrike())
            secondPreviousTotal = calculateDoubleScore();

        player.setTotalOfRoundInFrame(secondPreviousTotal, secondPreviousRound);
    }

    private int calculateSpareScore() {
        int firstPreviousTotal = TEN;
        if (round >= 2)
            firstPreviousTotal += player.getTotalOfRoundInFrame(secondPreviousRound);
        Pin nowPin = player.getPinOfRoundInFrame(round);
        firstPreviousTotal += nowPin.getFirstTurn();
        return firstPreviousTotal;
    }

    private int calculateStrikeScore() {
        int firstPreviousTotal = TEN;
        if (round >= 2)
            firstPreviousTotal += player.getTotalOfRoundInFrame(secondPreviousRound);
        Pin nowPin = player.getPinOfRoundInFrame(round);
        if (nowPin.isStrike() && round < 9) return 0; //이전이 스트라이크고 이번에도 스트라이크면 다음차례에서 계산해야함
        firstPreviousTotal += nowPin.getFirstTurn() + nowPin.getSecondTurn();
        return firstPreviousTotal;
    }

    private int calculateDoubleScore() { // round-2,round-1가 스트라이크일 경우
        int secondPreviousTotal = TEN;
        if (round >= 3)
            secondPreviousTotal += player.getTotalOfRoundInFrame(thirdPreviousRound);
        Pin firstPreviousPin = player.getPinOfRoundInFrame(firstPreviousRound);
        Pin nowPin = player.getPinOfRoundInFrame(round);
        secondPreviousTotal += firstPreviousPin.getFirstTurn();
        secondPreviousTotal += nowPin.getFirstTurn();
        return secondPreviousTotal;
    }

}
