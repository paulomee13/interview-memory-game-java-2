package org.couchbase;

import com.google.gson.Gson;
import org.couchbase.exception.CustomException;
import org.couchbase.model.GameData;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * GameRepositoryFileImp class for reading json doc and returning java object
 * @version 1
 * 3 Dec 2022
 */

public class GameRepositoryFileImp {
    /**
     *
     * @param fileName - name/url of the json file to be read
     * @return POJO of class GameData
     * @throws CustomException if program fails
     */
    public GameData initGameData(String fileName) throws CustomException {
        try {
            Gson gson = new Gson();

            // convert JSON file to Game Data object
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            GameData gameData = gson.fromJson(reader, GameData.class);

            return gameData;
        }
        //If the program fails during parsing the given input
        catch (Exception e){
            throw new CustomException("The program has run into an error");
        }
    }
}
