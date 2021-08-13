/*
 * FILENAME
 *     MarketPlaceAppTest.java
 *
 * FILE LOCATION
 *     $Source$
 *
 * VERSION
 *     $Id$
 *         @version       $Revision$
 *         Check-Out Tag: $Name$
 *         Locked By:     $Lockers$
 */

package com.ledger.marketplace;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * <p>
 * Test class for {@link MarketPlaceApp}.
 * </p>
 *
 * @author pham.pphi
 **/
@SuppressWarnings("nls")
public class MarketPlaceAppTest
{
    private MarketPlaceApp app;

    /**
     * A special method to be called before each test case to set up initial data state to be used by all test cases.
     *
     * @throws Exception
     *             if something went wrong
     */
    @Before
    public void setup() throws Exception
    {
        app = new MarketPlaceApp();
        app.balanceHandler = new TestBalanceHandler();
    }

    @Test
    public void testRun1() throws Exception
    {
        Path input = Paths.get(this.getClass().getResource("input1.txt").toURI());
        app.run(input);
        ((TestBalanceHandler) app.balanceHandler).verify(
            Lists.newArrayList("IDIDI Dale 1000 55", "IDIDI Dale 8000 20", "MBI Harry 1044 12", "MBI Harry 0 24"));
    }

    @Test
    public void testRun2() throws Exception
    {
        Path input = Paths.get(this.getClass().getResource("input2.txt").toURI());
        app.run(input);

        ((TestBalanceHandler) app.balanceHandler).verify(
            Lists.newArrayList("IDIDI Dale 1326 9", "IDIDI Dale 3652 4", "UON Shelly 15856 3", "MBI Harry 9044 10"));
    }
}
