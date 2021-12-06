package ticTacToe;

public interface Board {
    Position getPosition();

    GameResult makeMove(Move move);
}
