package org.battleship;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    Field[][] fields = new Field[10][10];

    public GameBoard() {
        initializeBoardValues();
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    private void initializeBoardValues() {
        Field[][] initialFields = new Field[10][10];
        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < 10; i++) {
                Coordinates coordinate = new Coordinates(n + 1, i + 1);
                initialFields[n][i] = new Field(coordinate, FieldType.FREE_FIELD);

            }
        }
        setFields(initialFields);
    }

    private void changeFieldValues(int yValue, int xValue, FieldType fieldType) {
        int y = yValue - 1;
        int x = xValue - 1;
//        if (checkIfFieldIsAvailable(new Coordinates(y, x))) {
//            fields[y][x].setFieldType(fieldType);
//        }
    }

    private void changeFieldValues(Coordinates coordinates, FieldType fieldType) {
        int y = coordinates.getYValue() - 1;
        int x = coordinates.getXValue() - 1;
//        if (checkIfFieldIsAvailable(new Coordinates(y, x))) {
//            fields[y][x].setFieldType(fieldType);
//        }
    }

    private boolean checkIfFieldIsAvailable(Coordinates coordinates) {

        int yValue = coordinates.getYValue();
        int xValue = coordinates.getXValue();
        if (fields[yValue][xValue].getFieldType() == FieldType.OCCUPIED_FIELD
                || fields[yValue][xValue].getFieldType() == FieldType.SHIP_FIELD) {
            System.out.println("Already Taken");
            return false;
        }
        return true;
    }
    public boolean checkIfFieldsRangeIsAvailable(Coordinates beginning, Coordinates end) {

        for (int n = beginning.getYValue(); n <= end.getYValue(); n++) {
            for (int i = beginning.getXValue(); i <= end.getXValue(); i++) {
                boolean check = checkIfFieldIsAvailable(new Coordinates(n, i));
                System.out.println(check);
                if (!check){
                    return false;
                }
                }
            }
        return true;
    }

    public void putShipOnBoard(List<Coordinates> coordinatesList) {
        Coordinates beginning = coordinatesList.get(0);
        Coordinates end = coordinatesList.get(1);
        List<List<Coordinates>> list = new ArrayList<>();
        for (int n = beginning.getYValue(); n <= end.getYValue(); n++) {
            int row = (n - beginning.getYValue());
            list.add(new ArrayList<Coordinates>());
            for (int i = beginning.getXValue(); i <= end.getXValue(); i++) {
                list.get(row).add(new Coordinates(n, i));
                changeFieldValues(n, i, FieldType.SHIP_FIELD);
            }
        }
        putShipRangeOnBoard(beginning, end);
    }

    private void putShipRangeOnBoard(Coordinates beginning, Coordinates end) {
        for (int n = 0; n <= end.getXValue() - beginning.getXValue(); n++) {
            if (beginning.getYValue() > 1) {
                changeFieldValues(new Coordinates(beginning.getYValue() - 1, beginning.getXValue() + n), FieldType.OCCUPIED_FIELD);
            }
            if (end.getYValue() < 10) {
                changeFieldValues(new Coordinates(end.getYValue() + 1, beginning.getXValue() + n), FieldType.OCCUPIED_FIELD);
            }
        }
        for (int n = 0; n <= end.getYValue() - beginning.getYValue(); n++) {
            if (beginning.getXValue() > 1) {
                changeFieldValues(new Coordinates(beginning.getYValue() + n, beginning.getXValue() - 1), FieldType.OCCUPIED_FIELD);
            }
            if (end.getXValue() < 10) {
                changeFieldValues(new Coordinates(beginning.getYValue() + n, end.getXValue() + 1), FieldType.OCCUPIED_FIELD);
            }
        }
    }
}
