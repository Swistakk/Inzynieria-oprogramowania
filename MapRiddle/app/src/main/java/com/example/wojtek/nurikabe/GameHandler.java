package com.example.wojtek.nurikabe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.util.Log.d;

/**
 * Created by wojtek on 24.04.15.
 */

/**
 * Class responsible for Nurikabe-specific things.
 * It is able to generate boards and checking solutions
 * Everything within this class is 1-indexed,
 * unfortunately with contrary to PlayLevel where things are 0-indexed
 */
public class GameHandler {

    public class Field {
        int type; // 0 - nie wiadomo, 1 - wyspa, 2 - morze
        int number;

        public Field(int t, int n) {
            type = t;
            number = n;
        }
        public Field() {
            type = 0;
            number = 0;
        }
    }

    /**
     * Creates a blank board of size n
     * ("2" in "n + 2" stands guards along each side
     * @param n_
     */
    public GameHandler(int n_) {
        n = n_;
        board = new Field[n + 2][n + 2];
        vis = new int[n + 2][n + 2];
        for (int r = 0; r < n + 2; r++) {
            for (int c = 0; c < n + 2; c++) {
                board[r][c] = new Field(0, 0);
                vis[r][c] = 0;
            }
        }
    }
    int n;
    Field[][] board;
    private boolean inRange(int l, int r, int x) {
        return l <= x && x <= r;
    }
    private boolean isValid(int r, int c) {
        return inRange(1, n, r) && inRange(1, n, c);
    }

    /**
     * Checks whether square [a, a+1] x [b, b+1] is covered with sea
     * @param a
     * @param b
     * @return
     */
    private boolean is2x2Sea(int a, int b) {
        for (int dr = 0; dr <= 1; dr++) {
            for (int dc = 0; dc <= 1; dc++) {
                if (board[a + dr][b + dc].type != 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks whether new 2x2 sea square will be created
     * after changing cell (a, b) to sea
     * @param a
     * @param b
     * @return
     */
    private boolean noNew2x2(int a, int b) {
        board[a][b].type = 2;
        boolean to_ret = true;
        for (int dr = -1; dr <= 0; dr++) {
            for (int dc = -1; dc <= 0; dc++) {
                if (is2x2Sea(a + dr, b + dc)) {
                    to_ret = false;
                }
            }
        }
        board[a][b].type = 0;
        return to_ret;
    }
    int[] ddr = {1, -1, 0, 0};
    int[] ddc = {0, 0, 1, -1};

    /**
     * Checks whether cell (a, b) has a neighbour which is sea
     * @param a
     * @param b
     * @return
     */
//    private boolean hasSeaNei(int a, int b) {
//        for (int dir = 0; dir < 4; dir++) {
//            if (board[a + ddr[dir]][b + ddc[dir]].type == 2) {
//                return true;
//            }
//        }
//        return false;
//    }
    private boolean has1SeaNei(int a, int b) {
        int cnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            if (board[a + ddr[dir]][b + ddc[dir]].type == 2) {
                cnt++;
            }
        }
        return (cnt == 1);
    }

    /**
     * Checks whether generator can change cell (a, b) to sea.
     * Established rule is that it cannot be sea already, it has to have
     * a neighbour which is sea and no 2x2 sea square can be created
     * @param a
     * @param b
     * @return
     */
    private boolean canPlace(int a, int b) {
        return board[a][b].type != 2 && has1SeaNei(a, b) && noNew2x2(a, b);
    }
    int[][] vis;

    /**
     * Perfmorms DFS from cell (a, b) where we are allowed
     * to step on cells with type "ty" only. It returns size of whole component
     * @param a
     * @param b
     * @param ty
     * @return
     */
    private int dfs(int a, int b, int ty) {
        vis[a][b] = 1;
        int sz = 1;
        for (int dir = 0; dir < 4; dir++) {
            int nr = a + ddr[dir];
            int nc = b + ddc[dir];
            if (isValid(nr, nc) && vis[nr][nc] == 0 && board[nr][nc].type == ty) {
                sz += dfs(nr, nc, ty);
            }
        }
        return sz;
    }

    /**
     * This method generates a board. Since initial board is a grid
     * with some field with some numbers, most succinct description
     * of it consists of list of triples (a, b, v) denoting
     * that value v is present in cell (a, b)
     * Since generation of board is randomized, seed need to be provided
     * to that function
     * @param seed
     * @return
     */
    public ArrayList<Triple> generateBoard(int seed) {
        Random rand = new Random();
        rand.setSeed(seed);
        board = new Field[n + 2][n + 2];
        for (int r = 0; r < n + 2; r++) {
            for (int c = 0; c < n + 2; c++) {
                board[r][c] = new Field(0, 0);
            }
        }
        int st_r = rand.nextInt(n) + 1;
        int st_c = rand.nextInt(n) + 1;
        //System.out.println("START: " + st_r + " " + st_c);
        d("START", st_r + " " + st_c);
        board[st_r][st_c].type = 2;
        int sea_area = 1;
        int failed_tries = 0, max_failed_tries = 100;
        while (sea_area < n * n / 5 || failed_tries < max_failed_tries) {
            int new_r = rand.nextInt(n) + 1;
            int new_c = rand.nextInt(n) + 1;
            if (canPlace(new_r, new_c)) {
                board[new_r][new_c].type = 2;
                sea_area++;
                failed_tries = 0;
            } else {
                failed_tries++;
            }
        }
        vis = new int[n + 2][n + 2];
        ArrayList<Triple> to_ret = new ArrayList<Triple>(1);
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].type != 2 ) {
                    if (vis[r][c] == 0){
                        board[r][c].number = dfs(r, c, 0);
                        to_ret.add(new Triple(r, c, board[r][c].number));
                    }
                    board[r][c].type = 1;
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].type == 2) {
                    System.out.print('#');
                } else if (board[r][c].number != 0) {
                    System.out.print(board[r][c].number);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }

        d("Check result:", checkBoard() + " ");

        return to_ret;
    }

    /**
     * Sets cell (r, c) to type ty and number in it to num
     * (used by check function from PlayLevel class)
     * @param r
     * @param c
     * @param ty
     * @param num
     */
    public void setField(int r, int c, int ty, int num) {
        board[r][c].type = ty;
        board[r][c].number = num;
    }

    /**
     * Checks whether board contains valid solution
     * and in case of player's failure provides him appropriate information of
     * what went wrong
     * @return
     */
    public String checkBoard() {
        int area = n * n;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                vis[r][c] = 0;
                if (is2x2Sea(r, c)) {
                    return "Istnieje kwadrat 2x2 morza!";
                }
                if (board[r][c].type == 0) {
                    return "Nie wszystko jest wypełnione!";
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].number != 0) {
                    if (vis[r][c] != 0) { // wyspa z wieloma liczbami
                        return "Istnieje wyspa z wieloma liczbami!";
                    }
                    int sz = dfs(r, c, 1);
                    if (sz != board[r][c].number) { // wyspa ze zla liczba pol
                        return "Istnieje wyspa ze złą liczbą pól!";
                    }
                    area -= sz;
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].type == 1 && vis[r][c] == 0) {
                    return "Istnieje wyspa bez liczby!";
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].type == 2) {
                    int sz = dfs(r, c, 2);
                    if (area == sz) {
                        return "WOW! Udało Ci się!!!";
                    } else {
                        return "Morze nie jest spójne!"; // jeden kawalek morza nie starczyl by wyrownac niezbadane pole do 0
                    }
                }
            }
        }
        return "Ups!"; // nie bylo morza wcale (zakladam, ze nie ma planszy bedacej jedna wyspa :) )
    }

}
