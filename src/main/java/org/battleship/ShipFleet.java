package org.battleship;

public enum ShipFleet {

    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int size;
    private final String shipName;

    private ShipFleet(int size, String shipName) {
        this.size = size;
        this.shipName = shipName;
    }

    public int getSize() {
        return this.size;
    }

    public String getShipName() {
        return this.shipName;
    }

}
