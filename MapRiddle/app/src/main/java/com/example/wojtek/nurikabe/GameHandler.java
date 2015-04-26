package com.example.wojtek.nurikabe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.util.Log.d;

/**
 * Created by wojtek on 24.04.15.
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


    public GameHandler(int n_) {
        n = n_;
    }
    int n;
//    int[] rep;
    Field[][] board;
//    private int getId(int r, int c) {
//        return r * (n + 2) + c;
//    }
    private boolean inRange(int l, int r, int x) {
        return l <= x && x <= r;
    }
    private boolean isValid(int r, int c) {
        return inRange(1, n, r) && inRange(1, n, c);
    }
//    private int find(int v) {
//        if (rep[v] == v) {
//            return v;
//        }
//        rep[v] = find(rep[v]);
//        return rep[v];
//    }
//    private void union(int a, int b) {
//        a = find(a);
//        b = find(b);
//        if (a != b) {
//            rep[a] = b;
//        }
//    }
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
    private boolean isNei(int a, int b) {
        for (int dir = 0; dir < 4; dir++) {
            if (board[a + ddr[dir]][b + ddc[dir]].type == 2) {
                return true;
            }
        }
        return false;
    }
    private boolean canPlace(int a, int b) {
        return board[a][b].type != 2 && isNei(a, b) && noNew2x2(a, b);
    }
    int[][] vis;
    int dfs(int a, int b, int ty) {
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
    public ArrayList<Triple> generateBoard(int seed) {
        Random rand = new Random();
        rand.setSeed(seed);
        //int vers = (n + 2) * (n + 2);
//        rep = new int[vers];
        board = new Field[n + 2][n + 2];
        for (int r = 0; r < n + 2; r++) {
            for (int c = 0; c < n + 2; c++) {
                board[r][c] = new Field(0, 0);
            }
        }
//        for (int i = 0; i < vers; i++) {
//            rep[i] = i;
//        }
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
//        for (int r = 1; r <= n; r++) {
//            for (int c = 1; c <= n; c++) {
//                if (board[r][c].number != 0) {
//                    board[r][c].type = 1;
//                } else {
//                    board[r][c].type = 2;
//                }
//            }
//        }

        d("Check result:", checkBoard() + " ");

        return to_ret;
    }

    public boolean checkBoard() {
        int area = n * n;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                vis[r][c] = 0;
                if (is2x2Sea(r, c)) {
                    return false;
                }
                if (board[r][c].type == 0) {
                    return false;
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].number != 0) {
                    if (vis[r][c] != 0) { // wyspa z wieloma liczbami
                        return false;
                    }
                    int sz = dfs(r, c, 1);
                    if (sz != board[r][c].number) { // wyspa ze zla liczba pol
                        return false;
                    }
                    area -= sz;
                }
            }
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (board[r][c].type == 2) {
                    int sz = dfs(r, c, 2);
                    if (area == sz) {
                        return true;
                    } else {
                        return false; // jeden kawalek morza nie starczyl by wyrownac niezbadane pole do 0
                    }
                }
            }
        }
        return false; // nie bylo morza wcale (zakladam, ze nie ma planszy bedacej jedna wyspa :) )
    }

}
