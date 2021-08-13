/*
 * FILENAME
 *     LoanIdentifier.java
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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.google.common.base.Objects;

/**
 * <p>
 * Class describes a loan identifier.
 * </p>
 *
 * @author pham.pphi
 **/
public class LoanIdentifier
{
    private String bankName;
    private String borrowerName;

    public LoanIdentifier(final String bankName, final String borrowerName)
    {
        checkArgument(!isNullOrEmpty(bankName), "bankName must not be null or emplty"); //$NON-NLS-1$
        checkArgument(!isNullOrEmpty(borrowerName), "borrowerName must not be null or emplty"); //$NON-NLS-1$
        this.bankName = bankName;
        this.borrowerName = borrowerName;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof LoanIdentifier))
            return false;
        LoanIdentifier other = (LoanIdentifier) obj;
        return Objects.equal(this.bankName, other.bankName) && Objects.equal(this.borrowerName, other.borrowerName);
    }

    @Override
    public int hashCode()
    {
        return bankName.hashCode() * borrowerName.hashCode();
    }

    public String getIdentifier()
    {
        return String.format("%s_%s", bankName, borrowerName); //$NON-NLS-1$
    }

    public String getBankName()
    {
        return bankName;
    }

    public String getBorrowerName()
    {
        return borrowerName;
    }
}
