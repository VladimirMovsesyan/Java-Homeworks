package ticTacToe;

public class SequentialPlayer implements Player {

    @Override
    public Move makeMove(Position position) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final Move move = new Move(position.getTurn(), r, c);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError();
    }
}
