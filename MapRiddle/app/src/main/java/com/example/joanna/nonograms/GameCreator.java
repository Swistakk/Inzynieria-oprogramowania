package com.example.joanna.nonograms;

import java.util.Random;

/**
 * Created by joanna on 05.05.15.
 */
public class GameCreator {

    /**
     * if board[i][j] == 1 then the cell should be painted
     */
    private int size;
    private int[][] board;
    private int[][] listV;
    private int numberArrayHeight;
    private int[][] listH;

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

    private void count() {
        listV = new int[(size+1)/2][size]; // maximum number of numbers in column is (n+1)/2
        listH = new int[size][(size+1)/2]; // maximum number of numbers in row is (n+1)/2
        numberArrayHeight = 0;
        for (int i = 0; i < size; i++) {
            int counterV = 0, counterH = 0, lastH = 0, lastV = 0;
            int column = 0, row = 0;
            for (int j = 0; j < size; j++) {
                /* vertical */
                if (board[i][j] == 0 && lastV == 1) {
                        lastV = 0;
                        listV[row][i] = counterV;
                        row++;
                        counterV = 0;
                } else {
                    if (board[i][j] == 1) {
                        lastV = 1;
                        counterV++;
                    }
                }
                /* horizontal */
                if (board[j][i] == 0 && lastH == 1) {
                    lastH = 0;
                    listH[i][column] = counterH;
                    column++;
                    counterH = 0;
                } else {
                    if (board[j][i] == 1) {
                        lastH = 1;
                        counterH++;
                    }
                }
            }
            listV[row][i] = counterV;
            row++;
            listH[i][column] = counterH;
            column++;
            numberArrayHeight = Math.max(numberArrayHeight, row);
            numberArrayHeight = Math.max(numberArrayHeight, column);
        }
    }

    GameCreator(int size, int seed) {
        this.size = size;
        generateRandomBoard(seed);
        count();
    }

    public int getTotalSize() {
        return size + numberArrayHeight;
    }

    public CharSequence[] generateCellList() {
        int boardSize = size + numberArrayHeight;
        CharSequence[] cellList = new CharSequence[boardSize * boardSize];
        int k = 0;
        /* vertical */
        for (int i = 0; i < numberArrayHeight; i++) {

            for (int l = 0; l < numberArrayHeight; l++) {
                cellList[k] = "";
                k++;
            }
            for (int j = 0; j < size; j++) {
                if (listH[j][i] == 0)
                    cellList[k] = "";
                else
                    cellList[k] = Integer.toString(listH[j][i]);
                k++;
            }
        }
        /* horizontal */
        for (int i = 0; i < size; i++) {

            for (int l = 0; l < numberArrayHeight; l++) {
                if (listV[l][i] == 0)
                    cellList[k] = "";
                else
                    cellList[k] = Integer.toString(listV[l][i]);
                k++;
            }
            for (int j = 0; j < size; j++) {
                cellList[k] = "";
                k++;
            }
        }
        return cellList;
    }

}
