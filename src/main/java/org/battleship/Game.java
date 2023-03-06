package org.battleship;

import java.util.Scanner;

public class Game {

    Player playerOne = new Player();
    Player playerTwo = new Player();

    public void run() {
        playerInitialInformationSaving(playerOne, 1);
        playerShipPlacingAction(playerOne);
        playerInitialInformationSaving(playerTwo, 2);
        playerShipPlacingAction(playerTwo);
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
//        var a = ShipFleet.values()[1];
        for (int n = 0; n < 5; n++){
            System.out.format("Enter coordinates for ship %s: ", ShipFleet.values()[n].getShipName());
            Scanner scanner = new Scanner(System.in);
            player.createPlayerAction(scanner, n);
        }
    }
}
