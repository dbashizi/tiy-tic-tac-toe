package com.tiy.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by dbashizi on 12/10/16.
 */
public class TicTacToeUltimateGameTest {
    TicTacToeUltimateGame ultimateGame;
    Player player1;
    Player player2;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() {
        ultimateGame = new TicTacToeUltimateGame();
        player1 = new Player(PlayerIdentity.PLAYER1, "First Player");
        player2 = new Player(PlayerIdentity.PLAYER2, "Second Player");
    }

    @Test
    public void testMoves() throws InvalidMoveException {
        TicTacToeGame singleGame = ultimateGame.getSpecificGame(1);
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        char[][] squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[1][1]);

        singleGame = ultimateGame.getSpecificGame(5);
        ultimateGame.makeMove(player1, singleGame, 2, 2);
        squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[2][2]);
    }

    @Test
    public void testInvalidSecondMove() throws InvalidMoveException {
        TicTacToeGame singleGame = ultimateGame.getSpecificGame(1);
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        char[][] squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[1][1]);

        singleGame = ultimateGame.getSpecificGame(4);
        expectedException.expect(InvalidMoveException.class);
        ultimateGame.makeMove(player1, singleGame, 2, 2);
    }

    @Test
    public void testWinningMoves() throws InvalidMoveException {
        TicTacToeGame singleGame = ultimateGame.getSpecificGame(1);
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        char[][] squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[1][1]);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 0, 1);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 2, 1);

        // acive game should be null because player1 won a board on the previous move
        singleGame = ultimateGame.getActiveGame();
        assertNull(singleGame);

        // win the second game (on board 2)
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player2, singleGame, 0, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 2);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 0);

        // win the third game (on board 3)
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player2, singleGame, 0, 0);
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player1, singleGame, 1, 0);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 2);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 2);
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player1, singleGame, 1, 2);

        assertTrue(ultimateGame.isGameOver());
        assertNotNull(ultimateGame.getWinner());
        assertEquals(player1, ultimateGame.getWinner());

        assertTrue(false); // add a test to make sure the ultimate game detects a win in diagonal (and for player 2)
    }

    @Test
    public void testWinningInDiagonalInGame5() throws InvalidMoveException {
        TicTacToeGame singleGame = ultimateGame.getSpecificGame(1);
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        char[][] squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[1][1]);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 2, 2);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 0, 0);

        assertEquals(player1, singleGame.getWinner());
        // acive game should be null because player1 won a board on the previous move
        singleGame = ultimateGame.getActiveGame();
        assertNull(singleGame);

        // win the second game (on board 2)
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player2, singleGame, 0, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 1, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 2);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 0);

        // win the third game (on board 3)
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player1, singleGame, 1, 0);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 2);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 2, 2);
        singleGame = ultimateGame.getSpecificGame(3);
        ultimateGame.makeMove(player1, singleGame, 1, 2);

        assertTrue(singleGame.isGameOver());
        assertNull(ultimateGame.getActiveGame());
   }

    @Test
    public void testWinningInDiagonalInGame1() throws InvalidMoveException {
        TicTacToeGame singleGame = ultimateGame.getSpecificGame(1);
        ultimateGame.makeMove(player1, singleGame, 1, 1);
        char[][] squares = singleGame.getSquares();
        assertEquals(player1.getIdentity().symbol(), squares[1][1]);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 2, 2);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player2, singleGame, 0, 0);

        singleGame = ultimateGame.getActiveGame();
        ultimateGame.makeMove(player1, singleGame, 0, 0);

        assertEquals(player1, singleGame.getWinner());
        // acive game should be null because player1 won a board on the previous move
        singleGame = ultimateGame.getActiveGame();
        assertNull(singleGame);
    }
}