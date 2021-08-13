/*
 * FILENAME
 *     Balance.java
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

package com.ledger.marketplace.model;

/**
 * <p>
 * Class describes a loan balance.
 * </p>
 *
 * @author pham.pphi
 **/
public class Balance
{
    private LoanIdentifier identifier;
    private int amountPaid;
    private int remainingEmi;

    public Balance(final LoanIdentifier identifier, final int amountPaid, final int remainingEmi)
    {
        super();
        this.identifier = identifier;
        this.amountPaid = amountPaid;
        this.remainingEmi = remainingEmi;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %d %d", //$NON-NLS-1$
            this.identifier.getBankName(), this.identifier.getBorrowerName(), this.amountPaid, this.remainingEmi);
    }
}
