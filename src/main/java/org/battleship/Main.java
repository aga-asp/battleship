package org.battleship;

public class Main {
    public static void main(String[] args) {
        startNewGame();
    }

    private static void startNewGame() {
        Game game = new Game();
        game.run();
    }
}