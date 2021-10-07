package bowling.scoreboard.service;


import bowling.scoreboard.dto.Frame;
import bowling.scoreboard.dto.Pin;

import bowling.scoreboard.dto.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
     private final List<Player> players;
     private int round;

     public BowlingGame(int playerNumber) {
          players = new ArrayList<>();
          for (int i = 1; i <= playerNumber; i++) {
               String playerName = "Player" + i;
               players.add(new Player(playerName));
          }
     }

    public List<Player> getPlayers() {
          return players;
     }

     public int getRound() {
          return round;
     }
     

     public void setPlayerScore(Frame frame){
          // 점수 계산 클래스에 보낸 후
          // 플레이어에게 입력
     }

}
