package org.couchbase;

import org.couchbase.exception.CustomException;
import org.couchbase.model.GameData;

/*
 * Main class, main entry point for simulating the game
 * @version 1
 * 4 Dec 2022
 */

public class Main {
    public static void main(String[] args) {
        GameRepositoryFileImp gameRepositoryFileImp = new GameRepositoryFileImp();
        try {
            GameData gameData = gameRepositoryFileImp.initGameData(FileNameConstants.file1);

            GameSimulator gameSimulator = new GameSimulator(gameData);
            gameSimulator.simulateGame();
        }
        catch (CustomException e){
            System.out.println(e.getMessage());
        }
    }
}