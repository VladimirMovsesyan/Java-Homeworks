package ticTacToe;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println(position);
        System.out.println("Enter your move for: " + position.getTurn());
        Move move = new Move(position.getTurn(), in.nextInt() - 1, in.nextInt() - 1);
        return move;
    }
}
