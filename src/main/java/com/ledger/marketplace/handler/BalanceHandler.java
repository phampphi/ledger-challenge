/*
 * FILENAME
 *     BalanceHandler.java
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

package com.ledger.marketplace.handler;

import com.ledger.marketplace.model.Balance;

/**
 * <p>
 * Interface describe the handler to interact with the {@link Balance}.
 * </p>
 *
 * @author pham.pphi
 **/
public interface BalanceHandler
{
    void handle(Balance balance);
}
