/*
 * FILENAME
 *     AbstractCommandFactory.java
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

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import com.google.common.base.Splitter;
import com.ledger.marketplace.model.LoanIdentifier;

/**
 * <p>
 * Abstract factory for all {@link CommandFactory}..
 * </p>
 *
 * @author pham.pphi
 **/
public abstract class AbstractCommandFactory implements CommandFactory
{
    @Override
    public boolean isApplied(final String data)
    {
        return !isNullOrEmpty(data) && data.startsWith(prefix())
            && Splitter.on(' ').omitEmptyStrings().trimResults().splitToList(data).size() == expectedSize();
    }

    protected LoanIdentifier createLoanIdentifier(final List<String> inputData)
    {
        return new LoanIdentifier(inputData.get(1), inputData.get(2));
    }

    protected abstract String prefix();

    protected abstract int expectedSize();
}
