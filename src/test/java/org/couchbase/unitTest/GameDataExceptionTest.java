package org.couchbase.unitTest;

import org.couchbase.FileNameConstants;
import org.couchbase.GameSimulator;
import org.couchbase.GameTest;
import org.couchbase.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameDataExceptionTest extends GameTest {
    @Test
    void checkOddCards(){
        try {
            init(FileNameConstants.file2);
            gameData.validateBoardData();
            Assertions.fail();
        } catch (CustomException e) {
            Assertions.assertEquals(e.getMessage(), "The board data is incorrect");
        }
    }

    @Test
    void checkUnpairedCards(){
        try {
            init(FileNameConstants.file6);
            gameData.validateBoardData();
            Assertions.fail();
        } catch (CustomException e) {
            Assertions.assertEquals(e.getMessage(), "The board data is incorrect");
        }
    }

    @Test
    void checkInvalidGuess(){
        try {
            init(FileNameConstants.file5);
            Assertions.fail();
        } catch (CustomException e) {
            Assertions.assertEquals(e.getMessage(), "The program has run into an error");
        }
    }

    @Test
    void checkInsufficientGuessData() {
        try {
            init(FileNameConstants.file7);
            gameData.validateGuessData();
            Assertions.fail();
        } catch (CustomException e){
            Assertions.assertEquals(e.getMessage(), "Error in game data");
        }
    }

    @Test
    void checkRepeatedGuessData() {
        try {
            init(FileNameConstants.file3); //same for FileNameConstants.file 4
            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.executeGuess(5);
            gameSimulator.executeGuess(10);
            Assertions.fail();
        } catch (CustomException e){
            Assertions.assertEquals(e.getMessage(), "The game data is incorrect");
        }
    }

    @Test
    void checkIncorrectScoreData() {
        try {
            init(FileNameConstants.file8);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.executeGuess(0);
            gameSimulator.executeGuess(1);
            Assertions.fail();
        } catch (CustomException e){
            Assertions.assertEquals(e.getMessage(), "The game data is incorrect");
        }
    }
}
