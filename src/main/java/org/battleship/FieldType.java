package org.battleship;

public enum FieldType {
    FREE_FIELD('~'),
    SHIP_FIELD('O'),
    OCCUPIED_FIELD('@');

    FieldType(char fieldSign) {
        this.fieldSign = fieldSign;
    }
    private char fieldSign;

    public char getFieldSign() {
        return fieldSign;
    }
}
