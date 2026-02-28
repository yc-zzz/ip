package johnchatbot;

public class Parser {

    public static void parse(String line, TaskList tasks, Ui ui) throws JohnChatBotException {
        if (line.equals("list")) {
            ui.showTaskList(tasks);
        } else if (line.startsWith("mark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            tasks.get(index).mark();
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
        } else if (line.startsWith("unmark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            tasks.get(index).unmark();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
        } else if (line.startsWith("todo ")) {
            String description = line.substring(5).trim();
            if (description.isEmpty())
                throw new JohnChatBotException("The description of a todo cannot be empty, mate.");
            tasks.add(new Todo(description));
            ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("deadline ")) {
            if (!line.contains(" /by ")) throw new JohnChatBotException("Deadlines need a /by date, fam.");
            String[] parts = line.split(" /by ");
            tasks.add(new Deadline(parts[0].substring(9), parts[1]));
            ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("event ")) {
            if (!line.contains(" /from ") || !line.contains(" /to "))
                throw new JohnChatBotException("Events need /from and /to timings, buddy.");
            String[] parts = line.split(" /from | /to ");
            tasks.add(new Event(parts[0].substring(6), parts[1], parts[2]));
            ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("delete ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            Task removed = tasks.remove(index);
            ui.showMessage("Noted. I've removed this task:\n  " + removed);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } else if(line.startsWith("find ")) {
            String keyword = line.substring(5).trim();
            if (keyword.isEmpty()) throw new JohnChatBotException("Please provide a keyword to search for, fella.");
            ui.showFoundTasks(tasks, keyword);
        } else {
            ui.showMessage("I have no idea what you are talking about, pal.");
        }
    }

    private static void validateIndex(int index, int size) throws JohnChatBotException {
        if (index < 0 || index >= size) throw new JohnChatBotException("That task index doesn't exist, cowboy.");
    }
}