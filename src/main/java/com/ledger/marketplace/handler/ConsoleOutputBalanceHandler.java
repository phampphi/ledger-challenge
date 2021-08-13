/*
 * FILENAME
 *     ConsoleOutputBalanceHandler.java
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

package com.ledger.marketplace.handler;

import static com.google.common.base.Preconditions.checkNotNull;

import com.ledger.marketplace.model.Balance;

/**
 * <p>
 * A {@link BalanceHandler} to print the balance to console.
 * </p>
 *
 * @author pham.pphi
 **/
public class ConsoleOutputBalanceHandler implements BalanceHandler
{
    @Override
    public void handle(final Balance balance)
    {
        checkNotNull(balance, "balance must not be null"); //$NON-NLS-1$
        System.out.println(balance.toString());
    }
}
