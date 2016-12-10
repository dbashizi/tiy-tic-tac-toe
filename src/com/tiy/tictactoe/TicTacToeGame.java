package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public class TicTacToeGame {

    private char[][] squares;
    private Player lastPlayerWhoMoved;
    private boolean gameOver = false;
    private Player winner;
    private boolean debugMoves = false;


    public TicTacToeGame() {
        squares = new char[3][3];
        int colIndex = 0;
        int rowIndex = 0;
        for (char[] cols : squares) {
            for (char square : cols) {
                squares[rowIndex][colIndex] = ' ';
                colIndex++;
            }
            colIndex = 0;
            rowIndex++;
        }
    }

    public void makeMove(Player player, int row, int col) throws InvalidMoveException {
        if (row > 2 || row < 0 || col > 2 || col < 0) {
            throw new InvalidMoveException("Playing with a 3x3 board");
        }
        if (squares[row][col] != ' ') {
            throw new InvalidMoveException("This square is already occupied by a previous move");
        }
        squares[row][col] = player.getIdentity().symbol();
        lastPlayerWhoMoved = player;
        checkAndSetWinner(player);
        if (debugMoves) {
            printBoard();
        }
    }

    private void checkAndSetWinner(Player player) {
        // check if player won horizontally
        for (char[] cols : squares) {
            if (cols[0] == cols[1] && cols[0] == cols[2] && cols[0] != ' ') {
                gameOver = true;
            }
        }

        // check if player won vertically
        for (int colIndex = 0; colIndex < 3; colIndex++) {
            if (squares[0][colIndex] == squares[1][colIndex] &&
                    squares[1][colIndex] == squares[2][colIndex] &&
                    squares[0][colIndex] != ' ') {
                gameOver = true;
            }
        }

        // check if player won diagonally
        if (squares[0][0] == squares[1][1] &&
                squares[0][0] == squares[2][2] &&
                squares[0][0] != ' ') {
            gameOver = true;
        }

        if (gameOver) {
            winner = player;
        }

        // it's possible that the game is over and no one won
        boolean squaresAvailable = false;
        for (char[] cols : squares) {
            for (char square : cols) {
                if (square == ' ') {
                    squaresAvailable = true;
                    break;
                }
            }
            if (squaresAvailable) {
                break;
            }
        }
        if (!squaresAvailable) {
            gameOver = true;
        }
    }

    public char[][] getSquares() {
        return squares;
    }

    public void printBoard() {
        int colIndex = 0;
        int rowIndex = 0;
        System.out.println(" -------------");
        for (char[] cols : squares) {
            for (char square : cols) {
                System.out.print(" | " + square);
                colIndex++;
            }
            System.out.println(" |");
            colIndex = 0;
            rowIndex++;
        }
        System.out.println(" -------------");
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public void setDebugMoves(boolean debugMoves) {
        this.debugMoves = debugMoves;
    }
}
