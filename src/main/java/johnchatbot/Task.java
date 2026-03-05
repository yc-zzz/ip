package johnchatbot;

/**
 * A generic task for the types of deadline, todo and event to extend on.
 * Has a completion status and description for each task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an undone task class with provided description
     * @param description the description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark a task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Get the status of the task
     * @return X if task is done and blank if the task is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task description and status.
     * @return status followed by task description
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
