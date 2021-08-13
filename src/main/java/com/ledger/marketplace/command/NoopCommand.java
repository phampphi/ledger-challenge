/*
 * FILENAME
 *     NoopCommand.java
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
 * Command that does nothing.
 * </p>
 *
 * @author pham.pphi
 **/
public class NoopCommand implements Command
{
    static final CommandFactory FACTORY = new NoopCommandFactory();

    public NoopCommand()
    {
    }

    @Override
    public CommandData getData()
    {
        return null;
    }

    static class NoopCommandFactory extends AbstractCommandFactory
    {
        @Override
        public boolean isApplied(final String data)
        {
            return true;
        }

        @Override
        public NoopCommand createInstance(final String data)
        {
            return new NoopCommand();
        }

        @Override
        protected String prefix()
        {
            return null;
        }

        @Override
        protected int expectedSize()
        {
            // TODO Auto-generated method stub
            return 0;
        }
    }
}
