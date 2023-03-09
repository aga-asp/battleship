package org.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Coordinates {

    int xValue;
    int yValue;

    String inputValue;

    public Coordinates(int yValue, int xValue) {
        this.yValue = yValue;
        this.xValue = xValue;
    }

    public Coordinates(String inputValue) {
        this.inputValue = inputValue;
        stringToCoordinates(inputValue);
    }

    public int getYValue() {
        return yValue;
    }

    public int getXValue() {
        return xValue;
    }

    private void setXValue(int xValue) {
        this.xValue = xValue;
    }

    private void setYValue(int yValue) {
        this.yValue = yValue;
    }

    private void stringToCoordinates(String input) {
        int xValue = Integer.parseInt(input.substring(1));
        int yValue = input.charAt(0) - '@';
        setXValue(xValue);
        setYValue(yValue);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return xValue == that.xValue && yValue == that.yValue && Objects.equals(inputValue, that.inputValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xValue, yValue, inputValue);
    }

    public static List<Coordinates> stringListToCoordinates(List<String> inputList) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        for (String s : inputList) {
            coordinatesList.add(new Coordinates(s));
        }
        return coordinatesList;
    }
    public static Coordinates stringListToCoordinates(String coordinatesSting) {

        return new Coordinates(coordinatesSting);
    }

    public static boolean coordinatesOrientationCheck(List<Coordinates> coordinatesList) {
        return (coordinatesList.get(0).yValue != coordinatesList.get(1).yValue ||
                coordinatesList.get(0).xValue != coordinatesList.get(1).xValue) &&
                (coordinatesList.get(0).yValue == coordinatesList.get(1).yValue ||
                        coordinatesList.get(0).xValue == coordinatesList.get(1).xValue);
    }

    public static boolean coordinatesLengthCheck(List<Coordinates> coordinatesList, int shipLength) {
        return (abs(coordinatesList.get(0).xValue - coordinatesList.get(1).xValue) == shipLength) ||
                (abs(coordinatesList.get(0).yValue - coordinatesList.get(1).yValue) == shipLength);
    }

    public static boolean coordinatesFreeFieldsCheckShipPlacement(List<Coordinates> coordinatesList, GameBoard gameBoard) {
        return gameBoard.checkIfFieldsRangeIsAvailableForShip(coordinatesList.get(0), coordinatesList.get(1));
    }
    public static boolean coordinatesHitOrMisPlacementCheck(Coordinates coordinates, GameBoard gameBoard){
        return gameBoard.checkFieldForHitOrMisPlacement(coordinates);
    }
}
