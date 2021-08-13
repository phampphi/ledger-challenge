/*
 * FILENAME
 *     LoanPayment.java
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

import com.ledger.marketplace.command.CommandData;

/**
 * <p>
 * Class describes a loan payment.
 * </p>
 *
 * @author pham.pphi
 **/
public class LoanPayment implements CommandData
{
    private LoanIdentifier identifier;
    private int lumpSum;
    private int emi;

    public LoanPayment(final LoanIdentifier identifier, final int lumpSum, final int emi)
    {
        this.identifier = identifier;
        this.lumpSum = lumpSum;
        this.emi = emi;
    }

    public LoanIdentifier getIdentifier()
    {
        return identifier;
    }

    public int getLumpSum()
    {
        return lumpSum;
    }

    public int getEmi()
    {
        return emi;
    }

}
