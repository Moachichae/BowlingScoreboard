package bowling.scoreboard.service;

import bowling.scoreboard.dto.Frame;
import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;

public class ScoreCalculate {
    private static ScoreCalculate scoreCalculateInstance = null;
    final int ZERO = 0;
    final int TEN = 10;

    private ScoreCalculate() {
    }

    

    public static ScoreCalculate getScoreCalculateInstance() {
        if (scoreCalculateInstance == null)
            scoreCalculateInstance = new ScoreCalculate();
        return scoreCalculateInstance;
    }

    public void setTotal(Player player, int round, int[] score) {
        setPin(player, round, score);
        Frame nowFrame = player.getFrame(round);
        Pin nowPin = player.getFrame(round).getPin();

        if (round >= 2)
            setTurkeyScore(player, round);
        if (round >= 1)
            setFirstBeforeTotal(player, round);

        if (nowPin.isSpare() || nowPin.isStrike()) return;
        int total = nowPin.getFirstTurn() + nowPin.getSecondTurn();
        if (round > 0)
            total += player.getFrame(round - 1).getTotal();
        nowFrame.setTotal(total);

    }

    public void setTurkeyScore(Player player, int round) { //두 차례전의 TOTAL이 0이면 터키이상
        Frame nowFrame = player.getFrame(round);
        Frame firstBeforeFrame = player.getFrame(round - 1);
        Frame secondBeforeFrame = player.getFrame(round - 2);
        if (secondBeforeFrame.getTotal() != 0) return;

        Pin nowPin = nowFrame.getPin();
        Pin firstBeforePin = firstBeforeFrame.getPin();
        Pin secondBeforePin = firstBeforeFrame.getPin();

        int total = (round >= 3) ? player.getFrame(round - 3).getTotal() : 0;
        if (secondBeforePin.isStrike() && firstBeforePin.isStrike()) //포베거 계산
            total += secondBeforePin.getFirstTurn() + firstBeforePin.getSecondTurn() + nowPin.getFirstTurn();

        secondBeforeFrame.setTotal(total);
    }


    public void setFirstBeforeTotal(Player player, int round) {
        Frame firstBeforeFrame = player.getFrame(round - 1);
        Pin firstBeforePin = firstBeforeFrame.getPin();
        if (firstBeforeFrame.getTotal() != 0) return;

        Frame nowFrame = player.getFrame(round - 1);
        Pin nowPin = firstBeforeFrame.getPin();
        if (firstBeforePin.isStrike() && nowPin.isStrike()) return;

        int total = 10;
        if (round >= 2)
            total += player.getFrame(round - 2).getTotal();
        // 스트라이크
        if (firstBeforePin.isStrike()) {
            total +=  nowPin.getFirstTurn() + nowPin.getSecondTurn();
        } else if (firstBeforePin.isSpare()) { // 스페어
            total +=  nowPin.getFirstTurn();
        }
        nowFrame.setTotal(total);
    }


    public void setPin(Player player, int round, int[] score) {
        Frame frame = player.getFrame(round);
        frame.setPin(new Pin(score[0], score[1]));
    }

}
