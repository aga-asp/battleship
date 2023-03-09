package org.battleship;

public enum FieldType {
    FREE_FIELD('~'),
    SHIP_FIELD('O'),
    OCCUPIED_FIELD('~'),

    HIT_FIELD('X'),

    MISS_FIELD('M'),

    HIDDEN_FIELD_SHIP('~');


    FieldType(char fieldSign) {
        this.fieldSign = fieldSign;
    }
    private final char fieldSign;

    public char getFieldSign() {
        return fieldSign;
    }
}
