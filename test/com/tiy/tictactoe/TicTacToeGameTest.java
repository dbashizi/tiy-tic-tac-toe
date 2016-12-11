package com.tiy.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by dbashizi on 12/9/16.
 */
public class TicTacToeGameTest {

    TicTacToeGame game;
    Player player1;
    Player player2;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        game = new TicTacToeGame();
        game.setDebugMoves(false);
        player1 = new Player(PlayerIdentity.PLAYER1, "First Player");
        player2 = new Player(PlayerIdentity.PLAYER2, "Second Player");
    }

    @After
    public void tearDown() {
    }

    private void checkEmptyBoard(char[][] squares) {
        for (char[] cols : squares) {
            for (char square : cols) {
                assertEquals(' ', square);
            }
        }
    }

    @Test
    public void testWinnigMovesHorizontal() throws InvalidMoveException {
        // test first row
        game.makeMove(player1, 0, 0);
        game.makeMove(player1, 0, 1);
        game.makeMove(player1, 0, 2);

        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

        // test second row
        game = new TicTacToeGame();
        game.makeMove(player1, 1, 0);
        game.makeMove(player1, 1, 1);
        game.makeMove(player1, 1, 2);

        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

        // test third row
        game = new TicTacToeGame();
        game.makeMove(player2, 2, 0);
        game.makeMove(player2, 2, 1);
        game.makeMove(player2, 2, 2);

        assertEquals(player2, game.getWinner());
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void testNonWinningMoves() throws InvalidMoveException {
        game.makeMove(player1, 0, 0);
        game.makeMove(player1, 0, 1);
        game.makeMove(player1, 1, 0);
        assertEquals(null, game.getWinner());
        assertEquals(false, game.isGameOver());

        game.makeMove(player2, 0, 2);
        game.makeMove(player2, 1, 1);
        game.makeMove(player2, 1, 2);
        assertEquals(null, game.getWinner());
        assertEquals(false, game.isGameOver());

        game.makeMove(player2, 2, 0);
        game.makeMove(player1, 2, 1);
        assertEquals(null, game.getWinner());
        assertEquals(false, game.isGameOver());

        // make the last move
        game.setDebugMoves(true);
        game.makeMove(player1, 2, 2);
        // there is no winner, but game should be over
        assertEquals(null, game.getWinner());
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void testGetBoardAsSB() throws InvalidMoveException {
        game.makeMove(player1, 0, 0);
        game.makeMove(player1, 0, 1);
        game.makeMove(player1, 1, 0);

        game.makeMove(player2, 0, 2);
        game.makeMove(player2, 1, 1);
        game.makeMove(player2, 1, 2);

        game.makeMove(player2, 2, 0);
        game.makeMove(player1, 2, 1);

        game.setDebugMoves(true);
        game.makeMove(player1, 2, 2);

        StringBuilder[] rowsAsSB = game.getBoardAsSBs();
        assertEquals(" -------------", rowsAsSB[0].toString());
        assertEquals(" | X | X | O |", rowsAsSB[1].toString());
        assertEquals(" | X | O | O |", rowsAsSB[2].toString());
        assertEquals(" | O | X | X |", rowsAsSB[3].toString());
        assertEquals(" -------------", rowsAsSB[4].toString());
    }

    @Test
    public void testWinningMovesDiagonally() throws InvalidMoveException {
        game.makeMove(player1, 0, 0);
        game.makeMove(player1, 1, 1);
        game.makeMove(player1, 2, 2);
        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

        game = new TicTacToeGame();
        game.makeMove(player2, 0, 0);
        game.makeMove(player2, 1, 1);
        game.makeMove(player2, 2, 2);
        assertEquals(player2, game.getWinner());
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void testWinnigMovesVertical() throws InvalidMoveException {
        // test first column
        game.makeMove(player1, 0, 0);
        game.makeMove(player1, 1, 0);
        game.makeMove(player1, 2, 0);

        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

        // test second column
        game = new TicTacToeGame();
        game.makeMove(player1, 0, 1);
        game.makeMove(player1, 1, 1);
        game.makeMove(player1, 2, 1);

        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

        // test third column
        game = new TicTacToeGame();
        game.makeMove(player1, 0, 2);
        game.makeMove(player1, 1, 2);
        game.makeMove(player1, 2, 2);

        assertEquals(player1, game.getWinner());
        assertEquals(true, game.isGameOver());

    }

    @Test
    public void testFirstMove() throws InvalidMoveException {
        char[][] squares = game.getSquares();
        checkEmptyBoard(squares);

        game.makeMove(player1, 0, 0);
        assertEquals(player1.getIdentity().symbol(), squares[0][0]);

        // reset the game to test another move
        game = new TicTacToeGame();
        // new game means new squares => get the new reference
        squares = game.getSquares();
        checkEmptyBoard(squares);

        game.makeMove(player2, 2, 1);
        assertEquals(player2.getIdentity().symbol(), squares[2][1]);
    }

    @Test
    public void testNegativeMove() throws InvalidMoveException {
        expectedException.expect(InvalidMoveException.class);
        game.makeMove(player1, 0, -1);
    }

    @Test
    public void testOutOfBoundsMove() throws InvalidMoveException {
        expectedException.expect(InvalidMoveException.class);
        game.makeMove(player1, 0, 3);
    }

    @Test
    public void testMoveToBusySquare() throws InvalidMoveException {
        char[][] squares = game.getSquares();

        game.makeMove(player1, 0, 0);
        expectedException.expect(InvalidMoveException.class);
        game.makeMove(player1, 0, 0);
    }

    @Test
    public void testConversionToRowColIndex() {
        int rowIndex = game.getRowIndex(1);
        assertEquals(0, rowIndex);
        rowIndex = game.getRowIndex(2);
        assertEquals(0, rowIndex);
        rowIndex = game.getRowIndex(3);
        assertEquals(0, rowIndex);
        rowIndex = game.getRowIndex(4);
        assertEquals(1, rowIndex);
        rowIndex = game.getRowIndex(5);
        assertEquals(1, rowIndex);
        rowIndex = game.getRowIndex(6);
        assertEquals(1, rowIndex);
        rowIndex = game.getRowIndex(7);
        assertEquals(2, rowIndex);
        rowIndex = game.getRowIndex(8);
        assertEquals(2, rowIndex);
        rowIndex = game.getRowIndex(9);
        assertEquals(2, rowIndex);

        int colIndex = game.getColIndex(1);
        assertEquals(0, colIndex);
        colIndex = game.getColIndex(2);
        assertEquals(1, colIndex);
        colIndex = game.getColIndex(3);
        assertEquals(2, colIndex);
        colIndex = game.getColIndex(4);
        assertEquals(0, colIndex);
        colIndex = game.getColIndex(5);
        assertEquals(1, colIndex);
        colIndex = game.getColIndex(6);
        assertEquals(2, colIndex);
        colIndex = game.getColIndex(7);
        assertEquals(0, colIndex);
        colIndex = game.getColIndex(8);
        assertEquals(1, colIndex);
        colIndex = game.getColIndex(9);
        assertEquals(2, colIndex);
    }

    @Test
    public void testConversionToSquareIndex() {
        int squareIndex = game.getSquareIndex(0, 0);
        assertEquals(1, squareIndex);
        squareIndex = game.getSquareIndex(0,1);
        assertEquals(2, squareIndex);
        squareIndex = game.getSquareIndex(0,2);
        assertEquals(3, squareIndex);
        squareIndex = game.getSquareIndex(1,0);
        assertEquals(4, squareIndex);
        squareIndex = game.getSquareIndex(1,1);
        assertEquals(5, squareIndex);
        squareIndex = game.getSquareIndex(1,2);
        assertEquals(6, squareIndex);
        squareIndex = game.getSquareIndex(2,0);
        assertEquals(7, squareIndex);
        squareIndex = game.getSquareIndex(2,1);
        assertEquals(8, squareIndex);
        squareIndex = game.getSquareIndex(2,2);
        assertEquals(9, squareIndex);
    }

}