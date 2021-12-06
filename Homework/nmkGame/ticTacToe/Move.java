package ticTacToe;

public class Move {
    private final Cell value;
    private final int row;
    private final int col;

    Move(Cell value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    public Cell getValue() {
        return value;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %d, %d)", value, row + 1, col + 1);
    }

}
