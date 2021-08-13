/*
 * FILENAME
 *     LoanBalanceRequest.java
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
 * Class describes a loan balance request.
 * </p>
 *
 * @author pham.pphi
 **/
public class LoanBalanceRequest implements CommandData
{
    private LoanIdentifier identifier;
    private int emi;

    public LoanBalanceRequest(final LoanIdentifier identifier, final int emi)
    {
        this.identifier = identifier;
        this.emi = emi;
    }

    public LoanIdentifier getIdentifier()
    {
        return identifier;
    }

    public int getEmi()
    {
        return emi;
    }

}
