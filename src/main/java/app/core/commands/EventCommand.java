package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;
import app.util.Date;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidDateTimeFormatException;
import app.exceptions.InvalidUsageException;

final class EventCommand extends Command {
    private String description;
    private Date when;

    EventCommand(String args) throws InvalidUsageException, InvalidDateTimeFormatException {
        String[] splitArgs = args.split("/at");
        if (splitArgs.length != 2) {
            throw new InvalidUsageException("Usage: event <description> /at <when>");
        }

        this.description = splitArgs[0].trim();
        this.when = Date.fromFormat(splitArgs[1].trim(), Date.DEFAULT_INPUT_FORMAT);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) throws StorageFileException {
        String output = taskManager.addEventTask(this.description, this.when);
        userInterface.render(output);
    }
}