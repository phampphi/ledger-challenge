/*
 * FILENAME
 *     MarketPlaceApp.java
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

package com.ledger.marketplace;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.ledger.marketplace.command.BalanceCommand;
import com.ledger.marketplace.command.Command;
import com.ledger.marketplace.command.CommandParser;
import com.ledger.marketplace.command.LoanCommand;
import com.ledger.marketplace.command.NoopCommand;
import com.ledger.marketplace.command.PaymentCommand;
import com.ledger.marketplace.handler.BalanceHandler;
import com.ledger.marketplace.handler.ConsoleOutputBalanceHandler;
import com.ledger.marketplace.model.Loan;
import com.ledger.marketplace.model.LoanBalanceRequest;
import com.ledger.marketplace.model.LoanIdentifier;
import com.ledger.marketplace.model.LoanPayment;

/**
 * <p>
 * Ledger challenge application.
 * </p>
 *
 * @author pham.pphi
 **/
public class MarketPlaceApp
{
    @SuppressWarnings("rawtypes")
    private final ImmutableList<CommandExecutor> executors = ImmutableList.of(new LoanCommandExecutor(),
        new PaymentCommandExecutor(), new BalanceCommandExecutor(), new NoopCommandExecutor());

    BalanceHandler balanceHandler = new ConsoleOutputBalanceHandler();

    private Map<LoanIdentifier, Loan> loanRegistry = new HashMap<>();

    public static void main(final String... args)
    {
        new MarketPlaceApp().run(Paths.get(args[0]));
    }

    public void run(final Path inputFile)
    {
        checkNotNull(inputFile, "inputFile does not exist"); //$NON-NLS-1$
        try (BufferedReader reader = Files.newBufferedReader(inputFile))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                perform(CommandParser.parse(line));
            }
        }
        catch (IOException e)
        {
            System.out.println(String.format("Error during executing: %s", e.getMessage())); //$NON-NLS-1$
        }
    }

    public void perform(final Command command)
    {
        executors.stream().filter(e -> e.accept(command)).findFirst().get().execute(command);
    }

    interface CommandExecutor<T extends Command>
    {
        boolean accept(Command command);

        void execute(T command);
    }

    class LoanCommandExecutor implements CommandExecutor<LoanCommand>
    {
        @Override
        public boolean accept(final Command command)
        {
            return command instanceof LoanCommand;
        }

        @Override
        public void execute(final LoanCommand command)
        {
            Loan loan = command.getData();
            loanRegistry.computeIfAbsent(loan.getIdentifier(), k -> loan);
        }
    }

    class PaymentCommandExecutor implements CommandExecutor<PaymentCommand>
    {
        @Override
        public boolean accept(final Command command)
        {
            return command instanceof PaymentCommand;
        }

        @Override
        public void execute(final PaymentCommand command)
        {
            LoanPayment payment = command.getData();
            Loan loan = loanRegistry.get(payment.getIdentifier());
            checkState(loan != null, "loan not found"); //$NON-NLS-1$
            loan.processPayment(payment);
        }
    }

    class BalanceCommandExecutor implements CommandExecutor<BalanceCommand>
    {

        @Override
        public boolean accept(final Command command)
        {
            return command instanceof BalanceCommand;
        }

        @Override
        public void execute(final BalanceCommand command)
        {
            LoanBalanceRequest request = command.getData();
            Loan loan = loanRegistry.get(request.getIdentifier());
            checkState(loan != null, "loan not found"); //$NON-NLS-1$
            balanceHandler.handle(loan.balanceAt(request.getEmi()));
        }
    }

    class NoopCommandExecutor implements CommandExecutor<NoopCommand>
    {
        @Override
        public boolean accept(final Command command)
        {
            return true;
        }

        @Override
        public void execute(final NoopCommand command)
        {
            // do nothing
        }
    }
}
