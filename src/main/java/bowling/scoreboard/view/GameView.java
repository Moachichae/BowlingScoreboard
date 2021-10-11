package bowling.scoreboard.view;

import bowling.scoreboard.dto.Frame;
import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;
import bowling.scoreboard.service.BowlingGame;
import bowling.scoreboard.service.ScoreCalculate;

import java.util.Scanner;

public class GameView {
    private final Scanner scanner;
    private final BowlingGame bowlingGame;
    private static final int FINAL_ROUND = 10;
    private static final int TEN = 10;


    public GameView(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
        scanner = new Scanner(System.in);
    }

    public void printBoardHeader() {
        final String BOARD_HEADER_MESSAGE
                = "| ROUND |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
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
        for (int i = 0; i < bowlingGame.getRound(); i++) {
            Frame frame = player.getFrame(i);
            Pin pin = frame.getPin();
            System.out.print(getPinForm(pin.getFirstTurn(),pin.getSecondTurn()));
        }
        System.out.print("\n");
    }

    public String getPinForm(int firstPin, int secondPin){
        String blank = "  ";
        if(firstPin == TEN)
            return "STRIKE|";
        else if(firstPin + secondPin == TEN)
            return " " + firstPin + blank + "\\ |";
        return " " + firstPin + blank + secondPin + " |";
    }

    public void printPlayerTotalScore(Player player) {
        System.out.print("| TOTAL |");
        for (int round = 0; round < bowlingGame.getRound(); round++) {
            Frame frame = player.getFrame(round);
            Pin pin = frame.getPin();
            int total = frame.getTotal();
            String str = total > 0 ? String.valueOf(total) : "  ";
            System.out.print(getTotalForm(total));
        }
        System.out.println();

    }

    public String getTotalForm(int total){
       if(total == 0)
           return "      |";
        else if(total < 10)
            return "  "+ total +"   |";
       else if (total < 100)
            return "  "+total+"  |";
       return " "+total+" |";
    }

    public void setPlayersScore() {
        int playersCount = bowlingGame.getPlayersCount();
        for (int i = 0; i < playersCount; i++) {
            setPlayerScoreView(i);
        }
    }

    public void setPlayerScoreView(int playerCount){
        int round = bowlingGame.getRound();
        Player player = bowlingGame.getPlayer(playerCount);
        int [] score = new int[3];
        System.out.println(player.getName());
        System.out.print("첫번째 점수를 입력하세요 : ");
        score[0] = scanner.nextInt();
        if (score[0] < TEN){
            System.out.print("두번째 점수를 입력하세요 : ");
            score[1] = scanner.nextInt();
        }
        if (round == FINAL_ROUND && score[0] + score[1] >= 10){
            System.out.print("세번째 점수를 입력하세요 : ");
            score[2] = scanner.nextInt();
        }

        ScoreCalculate scoreCalculate = ScoreCalculate.getScoreCalculateInstance();
        scoreCalculate.setTotal(player,round,score);
    }



}
