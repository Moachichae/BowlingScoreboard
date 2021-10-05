package bowling.scoreboard;

import bowling.scoreboard.service.BowlingGame;
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
    }


    public static void main(String[] args) {new ScoreboardApplication().run();}

}
