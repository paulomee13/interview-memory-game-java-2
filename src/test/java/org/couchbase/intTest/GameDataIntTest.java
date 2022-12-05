
package org.couchbase.intTest;

import org.couchbase.FileNameConstants;
import org.couchbase.GameSimulator;
import org.couchbase.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.couchbase.GameTest;

public class GameDataIntTest extends GameTest {
    @Test
    public void checkExecuteGuess() {
        try {
            init(FileNameConstants.file1);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            Assertions.assertEquals(gameSimulator.executeGuess(8), gameData.getGuesses().get(8).getScore());
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void checkSimulateGame() {
        try {
            init(FileNameConstants.file1);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.simulateGame();
        } catch (CustomException e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }
    }

    @Test
    public void checkNonEmptyBoardAfterSimulateGameException() {
        try {
            init(FileNameConstants.file9);
            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.simulateGame();
            Assertions.fail();
        } catch (CustomException e){
            Assertions.assertEquals(e.getMessage(), "Error in game data");
        }
    }
}
