package com.tiy.tictactoe;

import java.util.Scanner;

/**
 * Created by dbashizi on 12/10/16.
 */
public class TicTacToeUltimateGame {
    private TicTacToeGame[][] games;
    private boolean debugMoves = true;
    private TicTacToeGame activeGame = null;

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

        activeGame = games[rowIndex][colIndex];
        if (activeGame.isGameOver()) {
            activeGame = null;
        }

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
            System.out.println("Active Game: Any Game");
        }
    }
}
