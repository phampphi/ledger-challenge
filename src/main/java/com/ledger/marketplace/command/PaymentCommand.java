/*
 * FILENAME
 *     PaymentCommand.java
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
import com.ledger.marketplace.model.LoanPayment;

/**
 * <p>
 * Payment command to add payment to the loan.
 * </p>
 *
 * @author pham.pphi
 **/
public class PaymentCommand implements Command
{
    static final CommandFactory FACTORY = new PaymentCommandFactory();

    private LoanPayment payment;

    public PaymentCommand(final LoanPayment payment)
    {
        this.payment = payment;
    }

    @Override
    public LoanPayment getData()
    {
        return payment;
    }

    static class PaymentCommandFactory extends AbstractCommandFactory
    {

        @Override
        public PaymentCommand createInstance(final String data)
        {
            List<String> inputData = Splitter.on(' ').omitEmptyStrings().trimResults().splitToList(data);
            return new PaymentCommand(new LoanPayment(createLoanIdentifier(inputData),
                Integer.parseInt(inputData.get(3)), Integer.parseInt(inputData.get(4))));
        }

        @Override
        protected String prefix()
        {
            return "PAYMENT"; //$NON-NLS-1$
        }

        @Override
        protected int expectedSize()
        {
            return 5;
        }
    }
}
