package org.battleship;

public class BoardPrinter {

    static GameBoard gameBoard;

    public BoardPrinter(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    public static void printBoard(){
        Field[][] fields = gameBoard.getFields();
        System.out.print(" ");
        for (int n = 1; n <= 10; n++){
            System.out.print(n+ " ");
        }
        System.out.println();
        for (int n = 0; n < fields.length; n++){
            System.out.print((char)('A'+n)+ " ");
            for (int i = 0; i < fields[0].length; i++){
                System.out.print(fields[n][i].getFieldType().getFieldSign()+" ");
            }
            System.out.println();
        }
    }
}
