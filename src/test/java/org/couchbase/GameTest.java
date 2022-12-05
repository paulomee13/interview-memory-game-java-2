package org.couchbase;

import org.couchbase.exception.CustomException;
import org.couchbase.model.GameData;

/*
 * GameTest abstract class required for the unit and integration test classes,
 * needed to initialise the classes in intTest and unitTest
 * @version 1
 * 3 Dec 2022
 */
public abstract class GameTest {
    protected GameRepositoryFileImp fileImp;
    protected GameData gameData;

    public GameTest() {
        this.fileImp = new GameRepositoryFileImp();
    }

    protected void init(String fileName) throws CustomException {
        gameData = fileImp.initGameData(fileName);
    }
}
