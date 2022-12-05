package org.couchbase.unitTest;

import org.couchbase.FileNameConstants;
import org.couchbase.GameSimulator;
import org.couchbase.GameTest;
import org.couchbase.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameDataUnitTest extends GameTest {
    @Test
    void checkInitGameData(){
        try {
            init(FileNameConstants.file1);
            Assertions.assertEquals(12, gameData.getBoard().length);
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkBoardData(){
        try {
            init(FileNameConstants.file1);
            Assertions.assertEquals(12, gameData.getBoard().length);
            Assertions.assertTrue(gameData.getBoard().length > 0 && gameData.getBoard()[0].equals("W"));
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkGuessesData(){
        try {
            init(FileNameConstants.file1);
            Assertions.assertEquals(9, gameData.getGuesses().size());
            Assertions.assertTrue(gameData.getGuesses().size() > 0 && gameData.getGuesses().get(5).getGuess()[0] == 9);
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkGuessesScore(){
        try {
            init(FileNameConstants.file1);
            Assertions.assertTrue(gameData.getGuesses().size() > 0 && gameData.getGuesses().get(5).getScore() == 10);
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkValidateBoardData() {
        try {
            init(FileNameConstants.file1);
            gameData.validateBoardData();
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkValidateGuessData() {
        try {
            init(FileNameConstants.file1);
            gameData.validateGuessData();
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkExecuteGuess() {
        try {
            init(FileNameConstants.file1);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            Assertions.assertEquals(gameSimulator.executeGuess(7), gameData.getGuesses().get(7).getScore());
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    void checkEmptyBoard() {
        try {
            init(FileNameConstants.file1);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.simulateGame();
            Assertions.assertTrue(gameData.isBoardEmpty());
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }
}
