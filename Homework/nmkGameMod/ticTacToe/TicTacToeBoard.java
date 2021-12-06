package ticTacToe;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final static Map<Cell, String> CellToString = Map.of(
        Cell.X, "X",
        Cell.O, "O",
        Cell.E, ".",
        Cell.V, "|",
        Cell.H, "-"
    );

    private final Cell[][] field;
    private int freeCells;
    private final int n;
    private final int m;
    private final int k;
    private final int playersCount;
    private Cell turn;

    public TicTacToeBoard(int n, int m, int k, int playersCount) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.playersCount = playersCount;
        field = new Cell[n][m];
        for (Cell[] i : field) {
            Arrays.fill(i, Cell.E);
        }
        turn = Cell.X;
        freeCells = n * m;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    private boolean checkVerticalWin(int col) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (field[i][col] == turn) {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == k) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontalWin(int row) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (field[row][i] == turn) {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == k) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidRow(int r) {
        return 0 <= r && r < n;
    }

    private boolean isValidCol(int c) {
        return 0 <= c && c < m;
    }

    private boolean checkDiagonals(int row, int col, int rowStep, int colStep) {
        int cnt = 0;
        for (int r = row, c = col; isValidRow(r) 
                                && isValidCol(c) && field[r][c] == turn; r += rowStep, c += colStep) {
            cnt++;
            if (cnt == k) {
                return true;
            }
        }
        for (int r = row - rowStep, c = col - colStep; isValidRow(r) 
                                        && isValidCol(c) && field[r][c] == turn; r -= rowStep, c -= colStep) {
            cnt++;
            if (cnt == k) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWin(int row, int col) {
        return checkHorizontalWin(row)
                || checkVerticalWin(col)
                || checkDiagonals(row, col, 1, 1)
                || checkDiagonals(row, col, -1, 1);
    }

    private boolean checkDraw() {
        return freeCells == 0;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getValue();
        freeCells--;
        
        if (checkWin(move.getRow(), move.getCol())) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = nextTurn(turn);

        return GameResult.UNKNOWN;
    }

    private Cell nextTurn(Cell turn) {
        switch (turn) {
            case X:
                return Cell.O;
            case O: 
                return (playersCount > 2 ? Cell.V : Cell.X);
            case V:
                return (playersCount > 3 ? Cell.H : Cell.X);
            case H:
                return Cell.X;
            default:
                return Cell.E;
        }
    }

    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n && 
                0 <= move.getCol() && move.getCol() < m && 
                field[move.getRow()][move.getCol()] == Cell.E &&
                turn == move.getValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" 123").append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            sb.append(r + 1);
            for (int c = 0; c < m; c++) {
                sb.append(CellToString.get(field[r][c]));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }
}
