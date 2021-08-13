/*
 * FILENAME
 *     CommandParser.java
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

package com.ledger.marketplace.command;

import com.google.common.collect.ImmutableList;

/**
 * <p>
 * Utility class to parse command from a data string.
 * </p>
 *
 * @author pham.pphi
 **/
public class CommandParser
{
    private static final ImmutableList<CommandFactory> FACTORIES =
        ImmutableList.of(LoanCommand.FACTORY, PaymentCommand.FACTORY, BalanceCommand.FACTORY);

    public static Command parse(final String data)
    {
        return FACTORIES.stream().filter(f -> f.isApplied(data)).findFirst().orElse(NoopCommand.FACTORY)
            .createInstance(data);
    }
}
