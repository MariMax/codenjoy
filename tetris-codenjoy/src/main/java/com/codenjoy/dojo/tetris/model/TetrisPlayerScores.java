package com.codenjoy.dojo.tetris.model;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.services.PlayerScores;

public class TetrisPlayerScores implements GlassEventListener, ChangeLevelListener, PlayerScores {

    public static final int FIGURE_DROPPED_SCORE = 1;
    public static final int ONE_LINE_REMOVED_SCORE = 10;
    public static final int TWO_LINES_REMOVED_SCORE = 30;
    public static final int THREE_LINES_REMOVED_SCORE = 50;
    public static final int FOUR_LINES_REMOVED_SCORE = 100;
    public static final int GLASS_OVERFLOWN_PENALTY = - ONE_LINE_REMOVED_SCORE;

    private volatile int score;
    private GameLevel level;

    public TetrisPlayerScores(int startScore) {
        this.score = startScore;
    }

    @Override
    public void glassOverflown() {
        int openCount = level.getFigureTypesToOpenCount();
        score += GLASS_OVERFLOWN_PENALTY * openCount;
        score = Math.max(0, score);
    }

    @Override
    public void linesRemoved(int amount) {
        int delta = 0;
        int openCount = level.getFigureTypesToOpenCount();
        switch (amount) {
            case 1:
                delta = ONE_LINE_REMOVED_SCORE * openCount;
                break;
            case 2:
                delta = TWO_LINES_REMOVED_SCORE * openCount;
                break;
            case 3:
                delta = THREE_LINES_REMOVED_SCORE * openCount;
                break;
            case 4:
                delta = FOUR_LINES_REMOVED_SCORE * openCount;
                break;
        }
        delta += score;
        score = delta;
    }

    @Override
    public void figureDropped(Figure figure) {
        score += FIGURE_DROPPED_SCORE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int clear() {
        return score = 0;
    }

    @Override
    public void levelChanged(int levelNumber, GameLevel level) {
        this.level = level;
    }

    @Override
    public void event(Object o) {

    }
}
