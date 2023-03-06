package org.battleship;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {

    String playerName;
    int Score;
    GameBoard gameBoard;

    public Player() {
    }

    public void Player(String playerName, GameBoard gameBoard) {
        this.playerName = playerName;
        this.gameBoard = new GameBoard();
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    // TODO: 06.03.2023 change from int to enum
    // TODO: 06.03.2023 change from list to double coords
    public void createPlayerAction(Scanner scanner, int numberOfShip) {
        PlayerAction playerAction = new PlayerAction(this.gameBoard);
        playerAction.setCurrentShipNumber(numberOfShip);
        do {
            String input = scanner.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            playerAction.setUserInputLine(inputList);
            playerAction.playerInputCheck();
        } while (!playerAction.isPlayerInputCorrect());

    }
}
