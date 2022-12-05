package org.couchbase;

import org.couchbase.exception.CustomException;
import org.couchbase.model.GameData;
import org.couchbase.model.Guess;

import java.util.List;

/*
 * GameSimulator class for simulation of Concentration game
 * @version 1
 * 3 Dec 2022
 */

public class GameSimulator {
    private final GameData gameData;
    private String[] board;
    private List<Guess> guesses;

    public GameSimulator(GameData gameData) {
        this.gameData = gameData;
        board = gameData.getBoard();
        guesses = gameData.getGuesses();
    }

    /**
     * Calculate if the total score is correct based on the guesses data provided
     * @param index is the index value(from 0 to length-1 of the guesses) indicating the position of the guess
     * @return the score calculated in the overloaded method executeGuess(int index, int previousScore, boolean print)
     * @throws CustomException message in case of exceptions from the called method
     */
    public int executeGuess(int index) throws CustomException{
        return executeGuess(index, (index == 0) ? 0 : guesses.get(index - 1).getScore(), true);
    }

    /**
     * Check if the cards at the guess match, if yes, mark them '#' (indicates removed), if no match then keep as it is
     * Further calculate the score for the current guess and check if it is equal to given score in game data
     * @param index is the index value of the guess
     * @param previousScore is the score of the previous guess, which is 0 if index=0
     * @param print to print statement only when true
     * @return the current score at the guess which is calculated as previousScore - 1 if the 2 cards for the guess
     * are a match, and previousScore + 7 if the cards for the guess are different
     * @throws CustomException message if data is incorrect - 1) If the board card has already been removed
     * 2) If the actual score calculated is different from the score in the game data provided
     */
    public int executeGuess(int index, int previousScore, boolean print) throws CustomException{
        Guess guess = gameData.getGuesses().get(index);

        if(board[guess.getGuess()[0] - 1].equals("#") || board[guess.getGuess()[1] - 1].equals("#")) {
            throw new CustomException("The game data is incorrect");
        }

        if(board[guess.getGuess()[0] - 1].equals(board[guess.getGuess()[1] - 1])) {
            previousScore -= 1;
            board[guess.getGuess()[0] - 1] = "#";
            board[guess.getGuess()[1] - 1] = "#";
        } else{
            previousScore += 7;
        }

        //check the updated previousScore with the guess score
        if(previousScore == guess.getScore()) {
            if (print) System.out.println("The game data is correct");
        } else {
            throw new CustomException("The game data is incorrect");
        }
        return guess.getScore();
    }

    /**
     * Simulate the entire Concentration memory game if the Board data and Guess data is valid, till all the cards
     * are removed and the board is empty
     * @throws CustomException if the board is not empty i.e.all matches not found
     */
    public void simulateGame() throws CustomException {
        gameData.validateBoardData();
        gameData.validateGuessData();

        int currentScore = 0;
        for(int i = 0; i < guesses.size(); i++){
            currentScore = executeGuess(i, currentScore, false);
        }
        if(gameData.isBoardEmpty()) {
            System.out.println("The game data is correct");
        } else {
            throw new CustomException("Error in game data");
        }
    }
}
