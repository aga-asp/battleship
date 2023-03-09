package org.battleship;

import java.util.Scanner;

public class Game {

    Player playerOne = new Player();
    Player playerTwo = new Player();

    public void run() {
        playerInitialInformationSaving(playerOne, 1);
        playerShipPlacingAction(playerOne);
        playerTwo.setOpponentHiddenGameBoard(playerOne.getGameBoard());
        playerInitialInformationSaving(playerTwo, 2);
        playerShipPlacingAction(playerTwo);
        playerOne.setOpponentHiddenGameBoard(playerTwo.getGameBoard());
        playersSkirmish(playerOne, playerTwo);
    }

    private void playerInitialInformationSaving(Player player, int playerNumber) {
        String playerName = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.format("Please enter your name Player %d: ", playerNumber);
            String input = scanner.nextLine();
            if (input != null) {
                playerName = input;
            }
        } while (playerName.isEmpty());
        player.setPlayerName(playerName);
        player.setGameBoard(new GameBoard());
        player.setScore(17);
    }
    private void playerShipPlacingAction(Player player){
        for (int n = 0; n < ShipFleet.values().length; n++){
            System.out.format("Enter coordinates for ship %s (length of %d): ", ShipFleet.values()[n].getShipName(), ShipFleet.values()[n].getSize());
            Scanner scanner = new Scanner(System.in);
            player.createPlayerAction(scanner, ShipFleet.values()[n], MoveType.SHIP_PLACEMENT_MOVE);
        }
    }
    private void playersSkirmish(Player playerOne, Player playerTwo){
        do {
            playerAttackMove(playerOne);
            playerAttackMove(playerOne);
        }while (playerOne.getScore()!=0||playerTwo.getScore()!=0);

    }
    private void playerAttackMove(Player player){
        System.out.println("Place your missile "+ player.getPlayerName()+" : ");
        Scanner scanner = new Scanner(System.in);
        player.createPlayerAction(scanner, null, MoveType.ATTACK_MOVE);
    }

}
