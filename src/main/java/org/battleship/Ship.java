package org.battleship;

import java.util.List;

public class Ship {


    String name;
    int hpPoints;
    int shipPlacementNumber;
    List<String> coordinates;
    List<String> shipFields;
    List<String> shipAreaFields;

    public Ship() {
    }

    public Ship(String name, int hpPoints) {
        this.name = name;
        this.hpPoints = hpPoints;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getShipFields() {
        return shipFields;
    }

    public void setShipFields(List<String> shipFields) {
        this.shipFields = shipFields;
    }

    public List<String> getShipAreaFields() {
        return shipAreaFields;
    }

    public void setShipAreaFields(List<String> shipAreaFields) {
        this.shipAreaFields = shipAreaFields;
    }

    public int getHpPoints() {
        return hpPoints;
    }

    public void setHpPoints(int hpPoints) {
        this.hpPoints = hpPoints;
    }

    public String getName() {
        return name;
    }

    public int getShipPlacementNumber() {
        return shipPlacementNumber;
    }
}

