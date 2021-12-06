package ticTacToe;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        
        int n, m, k; 
        Player[] players;

        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Input n, m, k:");
                n = in.nextInt();
                m = in.nextInt();
                k = in.nextInt();

                while (true) {
                    try {
                        System.out.println("Input cnt players");
                        players = new Player[in.nextInt()];

                        for (int i = 0; i < players.length; i++) {
                            while (true) {
                                try {
                                    System.out.println("Input type of Players");

                                    System.out.println("Note: \n1)Random\n2)Seq");
                                    int type = in.nextInt();

                                    switch (type) {
                                        case 1:
                                            players[i] = new RandomPlayer();
                                            break;
                                        case 2:
                                            players[i] = new SequentialPlayer();
                                            break;
                                        default:
                                            throw new AssertionError("fuck");
                                    }
                                    break;
                                } catch (NoSuchElementException e) {
                                    System.out.println("shit3");
                                    in.nextLine();
                                } catch (AssertionError e) {
                                    System.out.println("shit4");
                                    in.nextLine();
                                }
                            }
                        }

                        break;
                    } catch (NoSuchElementException e) {
                        System.out.println("shit2");
                        in.nextLine();
                    }
                }
                break;
            } catch (NoSuchElementException e) {
                System.out.println("shit1");
                in.nextLine();
            }
        }
        in.close();

        final int result = new MultiplayerGame(
            new TicTacToeBoard(n, m, k, players.length),
            players
            ).play(true);
        switch (result) {
            case 1:
                System.out.println("First won");
                break;
            case 2:
                System.out.println("Second won");
                break;
            case 3:
                System.out.println("Third won");
                break;
            case 4:
                System.out.println("Fourth won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                System.out.println("WTF? " + result);
        }
    }
}
