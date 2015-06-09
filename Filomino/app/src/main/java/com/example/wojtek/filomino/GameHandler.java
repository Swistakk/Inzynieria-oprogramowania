package com.example.wojtek.filomino;

import java.util.ArrayList;

/**
 * Created by wojtek on 02.06.15.
 */
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


    public ArrayList<Triple> generateBoard() {
        ArrayList<Triple> to_ret = new ArrayList<Triple>(1);
        to_ret.add(new Triple(0, 0, 1));
        to_ret.add(new Triple(0, 2, 1));
        to_ret.add(new Triple(0, 7, 1));
        to_ret.add(new Triple(1, 4, 1));
        to_ret.add(new Triple(1, 5, 3));
        to_ret.add(new Triple(1, 6, 1));
        to_ret.add(new Triple(1, 7, 2));
        to_ret.add(new Triple(2, 0, 1));
        to_ret.add(new Triple(2, 1, 8));
        to_ret.add(new Triple(2, 2, 1));
        to_ret.add(new Triple(2, 4, 7));
        to_ret.add(new Triple(3, 0, 7));
        to_ret.add(new Triple(3, 4, 7));
        to_ret.add(new Triple(4, 0, 1));
        to_ret.add(new Triple(4, 1, 3));
        to_ret.add(new Triple(4, 3, 1));
        to_ret.add(new Triple(4, 5, 1));
        to_ret.add(new Triple(4, 6, 8));
        to_ret.add(new Triple(5, 1, 1));
        to_ret.add(new Triple(5, 3, 4));
        to_ret.add(new Triple(5, 4, 2));
        to_ret.add(new Triple(5, 6, 1));
        to_ret.add(new Triple(6, 0, 6));
        to_ret.add(new Triple(6, 2, 1));
        to_ret.add(new Triple(6, 5, 5));
        to_ret.add(new Triple(6, 7, 2));
        to_ret.add(new Triple(7, 4, 1));
        to_ret.add(new Triple(7, 7, 2));



        for (Triple tr : to_ret) {
            board[tr.st][tr.nd].type = FieldType.FILLED_FIXED;
            board[tr.st][tr.nd].number = tr.rd;
        }
        return to_ret;
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
