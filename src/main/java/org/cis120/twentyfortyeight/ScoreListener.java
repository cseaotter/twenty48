package org.cis120.twentyfortyeight;

/**
 * This is a listener interface that provides functions to update scores and whether the current
 * game state has lost or not.
 */
public interface ScoreListener {
    /**
     * This method is intended to update scores.
     * @param currScore  current score of the game
     */
    void onUpdate(int currScore);

    /**
     * Displays the result of the game, whether it has lost or not
     * @param res result of the game
     */
    void result(String res);
}
