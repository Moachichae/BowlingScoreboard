package bowling.scoreboard.view;

import bowling.scoreboard.dto.Frame;
import bowling.scoreboard.dto.Pin;
import bowling.scoreboard.dto.Player;
import bowling.scoreboard.service.BowlingGame;

import java.util.List;
import java.util.Scanner;

public class GameView {
    private final Scanner scanner;
    private final BowlingGame bowlingGame;

    public GameView(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
        scanner = new Scanner(System.in);
    }

    public void printBoardHeader() {
        final String BOARD_HEADER_MESSAGE
                = "|  NAME |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
        System.out.println(BOARD_HEADER_MESSAGE);
    }

    public void printBoardMain() {
        //플레이어들의 점수를 출력
        printBoardHeader();
        for (int i = 0; i < bowlingGame.getPlayersCount(); i++) {
            Player player = bowlingGame.getPlayer(i);
            System.out.printf("|%s|", player.getName());
            printPlayerPins(player);
            printPlayerRoundScore(player);
        }

    }

    public void printPlayerPins(Player player) {
        int frameCount = player.getFramesCount(); // 가지고 있는 프레임
        for (int r = 0; r < frameCount; r++) {
            Frame frame = player.getFrame(r);
            Pin pin = frame.getPin();
            System.out.printf(" %s/%s |", pin.getFirstTurn(), pin.getSecondTurn());
        }
        System.out.print("\n");
    }

    public void printPlayerRoundScore(Player player) {
        int frameCount = player.getFramesCount(); // 가지고 있는 프레임
        System.out.print("|       |");
        for (int r = 0; r < frameCount; r++) {
            Frame frame = player.getFrame(r);
            Pin pin = frame.getPin();
            System.out.printf("  %s  |", frame.getTotal());
        }
        System.out.print("\n");
    }

    public void setPlayersScore() {
        int playersCount = bowlingGame.getPlayersCount();
        for (int i = 0; i < playersCount; i++) {
            setPlayerScore(i);
            printBoardMain();
        }
    }

    public void setPlayerScore(int playerCount){
        int round = bowlingGame.getRound();
        final int FINAL_ROUND = 10;
        String playerName = bowlingGame.getPlayer(playerCount).getName();
        System.out.println(playerName);
        int [] score = new int[3];
        System.out.print("첫번째 점수를 입력하세요 : ");
        score[0] = scanner.nextInt();
        if (score[0] != 10){
            System.out.print("두번째 점수를 입력하세요 : ");
            score[1] = scanner.nextInt();
        }
        if (round == FINAL_ROUND){
            System.out.print("세번째 점수를 입력하세요 : ");
            score[2] = scanner.nextInt();
        }

    }



}
