package johnchatbot;

/**
 * Stores deadline by taking in a description and date
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Intialise a deadline.
     * @param description the description of the task
     * @param by the date or time the task should be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the user input of deadline to string.
     * @return the user input with / replaced by "by: ".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
