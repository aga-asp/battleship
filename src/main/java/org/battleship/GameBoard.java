package org.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {

    Field[][] fields = new Field[10][10];

    Ship[] ships = new Ship[5];

    ShipFleet currentShip;

    ActionStatus shootingActionStatus;

    public ActionStatus getShootingActionStatus() {
        return shootingActionStatus;
    }

    public void setShootingActionStatus(ActionStatus shootingActionStatus) {
        this.shootingActionStatus = shootingActionStatus;
    }

    public Ship[] getShips() {
        return ships;
    }

//    public void setShips(Ship[] ships) {
//        this.ships = ships;
//    }

    public void setShip(Ship ship, int n) {
        this.ships[n] = ship;
    }

    public void setCurrentShip(ShipFleet currentShip) {
        this.currentShip = currentShip;
    }

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
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Coordinates coordinate = new Coordinates(y + 1, x + 1);
                initialFields[y][x] = new Field(coordinate, FieldType.FREE_FIELD);

            }
        }
        setFields(initialFields);
    }
    //TODO MOVE SOME METHODS TO SHIP CLASS & SIMPLIFY

    private void changeFieldValues(Coordinates coordinates, FieldType fieldType) {
        int y = coordinates.getYValue() - 1;
        int x = coordinates.getXValue() - 1;
        fields[y][x].setFieldType(fieldType);

    }

    private boolean checkIfFieldIsAvailableForShipPlacement(Coordinates coordinates) {
        int yValue = coordinates.getYValue() - 1;
        int xValue = coordinates.getXValue() - 1;
        return fields[yValue][xValue].getFieldType() != FieldType.OCCUPIED_FIELD
                && fields[yValue][xValue].getFieldType() != FieldType.SHIP_FIELD;
    }
    private boolean checkIfFieldIsAvailableForHit(Coordinates coordinates){
        int yValue = coordinates.getYValue() - 1;
        int xValue = coordinates.getXValue() - 1;
        return fields[yValue][xValue].getFieldType() != FieldType.HIT_FIELD
                && fields[yValue][xValue].getFieldType() != FieldType.MISS_FIELD;
    }
    public void putHitOnBoard(Coordinates coordinates){
        int yValue = coordinates.getYValue() - 1;
        int xValue = coordinates.getXValue() - 1;
        if (fields[yValue][xValue].getFieldType() == FieldType.SHIP_FIELD) {
            changeFieldValues(coordinates, FieldType.HIT_FIELD);
            System.out.println("You hit a ship!");
            setShootingActionStatus(ActionStatus.FINISHED);
            checkShipForHit(coordinates);
        }else if(fields[yValue][xValue].getFieldType() == FieldType.HIT_FIELD ||
                fields[yValue][xValue].getFieldType() == FieldType.MISS_FIELD){
            System.out.println("Field shoot!");
            setShootingActionStatus(ActionStatus.UNFINISHED);
        }else {
            changeFieldValues(coordinates, FieldType.MISS_FIELD);
            System.out.println("You missed!");
            setShootingActionStatus(ActionStatus.FINISHED);
        }
    }
    private void checkShipForHit(Coordinates coordinates){
        for (int n = 0; n < getShips().length; n++){
            getShips()[n].lookForHit(coordinates);
        }
    }

    public boolean checkIfFieldsRangeIsAvailableForShip(Coordinates beginning, Coordinates end) {
        for (int y = beginning.getYValue(); y <= end.getYValue(); y++) {
            for (int x = beginning.getXValue(); x <= end.getXValue(); x++) {
                boolean check = checkIfFieldIsAvailableForShipPlacement(new Coordinates(y, x));
                if (!check) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkFieldForHitOrMisPlacement(Coordinates coordinates){
        return checkIfFieldIsAvailableForHit(coordinates);
    }

    public void putShipOnBoard(List<Coordinates> coordinatesList, ShipFleet currentShip) {
        setCurrentShip(currentShip);
        Coordinates beginning = coordinatesList.get(0);
        Coordinates end = coordinatesList.get(1);
        List<Coordinates> list = createShipCoordinatesLists(beginning, end);
        putShipRangeOnBoard(beginning, end);
        shipCreator(currentShip, list);
    }
    private List<Coordinates> createShipCoordinatesLists(Coordinates beginning, Coordinates end){
        List<List<Coordinates>> list = new ArrayList<>();
        for (int y = beginning.getYValue(); y <= end.getYValue(); y++) {
            int row = (y - beginning.getYValue());
            list.add(new ArrayList<>());
            for (int x = beginning.getXValue(); x <= end.getXValue(); x++) {
                list.get(row).add(new Coordinates(y, x));
                changeFieldValues(new Coordinates(y,x), FieldType.SHIP_FIELD);
            }
        }
        return list.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private void shipCreator(ShipFleet shipFleet, List<Coordinates> shipFieldsList) {
        Ship[] ships = new Ship[5];
        ships[shipFleet.ordinal()] = new Ship(shipFleet.getShipName(), shipFleet.getSize(), shipFieldsList);
        setShip(ships[shipFleet.ordinal()], shipFleet.ordinal());
    }

    private void putShipRangeOnBoard(Coordinates beginning, Coordinates end) {
        for (int x = 0; x <= end.getXValue() - beginning.getXValue(); x++) {
            if (beginning.getYValue() > 1) {
                changeFieldValues(new Coordinates(beginning.getYValue() - 1, beginning.getXValue() + x), FieldType.OCCUPIED_FIELD);
            }
            if (end.getYValue() < 10) {
                changeFieldValues(new Coordinates(end.getYValue() + 1, beginning.getXValue() + x), FieldType.OCCUPIED_FIELD);
            }
        }
        for (int y = 0; y <= end.getYValue() - beginning.getYValue(); y++) {
            if (beginning.getXValue() > 1) {
                changeFieldValues(new Coordinates(beginning.getYValue() + y, beginning.getXValue() - 1), FieldType.OCCUPIED_FIELD);
            }
            if (end.getXValue() < 10) {
                changeFieldValues(new Coordinates(beginning.getYValue() + y, end.getXValue() + 1), FieldType.OCCUPIED_FIELD);
            }
        }
    }
}
