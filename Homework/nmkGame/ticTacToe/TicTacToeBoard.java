package ticTacToe;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final static Map<Cell, String> CellToString = Map.of(
        Cell.X, "X",
        Cell.O, "O",
        Cell.E, "."
    );

    private final Cell[][] field;
    private Cell turn;

    public TicTacToeBoard() {
        field = new Cell[3][3];
        for (Cell[] i : field) {
            Arrays.fill(i, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    private boolean checkWin() {
        for (int r = 0; r < 3; r++) {
            int cnt = 0;
            for (int c = 0; c < 3; c++) {
                if (field[r][c] == turn) {
                    cnt++;
                }
            }
            if (cnt == 3) {
                return true;
            }
        }

        for (int c = 0; c < 3; c++) {
            int cnt = 0;
            for (int r = 0; r < 3; r++) {
                if (field[r][c] == turn) {
                    cnt++;
                }
            }
            if (cnt == 3) {
                return true;
            }
        }

        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] == turn) {
                cnt++;
            }
        }
        if (cnt == 3) {
            return true;
        }

        cnt = 0;

        for (int i = 0; i < 3; i++) {
            if (field[2 - i][i] == turn) {
                cnt++;
            }
        }

        if (cnt == 3) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        int cnt = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (field[r][c] == Cell.E) {
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            return true;
        }
        return false;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getValue();

        
        if (checkWin()) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;

        return GameResult.UNKNOWN;
    }

    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < 3 && 
                0 <= move.getCol() && move.getCol() < 3 && 
                field[move.getRow()][move.getCol()] == Cell.E &&
                turn == move.getValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" 123").append(System.lineSeparator());
        for (int r = 0; r < 3; r++) {
            sb.append(r + 1);
            for (int c = 0; c < 3; c++) {
                sb.append(CellToString.get(field[r][c]));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
