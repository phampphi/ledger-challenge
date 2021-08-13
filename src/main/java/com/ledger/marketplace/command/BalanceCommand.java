/*
 * FILENAME
 *     BalanceCommand.java
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

import java.util.List;

import com.google.common.base.Splitter;
import com.ledger.marketplace.model.LoanBalanceRequest;

/**
 * <p>
 * Balance command to get current loan balance.
 * </p>
 *
 * @author pham.pphi
 **/
public class BalanceCommand implements Command
{
    static final CommandFactory FACTORY = new BalanceCommandFactory();

    private LoanBalanceRequest request;

    public BalanceCommand(final LoanBalanceRequest request)
    {
        this.request = request;
    }

    @Override
    public LoanBalanceRequest getData()
    {
        return request;
    }

    static class BalanceCommandFactory extends AbstractCommandFactory
    {
        @Override
        public BalanceCommand createInstance(final String data)
        {
            List<String> inputData = Splitter.on(' ').omitEmptyStrings().trimResults().splitToList(data);
            return new BalanceCommand(
                new LoanBalanceRequest(createLoanIdentifier(inputData), Integer.parseInt(inputData.get(3))));
        }

        @Override
        protected String prefix()
        {
            return "BALANCE"; //$NON-NLS-1$
        }

        @Override
        protected int expectedSize()
        {
            return 4;
        }
    }

}
