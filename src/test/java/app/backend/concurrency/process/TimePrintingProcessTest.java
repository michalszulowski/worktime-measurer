package app.backend.concurrency.process;

import app.ui.terminal.input.ConsoleInputFetcher;
import app.ui.terminal.input.ContinuousFetcher;
import app.ui.terminal.input.InputObserver;
import command.Command;
import command.factory.CommandFactory;
import command.factory.DictionaryCommandFactory;
import command.factory.NoSuchCommandException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class allowing to manually test controlling ConcurrentProcess. It fetches commands from terminal and opens
 * new terminal with process output. Supports only Linux.
 */
class TimePrintingProcessTest {

    //TODO redo Fetcher to process
    public static void main(String[] args) {
        ConcurrentProcess timePrinter = new TimePrintingProcess();
        CommandFactory commandFactory = buildCommandFactory(timePrinter);
        timePrinter.start();
        ContinuousFetcher inputFetcher = new ConsoleInputFetcher();
        InputObserver inputObserver = new CommandOnInputObserver(commandFactory);
        inputFetcher.addObserver(inputObserver);
        inputFetcher.run();
    }

    private static class CommandOnInputObserver implements InputObserver {
        private final CommandFactory commandFactory;

        private CommandOnInputObserver(CommandFactory commandFactory) {
            this.commandFactory = commandFactory;
        }

        @Override
        public void typed(String text) {
            try {
                Command command = commandFactory.getCommand(text);
                command.invoke();
            } catch (NoSuchCommandException ex) {
                System.out.println("Wrong command: " + ex.getMessage());
            }
        }
    }

    private static CommandFactory buildCommandFactory(ConcurrentProcess process) {
        var commandFactory = new DictionaryCommandFactory();
        commandFactory.addCommand("pause", args -> (() -> process.getExecutionController().pause()));
        commandFactory.addCommand("unpause", args -> (() -> process.getExecutionController().unpause()));
        commandFactory.addCommand("kill", args -> (() -> process.getExecutionController().kill()));
        return commandFactory;
    }
}