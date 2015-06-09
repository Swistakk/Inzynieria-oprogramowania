package com.example.wojtek.filomino;

/**
 * Created by wojtek on 02.06.15.
 */
public class Field {
    int number;
    FieldType type;
    boolean full_comp;
    public Field(FieldType t, int n) {
        type = t;
        number = n;
        full_comp = false;
    }
    public Field() {
        type = FieldType.EMPTY;
        number = 0;
        full_comp = false;
    }
}
