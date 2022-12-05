package org.couchbase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
 * Guess class for defining the components within guesses, i.e. the guess array and score
 * @version 1
 * 3 Dec 2022
 */
@Getter
@AllArgsConstructor
public class Guess {
    final private int[] guess;
    final private int score;
}
