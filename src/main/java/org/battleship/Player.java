package org.battleship;

import java.util.*;

public class Player {

    String playerName;
    int Score;
    GameBoard gameBoard;

    GameBoard opponentHiddenGameBoard;

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setOpponentHiddenGameBoard(GameBoard opponentHiddenGameBoard) {
        this.opponentHiddenGameBoard = opponentHiddenGameBoard;
    }

    public Player() {
    }


    public void setScore(int score) {
        Score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getScore() {
        return Score;
    }

    // TODO: 06.03.2023 change from list to double coords
    public void createPlayerAction(Scanner scanner, ShipFleet shipFleet, MoveType moveType) {
        PlayerActionShipCreation playerActionShipCreation = new PlayerActionShipCreation(moveType);
        playerActionShipCreation.setCurrentShip(shipFleet);
        do {
            String input = scanner.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            Collections.sort(inputList);
            playerActionShipCreation.checkAllConditionsToPutOnGameBoard(this.gameBoard, this.opponentHiddenGameBoard, inputList);
        } while (!playerActionShipCreation.isPlayerInputCorrect());

    }

}
