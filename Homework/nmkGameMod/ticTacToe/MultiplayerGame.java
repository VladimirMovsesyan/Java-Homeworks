package ticTacToe;

public class MultiplayerGame {
    private final Player[] players;
    private Board board;

    MultiplayerGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeMove(players[0], 1, log);
            if (result1 != -1) {
                return result1;
            }

            final int result2 = makeMove(players[1], 2, log);
            if (result2 != -1) {
                return result2;
            }

            if (players.length > 2) {
                final int result3 = makeMove(players[2], 3, log);
                if (result3 != -1) {
                    return result3;
                }
            }

            if (players.length > 3) {
                final int result4 = makeMove(players[3], 4, log);
                if (result4 != -1) {
                    return result4;
                }
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case LOSE:
                return 5 - no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Unknown result " + result);
        }
    }

    public int getPlayersCount() {
        return players.length;
    }

}