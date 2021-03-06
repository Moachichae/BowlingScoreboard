package bowling.scoreboard;

import bowling.scoreboard.service.BowlingGame;
import bowling.scoreboard.view.GameView;
import bowling.scoreboard.view.StartView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreboardApplication {
    private final StartView startView;

    public ScoreboardApplication() {
        this.startView = new StartView();
    }

    public void run() {
        int playerCount = startView.setPlayerCount();
        BowlingGame bowlingGame = new BowlingGame(playerCount);
        GameView gameView = new GameView(bowlingGame);
        int n = 10;
        while (n-- > 0) {
            gameView.printBoardMain();
            gameView.setPlayersScore();
            bowlingGame.addRound();
        }
        gameView.printBoardMain();
    }


    public static void main(String[] args) {
        new ScoreboardApplication().run();
    }

}
