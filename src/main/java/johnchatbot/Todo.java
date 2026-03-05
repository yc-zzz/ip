package johnchatbot;

/**
 * Represents a todo that only has a description and no deadline.
 */
public class Todo extends Task {
    /**
     * Constructs a todo class with description.
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     * @return the type, status and description.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
