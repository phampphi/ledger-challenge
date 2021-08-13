/*
 * FILENAME
 *     CommandFactory.java
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

/**
 * <p>
 * Interface describe the command factory.
 * </p>
 *
 * @author pham.pphi
 **/
public interface CommandFactory
{
    boolean isApplied(String data);

    Command createInstance(String data);
}
