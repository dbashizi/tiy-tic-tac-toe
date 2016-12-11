package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public class TicTacToeGame {

    private char[][] squares;
    private Player lastPlayerWhoMoved;
    private boolean gameOver = false;
    private Player winner;
    private boolean debugMoves = true;
    private int ultimateRowIndex;
    private int ultimateColIndex;

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
        this.ultimateRowIndex = 0;
        this.ultimateColIndex = 0;
    }

    public TicTacToeGame(boolean labelSquares) {
        this();
        if (labelSquares) {
            int squareIndex = 1;
            int rowIndex = 0;
            int colIndex = 0;
            for (char[] cols : squares) {
                for (char square : cols) {
                    squares[rowIndex][colIndex] = Character.forDigit(squareIndex, 10);
                    colIndex++;
                    squareIndex++;
                }
                colIndex = 0;
                rowIndex++;
            }
        }

    }

    public TicTacToeGame(boolean labelSquares, int ultimateRowIndex, int ultimateColIndex) {
        this(labelSquares);
        this.ultimateRowIndex = ultimateRowIndex;
        this.ultimateColIndex = ultimateColIndex;
    }

    public static int getRowIndex(int squareIndex) {
        return (squareIndex - 1) / 3;
    }

    public static int getColIndex(int squareIndex) {
        return (squareIndex - 1) % 3;
    }

    public static int getSquareIndex(int rowIndex, int colIndex) {
        return ((rowIndex * 3) + colIndex + 1);
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
        System.out.println(" -------------");
        for (char[] rows : squares) {
            for (char square : rows) {
                System.out.print(" | " + square);
            }
            System.out.println(" |");
        }
        System.out.println(" -------------");
    }

    public StringBuilder[] getBoardAsSBs() {
        int rowIndex = 0;
        StringBuilder[] rowsAsSBs = new StringBuilder[5];
        StringBuilder rowAsSB = new StringBuilder();
        int squareIndex = getSquareIndex(ultimateRowIndex, ultimateColIndex);
        for (int letterIndex = 0; letterIndex < 14; letterIndex++) {
            if (letterIndex == 0) {
                rowAsSB.append(squareIndex);
            } else {
                rowAsSB.append("-");
            }
        }
//        rowAsSB.append(" -------------");
        rowsAsSBs[rowIndex] = rowAsSB;
        rowIndex++;

        for (char[] rows : squares) {
            rowAsSB = new StringBuilder();
            for (char square : rows) {
                rowAsSB.append(" | " + square);
            }
            rowAsSB.append(" |");
            rowsAsSBs[rowIndex] = rowAsSB;
            rowIndex++;
        }
        rowAsSB = new StringBuilder();
        for (int letterIndex = 0; letterIndex < 14; letterIndex++) {
            rowAsSB.append("-");
        }
        rowsAsSBs[rowIndex] = rowAsSB;
        rowIndex++;

        return rowsAsSBs;
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

    public int getUltimateRowIndex() {
        return ultimateRowIndex;
    }

    public int getUltimateColIndex() {
        return ultimateColIndex;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        TicTacToeGame otherGame = (TicTacToeGame) otherObject;
        if (otherGame.getUltimateRowIndex() == ultimateRowIndex &&
                otherGame.getUltimateColIndex() == ultimateColIndex ) {
            return true;
        }
        return false;
    }

}
