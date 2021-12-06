package ticTacToe;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random(); 

    @Override
    public Move makeMove(Position position) {
        while (true) {
           Move move = new Move(position.getTurn(), 
                            random.nextInt(position.getN()), 
                            random.nextInt(position.getM()));
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
