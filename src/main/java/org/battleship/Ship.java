package org.battleship;

import java.util.List;
import java.util.Optional;

public class Ship {


    String name;
    int hpPoints;
    List<Coordinates> shipFields;


    public Ship(String name, int hpPoints, List<Coordinates> shipFields) {
        this.name = name;
        this.hpPoints = hpPoints;
        this.shipFields = shipFields;
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

    public List<Coordinates> getShipFields() {
        return shipFields;
    }
    private void hpReduction(){
        int currentShipHp = getHpPoints();
        if (currentShipHp ==0){
            System.out.println("You sank a ship: "+ getName());
        }else {
            setHpPoints(currentShipHp - 1);
        }
    }
    public void lookForHit(Coordinates coordinates){
        Optional<Coordinates> coordinateMatch = shipFields.stream().filter(c -> c.equals(coordinates)).findAny();
        if (coordinateMatch.isPresent()) {
            hpReduction();
        }
    }
}

