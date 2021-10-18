package bowling.scoreboard.view;

import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;
import bowling.scoreboard.service.BowlingGame;
import bowling.scoreboard.service.LastScoreCalculator;
import bowling.scoreboard.service.ScoreCalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameView {
    private final Scanner scanner;
    private final BowlingGame bowlingGame;
    private static final int FINAL_ROUND = 9;
    private static final int TEN = 10;
    private static final String STRIKE = "STRIKE";
    private static final String ZERO = "-";
    private static final String SPARE = "\\";

    public GameView(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
        scanner = new Scanner(System.in);
    }

    public void printBoardHeader() {
        final String BOARD_HEADER_MESSAGE
                = "| ROUND |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |      10     |";
        System.out.println(BOARD_HEADER_MESSAGE);
    }

    public void printBoardMain() { //플레이어들의 점수를 출력
        printBoardHeader();
        for (int i = 0; i < bowlingGame.getPlayersCount(); i++) {
            Player player = bowlingGame.getPlayer(i);
            System.out.printf("|%s|", player.getName());
            printPlayerPins(player);
            printPlayerTotalScore(player);
        }
    }

    public void printPlayerPins(Player player) {
        for (int round = 0; round < bowlingGame.getRound(); round++) {
            Pin pin = player.getPinOfRoundInFrame(round);
            if (round == FINAL_ROUND)
                System.out.print(getPinForm(pin.getFirstTurn(), pin.getSecondTurn(), pin.getThirdSTurn()));
            else {
                System.out.print(getPinForm(pin.getFirstTurn(), pin.getSecondTurn()));
            }
        }
        System.out.print("\n");
    }

    public String getPinForm(int firstPin, int secondPin, int thirdPin) {
        String firstPinStr = firstPin > 0 ? String.valueOf(firstPin) : ZERO;
        String secondPinStr = secondPin > 0 ? String.valueOf(secondPin) : ZERO;
        String thirdPinStr = thirdPin > 0 ? String.valueOf(thirdPin) : ZERO;

        if (firstPin == TEN) {
            if (secondPin == TEN)
                return STRIKE + " " + STRIKE + "|";
            firstPinStr = STRIKE;
        }

        if (firstPin + secondPin == TEN && firstPin != TEN) {
            secondPinStr = SPARE;
        }
        if (thirdPin == TEN) {
            if (secondPin == 0)
                thirdPinStr = SPARE;
            else
                thirdPinStr = STRIKE;
        }

        if (firstPinStr.equals(STRIKE) || thirdPinStr.equals(STRIKE))
            return " " + firstPinStr + "  " + secondPinStr + "  " + thirdPinStr + "|";

        return "  " + firstPinStr + "   " + secondPinStr + "   " + thirdPinStr + "  |";
    }


    public String getPinForm(int firstPin, int secondPin) {
        String firstPinStr = firstPin > 0 ? String.valueOf(firstPin) : ZERO;
        String secondPinStr = secondPin > 0 ? String.valueOf(secondPin) : ZERO;

        if (firstPin == TEN) {
            firstPinStr = STRIKE;
            return firstPinStr + "|";
        } else if (firstPin + secondPin == 10) {
            secondPinStr = SPARE;
        }

        return " " + firstPinStr + "  " + secondPinStr + " |";


    }

    public void printPlayerTotalScore(Player player) {
        System.out.print("| TOTAL |");
        for (int round = 0; round < bowlingGame.getRound(); round++) {
            int total = player.getTotalOfRoundInFrame(round);
            String str = total > 0 ? String.valueOf(total) : "  ";
            System.out.print(getTotalForm(total, round));
        }
        System.out.println();
    }

    public String getTotalForm(int total, int round) {
        String blank = "  ";
        String LastBlank = "";
        if (round == FINAL_ROUND) {
            blank = "     ";
            LastBlank = "    ";
        }

        if (total == 0)
            return LastBlank + "      |";
        else if (total < 10)
            return blank + total + blank + " |";
        else if (total < 100)
            return blank + total + blank + "|";
        return " " + LastBlank + total + blank + "|";
    }

    public void setPlayersScore() {
        int playersCount = bowlingGame.getPlayersCount();
        for (int i = 0; i < playersCount; i++) {
            setPlayerScoreView(i);
        }
    }

    public void setPlayerScoreView(int playerCount) {
        int round = bowlingGame.getRound();
        Player player = bowlingGame.getPlayer(playerCount);
        int[] score = new int[3];
        char temp;

        System.out.println(player.getName());
        System.out.print("첫번째 점수를 입력하세요 : ");
        score[0] = getScore();
        if (round == FINAL_ROUND || score[0] < TEN) {
            System.out.print("두번째 점수를 입력하세요 : ");
            score[1] = getScore();
        }
        if (round == FINAL_ROUND && score[0] + score[1] >= 10 && score[0] + score[1] < 20) {
            System.out.print("세번째 점수를 입력하세요 : ");
            score[2] = getScore();
        }

        if (!checkScore(score, round)) {
            System.out.println("다시 입력해주세요");
            setPlayerScoreView(playerCount);
            return;
        }
        ScoreCalculator scoreCalculate = new ScoreCalculator();
        if (round == FINAL_ROUND) {
            scoreCalculate = new LastScoreCalculator();
            scoreCalculate.setScore(player, round, score);
            return;
        }
        scoreCalculate.setScore(player, round, score);
    }

    boolean checkScore(int[] score, int round) {
        for (int s : score) {
            if (s < 0 || s > 10)
                return false;
        }
        if (round != FINAL_ROUND && score[0] + score[1] > 10)
            return false;

        if (round == FINAL_ROUND && score[0] + score[1] > 20)
            return false;

        return true;
    }

    int getScore() {
        int score = 0;
        while (true) {
            try {
                score = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("0~10의 정수만 입력하세요");
            }
        }
        return score;
    }


}
