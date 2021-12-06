package ticTacToe;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random(); 

    @Override
    public Move makeMove(Position position) {
        while (true) {
           Move move = new Move(position.getTurn(), 
                            random.nextInt(3), 
                            random.nextInt(3));
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
