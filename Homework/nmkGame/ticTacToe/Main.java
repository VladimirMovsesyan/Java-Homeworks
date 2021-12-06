package ticTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int result = new TwoPlayerGame(
            new SequentialPlayer(), new HumanPlayer(new Scanner(System.in)), 
            new TicTacToeBoard()
            ).play(false);
        switch (result) {
            case 1:
                System.out.println("First won");
                break;
            case 2:
                System.out.println("Second won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                System.out.println("WTF? " + result);
        }
    }
}
