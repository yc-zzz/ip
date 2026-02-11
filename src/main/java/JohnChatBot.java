import java.util.Scanner;

public class JohnChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you, friend?");

        Scanner myObj = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            line = myObj.nextLine();
            if (line.equals("bye")) {
                break;
            }
            try {
                if (handleCommand(line, tasks, taskCount)) {
                    taskCount++;
                }
            } catch (JohnChatBotException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR: Invalid task or format.");
            }
        }

        System.out.println("Bye. Hope to see you again soon, chief!");
        myObj.close();
    }

    private static boolean handleCommand(String line, Task[] tasks, int taskCount) throws JohnChatBotException {
        if (line.equals("list")) {
            if (taskCount == 0) {
                System.out.println("Nothing in list");
            } else {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            return false;
        } else if (line.startsWith("mark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, taskCount);
            tasks[index].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index]);
            return false;
        } else if (line.startsWith("unmark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, taskCount);
            tasks[index].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[index]);
            return false;
        } else if (line.startsWith("todo ")) {
            String description = line.substring(5);
            if (description.isEmpty()) {
                throw new JohnChatBotException("The description of a todo cannot be empty, mate.");
            }
            tasks[taskCount] = new Todo(description);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount]);
            return true;

        } else if (line.startsWith("deadline ")) {
            if (!line.contains(" /by ")) {
                throw new JohnChatBotException("Deadlines need a /by date, fam.");
            }
            String[] parts = line.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            tasks[taskCount] = new Deadline(description, by);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount]);
            return true;

        } else if (line.startsWith("event ")) {
            if (!line.contains(" /from ") || !line.contains(" /to ")) {
                throw new JohnChatBotException("Events need /from and /to timings, buddy.");
            }
            String[] parts = line.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];

            tasks[taskCount] = new Event(description, from, to);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount]);
            return true;

        } else {
            System.out.println("I have no idea what you are talking about, pal.");
            return false;
        }
    }

    private static void validateIndex(int index, int taskCount) throws JohnChatBotException {
        if (index < 0 || index >= taskCount) {
            throw new JohnChatBotException("That task index doesn't exist, cowboy.");
        }
    }
}