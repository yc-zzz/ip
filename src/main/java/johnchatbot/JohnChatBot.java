package johnchatbot;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class JohnChatBot {
    private static final String FILE_PATH = "data/johnchatbot.txt";

    public static void main(String[] args) {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you, friend?");

        Scanner myObj = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[100];
        int taskCount = 0;

        try {
            taskCount = loadTasks(tasks);
        } catch (FileNotFoundException e) {
            // handle the case where the data file doesn't exist at the start
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
        }

        while (true) {
            line = myObj.nextLine();
            if (line.equals("bye")) {
                break;
            }
            try {
                if (handleCommand(line, tasks, taskCount)) {
                    taskCount++;
                }
                saveTasks(tasks, taskCount);
            } catch (JohnChatBotException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR: Invalid task or format.");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
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
            System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);
            return false;
        } else if (line.startsWith("unmark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, taskCount);
            tasks[index].unmark();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);
            return false;
        } else if (line.startsWith("todo ")) {
            String description = line.substring(5).trim();
            if (description.isEmpty()) {
                throw new JohnChatBotException("The description of a todo cannot be empty, mate.");
            }
            tasks[taskCount] = new Todo(description);
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount]);
            return true;
        } else if (line.startsWith("deadline ")) {
            if (!line.contains(" /by ")) {
                throw new JohnChatBotException("Deadlines need a /by date, fam.");
            }
            String[] parts = line.split(" /by ");
            tasks[taskCount] = new Deadline(parts[0].substring(9), parts[1]);
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount]);
            return true;
        } else if (line.startsWith("event ")) {
            if (!line.contains(" /from ") || !line.contains(" /to ")) {
                throw new JohnChatBotException("Events need /from and /to timings, buddy.");
            }
            String[] parts = line.split(" /from | /to ");
            tasks[taskCount] = new Event(parts[0].substring(6), parts[1], parts[2]);
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount]);
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

    private static void saveTasks(Task[] tasks, int taskCount) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            String type = (t instanceof Todo) ? "T" : (t instanceof Deadline) ? "D" : "E";
            String isDone = t.isDone ? "1" : "0";
            String line = type + " | " + isDone + " | " + t.description;
            if (t instanceof Deadline) {
                line += " | " + ((Deadline) t).by;
            } else if (t instanceof Event) {
                line += " | " + ((Event) t).from + " | " + ((Event) t).to;
            }
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

    private static int loadTasks(Task[] tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        int count = 0;
        while (s.hasNext()) {
            String[] p = s.nextLine().split(" \\| ");
            if (p[0].equals("T")) {
                tasks[count] = new Todo(p[2]);
            } else if (p[0].equals("D")) {
                tasks[count] = new Deadline(p[2], p[3]);
            } else if (p[0].equals("E")) {
                tasks[count] = new Event(p[2], p[3], p[4]);
            }
            if (p[1].equals("1")) {
                tasks[count].mark();
            }
            count++;
        }
        s.close();
        return count;
    }
}