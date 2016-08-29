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


import static com.codenjoy.dojo.tetris.model.Figure.Type.I;
import static com.codenjoy.dojo.tetris.model.Figure.Type.O;
import static com.codenjoy.dojo.tetris.model.GlassEvent.Type.LINES_REMOVED;

/**
 * User: oleksandr.baglai
 * Date: 11/18/12
 * Time: 6:37 PM
 */
public class MockLevels extends Levels {

    public static final int LINES_REMOVED_FOR_NEXT_LEVEL = 4;

    public MockLevels(PlayerFigures queue) {
        super(new FigureTypesLevel(queue,
                new GlassEvent<>(LINES_REMOVED, LINES_REMOVED_FOR_NEXT_LEVEL),
                        I),

                new FigureTypesLevel(queue,
                        new GlassEvent<>(LINES_REMOVED, LINES_REMOVED_FOR_NEXT_LEVEL),
                        O),

                new FigureTypesLevel(queue,
                        new GlassEvent<>(LINES_REMOVED, LINES_REMOVED_FOR_NEXT_LEVEL),
                        I, O));
    }
}
