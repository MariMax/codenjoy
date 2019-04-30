package com.codenjoy.dojo.lemonade.model;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
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


import org.junit.Test;

import static org.junit.Assert.*;

public class SimulatorTest {

    @Test
    public void createSimulator_makeZeroMove_checkBeforeAndAfter() {

        Simulator sut = new Simulator(1);

        assertEquals(0, sut.getDay());
        assertEquals(0.02, sut.getLemonadeCost(), 0.001);
        assertTrue(sut.getMessages().startsWith("\nHI! WELCOME TO LEMONSVILLE, CALIFORNIA!\n"));
        assertEquals("UNKNOWN", sut.getWeatherForecast());
        assertEquals(0, sut.getLemonadeMade());
        assertEquals(0, sut.getSignsMade());
        assertEquals(0.0, sut.getLemonadePrice(), 0.001);
        assertEquals(0, sut.getLemonadeSold());
        assertEquals(0.00, sut.getIncome(), 0.001);
        assertEquals(0.00, sut.getExpenses(), 0.001);
        assertEquals(0.00, sut.getProfit(), 0.001);
        assertEquals(2.00, sut.getAssets(), 0.001);
        assertFalse(sut.isBankrupt());
    }

    @Test
    public void simulateSeveralDays_checkInputLimits() {

        Simulator sut = new Simulator(1);

        assertEquals(0, sut.getDay());
        assertEquals(2.00, sut.getAssets(), 0.001);

        sut.step(0, 0, 0);  // valid input
        assertEquals(1, sut.getDay());
        assertEquals(2.00, sut.getAssets(), 0.001);

        sut.step(1001, 0, 0);
        assertTrue(sut.getMessages().startsWith("lemonadeToMake parameter"));
        assertEquals(1, sut.getDay());

        sut.step(-1, 0, 0);
        assertTrue(sut.getMessages().startsWith("lemonadeToMake parameter"));
        assertEquals(1, sut.getDay());

        sut.step(0, 51, 0);
        assertTrue(sut.getMessages().startsWith("signsToMake parameter"));
        assertEquals(1, sut.getDay());

        sut.step(0, -1, 0);
        assertTrue(sut.getMessages().startsWith("signsToMake parameter"));
        assertEquals(1, sut.getDay());

        sut.step(0, 0, 101);
        assertTrue(sut.getMessages().startsWith("lemonadePriceCents parameter"));
        assertEquals(1, sut.getDay());

        sut.step(0, 0, -1);
        assertTrue(sut.getMessages().startsWith("lemonadePriceCents parameter"));
        assertEquals(1, sut.getDay());

        sut.step(-1, -1, -1);  // all three are invalid
        assertTrue(sut.getMessages().contains("lemonadePriceCents parameter"));
        assertTrue(sut.getMessages().contains("signsToMake parameter"));
        assertTrue(sut.getMessages().contains("lemonadePriceCents parameter"));
        assertEquals(1, sut.getDay());
    }

    @Test
    public void simulateSeveralDays_checkBeforeAndAfter() {

        Simulator sut = new Simulator(1);

        assertEquals(0, sut.getDay());
        sut.step(0, 0, 0);

        assertEquals(1, sut.getDay());
        assertEquals(2.00, sut.getAssets(), 0.001);
        assertEquals("CLOUDY", sut.getWeatherForecast());
        assertEquals(0.02, sut.getLemonadeCost(), 0.001);

        // day 1 step
        sut.step(20, 2, 12);

        assertEquals(2, sut.getDay());
        assertEquals(20, sut.getLemonadeMade());
        assertEquals(2, sut.getSignsMade());
        assertEquals(0.12, sut.getLemonadePrice(), 0.001);
        assertEquals(20, sut.getLemonadeSold());
        assertEquals(2.40, sut.getIncome(), 0.001);
        assertEquals(0.70, sut.getExpenses(), 0.001);
        assertEquals(1.70, sut.getProfit(), 0.001);
        assertEquals(3.70, sut.getAssets(), 0.001);
        assertFalse(sut.isBankrupt());
        assertEquals("SUNNY", sut.getWeatherForecast());
        assertEquals(0.02, sut.getLemonadeCost(), 0.001);

        // day 2 step
        sut.step(20, 2, 12);

        assertEquals(3, sut.getDay());
        assertEquals(20, sut.getLemonadeMade());
        assertEquals(2, sut.getSignsMade());
        assertEquals(0.12, sut.getLemonadePrice(), 0.001);
        assertEquals(20, sut.getLemonadeSold());
        assertEquals(2.40, sut.getIncome(), 0.001);
        assertEquals(0.70, sut.getExpenses(), 0.001);
        assertEquals(1.70, sut.getProfit(), 0.001);
        assertEquals(5.40, sut.getAssets(), 0.001);
        assertFalse(sut.isBankrupt());
        assertEquals("SUNNY", sut.getWeatherForecast());
        assertEquals(0.04, sut.getLemonadeCost(), 0.001);

        // day 3 step
        sut.step(20, 2, 12);

        assertEquals(4, sut.getDay());
        assertEquals(20, sut.getLemonadeMade());
        assertEquals(2, sut.getSignsMade());
        assertEquals(0.12, sut.getLemonadePrice(), 0.001);
        assertEquals(20, sut.getLemonadeSold());
        assertEquals(2.40, sut.getIncome(), 0.001);
        assertEquals(1.10, sut.getExpenses(), 0.001);
        assertEquals(1.30, sut.getProfit(), 0.001);
        assertEquals(6.70, sut.getAssets(), 0.001);
        assertFalse(sut.isBankrupt());
        assertEquals("SUNNY", sut.getWeatherForecast());
        assertEquals(0.04, sut.getLemonadeCost(), 0.001);
    }
}
