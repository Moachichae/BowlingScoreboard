package bowling.scoreboard.view;

import java.util.Scanner;

public class GameView {
    private final Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void printBoardHeader() {
        final String BOARD_HEADER_MESSAGE 
                = "| NAME |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
        System.out.println(BOARD_HEADER_MESSAGE);
    }

    public void printBoardMain(){
        //플레이어를 받고 플레이어들의
    }
    

    
    
    public void setBoardBody(){
        int firstPin = scanner.nextInt();
        System.out.print("/");
        int secondPin = scanner.nextInt();
        
    }


}
