package ticTacToe;

public class NullBoard implements Board {
    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public GameResult makeMove(Move move) {
        return GameResult.WIN;
    }
}
