package com.example.joanna.nonograms;

import java.util.Random;

/**
 * Created by joanna on 05.05.15.
 */
public class GameCreator {

    /**
     * if board[i][j] == 1 then the cell should be painted
     */
    private int size; // active board's size
    private int[][] board;
    private int[][] listV;
    private int[][] listH;
    private int numberingArrayHeight; // > 0
    private CharSequence[] cellList;

    private void generateRandomBoard(int seed) {
        Random rand = new Random();
        rand.setSeed(seed);

        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = rand.nextInt(2);
            }
        }
    }

    private void align() {
        for (int i = 0; i < size; i++) {
            for (int j = numberingArrayHeight - 1; j >= 0; j--) {
                /* vertical */
                int k = j;
                while (listV[k][i] == 0 && k > 0) {
                    k--;
                }
                if (k != j) {
                    listV[j][i] = listV[k][i];
                    listV[k][i] = 0;
                }
                /* horizontal */
                k = j;
                while (listH[i][k] == 0 && k > 0) {
                    k--;
                }
                if (k != j) {
                    listH[i][j] = listH[i][k];
                    listH[i][k] = 0;
                }
            }
        }
    }
    /* count painted cells in every column and every row */
    private void count() {
        listV = new int[(size+1)/2][size]; // vertical list - maximum number of numbers in column is (n+1)/2
        listH = new int[size][(size+1)/2]; // horizontal list - maximum number of numbers in row is (n+1)/2
        numberingArrayHeight = 0;
        for (int i = 0; i < size; i++) {
            int counterV = 0, counterH = 0, lastH = 0, lastV = 0;
            int column = 0, row = 0; //in which column/row of listH/listV we are
            for (int j = 0; j < size; j++) {
                /* vertical */
                if (board[i][j] == 0 && lastV == 1) { // ...1 0...
                        lastV = 0;
                        listV[row][i] = counterV;
                        row++;
                        counterV = 0;
                } else {
                    if (board[i][j] == 1) { // ...1..
                        lastV = 1;
                        counterV++;
                    }
                }
                /* horizontal */
                if (board[j][i] == 0 && lastH == 1) { //...10...
                    lastH = 0;
                    listH[i][column] = counterH;
                    column++;
                    counterH = 0;
                } else {
                    if (board[j][i] == 1) { // ...1...
                        lastH = 1;
                        counterH++;
                    }
                }
            } // for
            if (counterV != 0) {
                listV[row][i] = counterV;
                row++;
            }
            if (counterH != 0) {
                listH[i][column] = counterH;
                column++;
            }
            numberingArrayHeight = Math.max(numberingArrayHeight, row);
            numberingArrayHeight = Math.max(numberingArrayHeight, column);
        } // for
        align();
        //Log.d("numberingArrayHeight", Integer.toString(numberingArrayHeight));
        //Log.d("tag", Integer.toString(numberingArrayHeight));
    }

    GameCreator(int size, int seed) {
        this.size = size;
        generateRandomBoard(seed);
        count();
        generateCellList();
    }

    GameCreator(int size, int[][] paintedBoard) {
        this.size = size;
        board = new int[size][size];
        board = paintedBoard;
        count();
        generateCellList();
    }

    public int getTotalSize() {
        return size + numberingArrayHeight;
    }
    public CharSequence[] getCellList() {
        return cellList;
    }
    public int getCellListLength() {
        return cellList.length;
    }

    private void generateCellList() {
        int boardSize = size + numberingArrayHeight;
        cellList = new CharSequence[boardSize * boardSize];
        int k = 0;
        /* vertical numbering array */
        for (int i = 0; i < numberingArrayHeight; i++) {

            for (int l = 0; l < numberingArrayHeight; l++) { // empty cells in upper left-hand part of the board
                cellList[k] = "";
                k++;
            }
            for (int j = 0; j < size; j++) {
                if (listV[i][j] == 0)
                    cellList[k] = "";
                else
                    cellList[k] = Integer.toString(listV[i][j]);
                k++;
            }
        }
        /* horizontal numbering array */
        for (int i = 0; i < size; i++) {

            for (int l = 0; l < numberingArrayHeight; l++) {
                if (listH[i][l] == 0)
                    cellList[k] = "";
                else
                    cellList[k] = Integer.toString(listH[i][l]);
                k++;
            }
            for (int j = 0; j < size; j++) { // empty cells on the playing board
                cellList[k] = "";
                k++;
            }
        }
    }

}
