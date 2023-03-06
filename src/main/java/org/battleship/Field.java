package org.battleship;

public class Field {

    Coordinates coordinate;
    FieldType fieldType;


    public Field(Coordinates coordinate, FieldType fieldType) {
        this.coordinate = coordinate;
        this.fieldType = fieldType;
    }


    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "Field{" +
                "coordinate=" + coordinate +
                ", fieldType=" + fieldType +
                '}';
    }
}
