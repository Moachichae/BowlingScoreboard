package bowling.scoreboard.service;

import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;

public class LastScoreCalculator extends ScoreCalculator {

    public void setScore(Player player, int round, int[] score) {
       super.setScoreCalculate(player, round);
       setLastTotal(score);
    }

    private void setLastTotal(int[] score){
        super.setPin(score);
        super.checkPreviousRoundTotal();

        Pin nowPin = player.getPinOfRoundInFrame(round);
        int total = player.getTotalOfRoundInFrame(firstPreviousRound);
        total += nowPin.getFirstTurn() + nowPin.getSecondTurn();
        if(nowPin.isSpare() || nowPin.isStrike())
            total += nowPin.getThirdSTurn();

        if (nowPin.isTwoStrike())
            total += TEN;

        player.setTotalOfRoundInFrame(total,round);
    }





}
