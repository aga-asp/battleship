package org.battleship;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Coordinates> stringListToCoordinates(List<String> inputList) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        for (String s : inputList) {
            coordinatesList.add(new Coordinates(s));
        }
        return coordinatesList;
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
    public static boolean coordinatesFreeFieldsCheck(List<Coordinates> coordinatesList, GameBoard gameBoard) {
        return gameBoard.checkIfFieldsRangeIsAvailable(coordinatesList.get(0), coordinatesList.get(1));
    }
}
