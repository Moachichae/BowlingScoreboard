package bowling.scoreboard.view;

import bowling.scoreboard.service.BowlingGame;

import java.io.PrintStream;
import java.util.Scanner;

public class StartView {
    private final Scanner scanner;

    public StartView() {
        this.scanner = new Scanner(System.in);
    }

    public int setPlayerCount() {
        String INPUT_PLAYER_COUNT_MESSAGE = "플레이어 수를 입력하세요 : ";
        System.out.print(INPUT_PLAYER_COUNT_MESSAGE);

        String playerNumber = scanner.nextLine();
        return Integer.parseInt(playerNumber);
    }


}
