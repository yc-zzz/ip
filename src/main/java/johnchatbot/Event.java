package johnchatbot;

/**
 * Represents an event class with description, starting and ending time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an event class, taking in the user's description, start and end time
     * @param description description of the event
     * @param from starting time or date of the event
     * @param to ending time or date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event.
     * @return the status, the description and the starting/ending time
     */
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }
}
