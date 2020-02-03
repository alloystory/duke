package app.core.commands;

import app.core.tasks.TaskManager;
import app.exceptions.BaseException;
import app.core.UserInterface;

/**
 * An abstract class that defines abstract methods
 * to be implemented in child Command classes
 */
public abstract class Command {
    /**
     * Abstract execution method that runs the command on the
     * task manager and user interface
     * @param taskManager The desired task manager
     * @param userInterface The desired user interface
     * @throws BaseException If any error occurs during the execution of the command.
     */
    public abstract void execute(TaskManager taskManager, UserInterface userInterface) throws BaseException;
}