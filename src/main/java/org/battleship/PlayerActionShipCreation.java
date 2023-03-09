package org.battleship;

import java.util.List;


public class PlayerActionShipCreation {

    boolean isPlayerInputCorrect = false;

    MoveType moveType;

    ShipFleet currentShip;

    public PlayerActionShipCreation(MoveType moveType) {
        this.moveType = moveType;
    }

    public boolean isPlayerInputCorrect() {
        return isPlayerInputCorrect;
    }

    public void setPlayerInputCorrect(boolean playerInputCorrect) {
        this.isPlayerInputCorrect = playerInputCorrect;
    }


    public void setCurrentShip(ShipFleet currentShip) {
        this.currentShip = currentShip;
    }


    public void checkAllConditionsToPutOnGameBoard(GameBoard gameBoard, GameBoard opponentHiddenGameBoard, List<String> userInputLine) {
        GameBoard referenceBoard;
        if (moveType == MoveType.ATTACK_MOVE){
            referenceBoard = opponentHiddenGameBoard;
        }else {
            referenceBoard = gameBoard;
        }
        if (playerInputListLengthCheck(userInputLine)
                && playerInputInBoardRangeCheck(userInputLine)
                && playerInputOrientationXYCheck(userInputLine)
                && playerInputLengthCheck(userInputLine)
                && playerShipAreaCheck(userInputLine, referenceBoard)) {
            setPlayerInputCorrect(true);
            putPlayerShipOnGameBoard(referenceBoard, userInputLine);
        } else {
            setPlayerInputCorrect(false);
            System.out.println("Bad coordinates, try again");
        }
    }

    private void putPlayerShipOnGameBoard(GameBoard gameBoard, List<String> userInputLine) {
        if (moveType == MoveType.ATTACK_MOVE) {
            do {
                String coordinatesString = userInputLine.get(0);
                gameBoard.putHitOnBoard(new Coordinates(coordinatesString));
            }while (gameBoard.getShootingActionStatus()!=ActionStatus.FINISHED);
            new BoardPrinter(gameBoard);
            BoardPrinter.printBoardHidden();

        } else {
            gameBoard.putShipOnBoard(Coordinates.stringListToCoordinates(userInputLine), currentShip);
            new BoardPrinter(gameBoard);
            BoardPrinter.printBoard();
        }
    }

    private boolean playerInputListLengthCheck(List<String> userInputLine) {
        if (moveType == MoveType.ATTACK_MOVE) {
            return userInputLine.size() == 1;
        }
        if (moveType == MoveType.SHIP_PLACEMENT_MOVE) {
            return userInputLine.size() == 2;

        }
        return false;
    }

    private boolean playerInputInBoardRangeCheck(List<String> userInputLine) {
        return userInputLine.stream().allMatch(s -> s.matches("^[A-J]+([1-9]|10)"));
    }

    private boolean playerInputOrientationXYCheck(List<String> userInputLine) {
        if (moveType == MoveType.ATTACK_MOVE) {
            return true;
        }
        return Coordinates.coordinatesOrientationCheck(Coordinates.stringListToCoordinates(userInputLine));
    }

    private boolean playerInputLengthCheck(List<String> userInputLine) {
        if (moveType == MoveType.ATTACK_MOVE) {
            return true;
        }
        int length = currentShip.getSize() - 1;
        return Coordinates.coordinatesLengthCheck(Coordinates.stringListToCoordinates(userInputLine), length);
    }

    private boolean playerShipAreaCheck(List<String> userInputLine, GameBoard gameBoard) {
        if (moveType == MoveType.ATTACK_MOVE) {
            String coordinateInput = userInputLine.get(0);
            return Coordinates.coordinatesHitOrMisPlacementCheck(Coordinates.stringListToCoordinates(coordinateInput), gameBoard);
        }
        return Coordinates.coordinatesFreeFieldsCheckShipPlacement(Coordinates.stringListToCoordinates(userInputLine), gameBoard);
    }

}


