/*
 * FILENAME
 *     TestBalanceHandler.java
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ledger.marketplace.handler.BalanceHandler;
import com.ledger.marketplace.handler.ConsoleOutputBalanceHandler;
import com.ledger.marketplace.model.Balance;

/**
 * <p>
 * A {@link BalanceHandler} to support testing purpose.
 * </p>
 *
 * @author pham.pphi
 **/
public class TestBalanceHandler extends ConsoleOutputBalanceHandler
{
    private List<Balance> balances = new ArrayList<>();

    @Override
    public void handle(final Balance balance)
    {
        super.handle(balance);
        balances.add(balance);
    }

    public void verify(final List<String> expectedBalanceData)
    {
        assertThat(balances.stream().map(Balance::toString).collect(Collectors.toList()))
            .containsExactlyElementsOf(expectedBalanceData);
    }
}
