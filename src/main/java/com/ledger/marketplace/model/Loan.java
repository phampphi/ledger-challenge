/*
 * FILENAME
 *     Loan.java
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
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ledger.marketplace.command.CommandData;

/**
 * <p>
 * Class describes a loan.
 * </p>
 *
 * @author pham.pphi
 **/
public class Loan implements CommandData
{
    public static final int EXPECTED_DATA_SIZE = 5;

    private LoanIdentifier identifier;
    private int principal;
    private int loanPeriodInYears;
    private int interestRate;

    private int interestAmount;
    private int totalAmount;
    private int emiAmount;
    private int emiNo;

    private List<LoanPayment> payments = new ArrayList<>();

    public Loan(final LoanIdentifier identifier, final int principal, final int loanPeriodInYears,
        final int interestRate)
    {
        checkNotNull(identifier, "identifier must not be null"); //$NON-NLS-1$
        checkArgument(principal > 0, "principal must be a positive number"); //$NON-NLS-1$
        checkArgument(loanPeriodInYears > 0, "principal must be a positive number"); //$NON-NLS-1$
        checkArgument(interestRate > 0, "principal must be a positive number"); //$NON-NLS-1$

        this.identifier = identifier;
        this.principal = principal;
        this.loanPeriodInYears = loanPeriodInYears;
        this.interestRate = interestRate;

        this.interestAmount = this.principal * this.loanPeriodInYears * this.interestRate / 100;
        this.totalAmount = this.principal + this.interestAmount;
        this.emiNo = this.loanPeriodInYears * 12;
        this.emiAmount = (int) Math.ceil((double) this.totalAmount / this.emiNo);
    }

    public LoanIdentifier getIdentifier()
    {
        return identifier;
    }

    public void processPayment(final LoanPayment payment)
    {
        checkNotNull(payment, "payment must not be null"); //$NON-NLS-1$
        payments.add(payment);
    }

    public Balance balanceAt(final int emi)
    {
        int totalLumSum = this.payments.stream().filter(p -> p.getEmi() <= emi)
            .collect(Collectors.summingInt(LoanPayment::getLumpSum));
        int amountPaid = (this.emiAmount * emi) + totalLumSum;
        int newEmiNo = (int) Math.ceil((double) (this.totalAmount - amountPaid) / this.emiAmount);
        int remainingEmi = Math.min(this.emiNo - emi, newEmiNo);
        return new Balance(this.identifier, amountPaid, remainingEmi);
    }
}
