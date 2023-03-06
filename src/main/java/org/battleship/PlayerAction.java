package org.battleship;

import java.util.ArrayList;
import java.util.List;


public class PlayerAction {

    List<String> userInputLine = new ArrayList<>();

    boolean isPlayerInputCorrect = false;

    int currentShipNumber = 0;

    GameBoard gameBoard;

    public PlayerAction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean isPlayerInputCorrect() {
        return isPlayerInputCorrect;
    }

    public void setPlayerInputCorrect(boolean playerInputCorrect) {
        this.isPlayerInputCorrect = playerInputCorrect;
    }

    public void setUserInputLine(List<String> userInputLine) {
        this.userInputLine = userInputLine;
    }

    public int getCurrentShipNumber() {
        return currentShipNumber;
    }

    public void setCurrentShipNumber(int currentShipNumber) {
        this.currentShipNumber = currentShipNumber;
    }


//    public void playerInputCheck() {
//        if (playerInputListLengthCheck(userInputLine)
//                && playerInputInBoardRangeCheck(userInputLine)
//                && playerInputOrientationXYCheck(userInputLine)
//                && playerInputLengthCheck(userInputLine)) {
//            setPlayerInputCorrect(true);
//        } else {
//            setPlayerInputCorrect(false);
//            System.out.println("Bad coordinates, try again");
//        }
//
//    }
// TODO: 06.03.2023 rename
    public void playerInputCheck() {
        if (playerInputListLengthCheck(userInputLine)
                && playerInputInBoardRangeCheck(userInputLine)
                && playerInputOrientationXYCheck(userInputLine)
                && playerInputLengthCheck(userInputLine)
                && playerShipAreaCheck(userInputLine, gameBoard)){
            setPlayerInputCorrect(true);
            //this.game
            this.gameBoard.putShipOnBoard(Coordinates.stringListToCoordinates(userInputLine));
//            new BoardPrinter(gameBoard);
//            BoardPrinter.printBoard();
        } else {
            setPlayerInputCorrect(false);
            System.out.println("Bad coordinates, try again");
        }

//
//        public GameBoard playerInputCheck(Player player) {
//            if (playerInputListLengthCheck(userInputLine)
//                    && playerInputInBoardRangeCheck(userInputLine)
//                    && playerInputOrientationXYCheck(userInputLine)
//                    && playerInputLengthCheck(userInputLine)
//                    && playerShipAreaCheck(userInputLine, gameBoard)){
//                setPlayerInputCorrect(true);
//                //this.game
//             return gameBoard.putShipOnBoard(player);
////            new BoardPrinter(gameBoard);
////            BoardPrinter.printBoard();
//            } else {
//                setPlayerInputCorrect(false);
//                System.out.println("Bad coordinates, try again");
//            }
//        return


    }

    private boolean playerInputListLengthCheck(List<String> userInputLine) {
        return userInputLine.size() <= 2;
    }

    private boolean playerInputInBoardRangeCheck(List<String> userInputLine) {
        return userInputLine.stream().allMatch(s -> s.matches("^[A-J]+([1-9]|10)"));
    }

    private boolean playerInputOrientationXYCheck(List<String> userInputLine) {
        if (userInputLine.size() == 1) { // wydzielić
            return true;
        }
        return Coordinates.coordinatesOrientationCheck(Coordinates.stringListToCoordinates(userInputLine));
    }

    private boolean playerInputLengthCheck(List<String> userInputLine) {
        if (userInputLine.size() == 1) { // wydzielić
            return true;
        }
        int length = ShipFleet.values()[getCurrentShipNumber()].getSize() - 1;
        return Coordinates.coordinatesLengthCheck(Coordinates.stringListToCoordinates(userInputLine), length);
    }

    private boolean playerShipAreaCheck(List<String> userInputLine, GameBoard gameBoard){
        if (userInputLine.size() == 1) { // wydzielić
            return true;
        }
        return Coordinates.coordinatesFreeFieldsCheck(Coordinates.stringListToCoordinates(userInputLine), gameBoard);
    }
//    private void listOfOneCoordinateCondition(List<String> userInputLine){
//        if (userInputLine.size() == 1) { // wydzielić
//            return true;
//        }
//    }
}

