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

}