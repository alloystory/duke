package app.core.commands;

import app.core.Messages;
import app.core.tasks.Task;
import app.core.tasks.TaskManager;
import app.util.Date;
import app.util.Pair;
import app.exceptions.StorageFileException;
import app.exceptions.DuplicatedTaskException;
import app.exceptions.InvalidDateTimeFormatException;
import app.exceptions.InvalidUsageException;

final class DeadlineCommand extends Command {
    private String description;
    private Date deadline;

    DeadlineCommand(String args) throws InvalidUsageException, InvalidDateTimeFormatException {
        String[] splitArgs = args.split("/by");
        if (splitArgs.length != 2) {
            throw new InvalidUsageException(Messages.DEADLINE_WRONG_FORMAT_MESSAGE);
        }

        assert splitArgs.length == 2 : "There should only be 2 arguments in the split arguments";
        
        this.description = splitArgs[0].trim();
        this.deadline = Date.fromFormat(splitArgs[1].trim(), Date.DEFAULT_INPUT_FORMAT);
    }

    @Override
    public Pair execute(TaskManager taskManager) throws StorageFileException, DuplicatedTaskException {
        Task task = taskManager.addDeadlineTask(this.description, this.deadline);
        String output = String.format(Messages.ADD_TASK_SUCCESS_MESSAGE, task, taskManager.getSize());
        return new Pair(output, false);
    }
}