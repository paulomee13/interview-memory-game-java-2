package org.couchbase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.couchbase.exception.CustomException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/*
 * GameData class for defining the Board and Guess, also
 * has functions to check if the board is empty, and validate whether the board data and guess data is correct
 * @version 1
 * 3 Dec 2022
 */


@AllArgsConstructor
@Builder
@Getter
public class GameData {
    private String[] board;
    final private List<Guess> guesses;

    /**
     * Check if board is empty
     * @return true if cards are marked '#' i.e. removed from the board after match found, else return false.
     */
    public boolean isBoardEmpty(){
        for(String card : board)
            if(!card.equals("#")){
                return false;
            }
        return true;
    }

    /**
     * Validate if the board data is correct and there are matching pairs of letters provided
     * @throws CustomException message thrown if- 1) board size is not even, and 2) if each type of card is not even
     */
    public void validateBoardData() throws CustomException {
        if(board.length % 2 != 0) {
            throw new CustomException("The board data is incorrect");
        }
        HashMap<String, Integer> frequencyCounter = new HashMap<>();

        for(String card : board){
            frequencyCounter.put(card, frequencyCounter.getOrDefault(card, 0) + 1);
        }

        for(String card : frequencyCounter.keySet())
            if(frequencyCounter.get(card) % 2 == 1) {
                throw new CustomException("The board data is incorrect");
            }
    }

    /**
     * Check if some guess data is missing, minimum guesses done or not checked
     * @throws CustomException message when the guesses are less than half the size of the board,as 2 cards are flipped
     * at a time for a guess. So atleast board.length / 2 guesses have to be made to cover all cards, else exception.
     */
    public void validateGuessData() throws CustomException{
        if(guesses.size() < board.length / 2) {
            throw new CustomException("Error in game data");
        }
    }
}
