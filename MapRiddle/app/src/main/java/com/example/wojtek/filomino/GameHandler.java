package com.example.wojtek.filomino;

public class GameHandler {
    /**
     * Creates a blank board of size n
     * ("2" in "n + 2" stands guards along each side
     * @param n_
     */
    public GameHandler(int n_) {
        n = n_;
        board = new Field[n + 2][n + 2];
        vis = new boolean[n + 2][n + 2];
        for (int r = 0; r < n + 2; r++) {
            for (int c = 0; c < n + 2; c++) {
                board[r][c] = new Field(FieldType.EMPTY, 0);
            }
        }
    }

    int n;
    Field[][] board;
    private boolean[][] vis;
    private boolean inRange(int l, int r, int x) {
        return l <= x && x <= r;
    }
    private boolean isValid(int r, int c) {
        return inRange(0, n - 1, r) && inRange(0, n - 1, c);
    }

    int[] ddr = {1, -1, 0, 0};
    int[] ddc = {0, 0, 1, -1};


    public void generateBoard(int n, int id) {
        BoardsBase base = new BoardsBase();
        String board_desc = base.getBoard(n, id);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int val = Character.getNumericValue(board_desc.charAt(n * r + c));
                if (val != 0) {
                    board[r][c].type = FieldType.FILLED_FIXED;
                    board[r][c].number = val;
                }
            }
        }
    }


    public void setField(int r, int c, FieldType ty, int num) {
        board[r][c].type = ty;
        board[r][c].number = num;
    }


    public void clearVis() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                vis[r][c] = false;
            }
        }
    }

    private int dfs(int r, int c, int num) {
        int sz = 1;
        vis[r][c] = true;
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + ddr[dir];
            int nc = c + ddc[dir];
            if (isValid(nr, nc) && !vis[nr][nc] && board[nr][nc].number == num) {
                sz += dfs(nr, nc, num);
            }
        }
        return sz;
    }

    public void updateFullComps() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c].number != 0) {
                    clearVis();
                    int sz = dfs(r, c, board[r][c].number);
                    board[r][c].full_comp = (sz == board[r][c].number);
                }
            }
        }
    }


    /**
     * Checks whether board contains valid solution
     * and in case of player's failure provides him appropriate information of
     * what went wrong
     * @return
     */
    public String checkBoard() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (vis[r][c]) { continue; }
                if (board[r][c].number == 0) {
                    return "Istnieje nieoznaczone pole!";
                }
                int sz = dfs(r, c, board[r][c].number);
                if (sz != board[r][c].number) {
                    return "Istnieje klocek ze złą liczbą pól!";
                }
            }
        }
        return "WOW! Udało Ci się!";
    }

}
