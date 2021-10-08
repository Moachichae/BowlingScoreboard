package bowling.scoreboard.service;

import bowling.scoreboard.dto.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
     private final List<Player> players;
     private int round;

     public BowlingGame(int playerNumber) {
          players = new ArrayList<>();
          for (int i = 0; i < playerNumber; i++) {
               String playerName = "Player" + i;
               players.add(new Player(playerName));
          }
     }

     public Player getPlayer(int playerNumber){
          return players.get(playerNumber);
     }

     public int getPlayersCount(){
          return players.size();
     }

     public int getRound() {
          return round;
     }

     public void setRound(int round) {
          this.round = round;
     }
}
