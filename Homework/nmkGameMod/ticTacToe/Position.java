package ticTacToe;

public interface Position {
    Cell getTurn();
    int getN();
    int getM();
    boolean isValid(Move move);
}
