package com.tiy.tictactoe;

import java.util.Scanner;

/**
 * Created by dbashizi on 12/10/16.
 */
public class TicTacToeUltimateGame {
    private TicTacToeGame[][] games;
    private boolean debugMoves = true;
    private TicTacToeGame activeGame = null;
    private boolean gameOver = false;
    private Player winner = null;

    public TicTacToeUltimateGame() {
        games = new TicTacToeGame[3][3];
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                games[rowIndex][colIndex] = new TicTacToeGame(false, rowIndex, colIndex);
            }
        }

        printBoard();
    }

    public TicTacToeGame getActiveGame() {
        return activeGame;
    }

    public void makeMove(Player player, TicTacToeGame game, int rowIndex, int colIndex) throws InvalidMoveException {
        if (activeGame != null) {
            // make sure the move is valid
            if (!activeGame.equals(game)) {
                throw new InvalidMoveException("You can only make a move on board " +
                        activeGame.getSquareIndex(activeGame.getUltimateRowIndex(), activeGame.getUltimateColIndex()));
            }
        }
        game.makeMove(player, rowIndex, colIndex);
        if (game.isGameOver()) {
            activeGame = null;
        } else {
            activeGame = games[rowIndex][colIndex];
            if (activeGame.isGameOver()) {
                activeGame = null;
            }
        }

        checkAndSetWinner(player);

        if (debugMoves) {
            printBoard();
        }
    }

    public TicTacToeGame getSpecificGame(int squareIndex) {
        int rowIndex = TicTacToeGame.getRowIndex(squareIndex);
        int colIndex = TicTacToeGame.getColIndex(squareIndex);
        return games[rowIndex][colIndex];
    }

    public void printBoard() {
        int fullRowIndex = 0;
        int baseIndex = 0;
        StringBuilder[] ultimateRowsAsSB = new StringBuilder[15];
        // initialize the array of SBs for the ultimate board
        for (int i = 0; i < 15; i++) {
            ultimateRowsAsSB[i] = new StringBuilder();
        }
        // loop through all the rows of the ultimate game
        // each row being made up of 3 regular games
        for (TicTacToeGame[] rowOfGames : games) {
            for (TicTacToeGame game : rowOfGames) {
                StringBuilder[] gameRowsAsSBs = game.getBoardAsSBs();
                int singleGameRowIndex = 0;
                baseIndex = fullRowIndex * 5;
                for (StringBuilder gameRow : gameRowsAsSBs) {
                    ultimateRowsAsSB[(singleGameRowIndex + baseIndex)].append(gameRow);
                    singleGameRowIndex++;
                }
            }
//            System.out.println("baseIndex = " + baseIndex);
            fullRowIndex++;
        }

        for (StringBuilder ultimateGameRow : ultimateRowsAsSB) {
            System.out.println(ultimateGameRow);
        }
        if (activeGame != null) {
            int squareIndex = activeGame.getSquareIndex(activeGame.getUltimateRowIndex(),
                                                        activeGame.getUltimateColIndex());
            System.out.println("Active Game: Game " + squareIndex);
            activeGame.printBoard();
        } else {
            if (!isGameOver()) {
                System.out.println("Active Game: Any Game");
            } else {
                System.out.println("Game Over!");
            }
        }
    }

    private void checkAndSetWinner(Player player) {
        // check if player won horizontally
        for (TicTacToeGame[] gameRow : games) {
            if (gameRow[0].getWinner() != null && gameRow[0].getWinner().equals(player) &&
                    gameRow[1].getWinner() != null && gameRow[1].getWinner().equals(player) &&
                    gameRow[2].getWinner() != null && gameRow[2].getWinner().equals(player)) {
                gameOver = true;
            }
        }

        // check if player won vertically
        for (int colIndex = 0; colIndex < 3; colIndex++) {
            if (games[0][colIndex].getWinner() != null && games[0][colIndex].getWinner().equals(player) &&
                    games[1][colIndex].getWinner() != null && games[1][colIndex].getWinner().equals(player) &&
                    games[2][colIndex].getWinner() != null && games[2][colIndex].getWinner().equals(player)) {
                gameOver = true;
            }
        }

        // check if player won diagonally
        if (games[0][0].getWinner() != null && games[0][0].getWinner().equals(player) &&
                games[1][1].getWinner() != null && games[1][1].getWinner().equals(player) &&
                games[2][2].getWinner() != null && games[2][2].getWinner().equals(player)) {
            gameOver = true;
        }

        if (gameOver) {
            winner = player;
        }

        // it's possible that the game is over and no one won
        boolean gameAvailable = false;
        for (TicTacToeGame[] gameRow : games) {
            for (TicTacToeGame game : gameRow) {
                if (!game.isGameOver()) {
                    gameAvailable = true;
                    break;
                }
            }
            if (gameAvailable) {
                break;
            }
        }
        if (!gameAvailable) {
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }
}
