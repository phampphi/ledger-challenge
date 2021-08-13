/*
 * FILENAME
 *     LoanCommand.java
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
import com.ledger.marketplace.model.Loan;

/**
 * <p>
 * Loan command to generate a loan.
 * </p>
 *
 * @author pham.pphi
 **/
public class LoanCommand implements Command
{
    static final CommandFactory FACTORY = new BalanceCommandFactory();

    private Loan loan;

    public LoanCommand(final Loan loan)
    {
        this.loan = loan;
    }

    @Override
    public Loan getData()
    {
        return loan;
    }

    static class BalanceCommandFactory extends AbstractCommandFactory
    {
        @Override
        public LoanCommand createInstance(final String data)
        {
            List<String> inputData = Splitter.on(' ').omitEmptyStrings().trimResults().splitToList(data);
            return new LoanCommand(new Loan(createLoanIdentifier(inputData), Integer.parseInt(inputData.get(3)),
                Integer.parseInt(inputData.get(4)), Integer.parseInt(inputData.get(5))));
        }

        @Override
        protected String prefix()
        {
            return "LOAN"; //$NON-NLS-1$
        }

        @Override
        protected int expectedSize()
        {
            return 6;
        }
    }
}
