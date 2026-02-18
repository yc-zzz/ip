package johnchatbot;

import java.util.Scanner;
import java.util.ArrayList;
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

        ArrayList<Task> tasks = new ArrayList<>();
        loadTasks(tasks);


        while (true) {
            line = myObj.nextLine();
            if (line.equals("bye")) {
                break;
            }
            try {
                handleCommand(line, tasks);
                saveTasks(tasks);
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

    private static void handleCommand(String line, ArrayList<Task> tasks) throws JohnChatBotException {
        if (line.equals("list")) {
            if (tasks.isEmpty()) {
                System.out.println("Nothing in list");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        } else if (line.startsWith("mark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            tasks.get(index).mark();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
        } else if (line.startsWith("unmark ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            tasks.get(index).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
        } else if (line.startsWith("todo ")) {
            String description = line.substring(5).trim();
            if (description.isEmpty()) {
                throw new JohnChatBotException("The description of a todo cannot be empty, mate.");
            }
            tasks.add(new Todo(description));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("deadline ")) {
            if (!line.contains(" /by ")) {
                throw new JohnChatBotException("Deadlines need a /by date, fam.");
            }
            String[] parts = line.split(" /by ");
            tasks.add(new Deadline(parts[0].substring(9), parts[1]));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("event ")) {
            if (!line.contains(" /from ") || !line.contains(" /to ")) {
                throw new JohnChatBotException("Events need /from and /to timings, buddy.");
            }
            String[] parts = line.split(" /from | /to ");
            tasks.add(new Event(parts[0].substring(6), parts[1], parts[2]));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } else if (line.startsWith("delete ")) {
            int index = Integer.parseInt(line.split(" ")[1]) - 1;
            validateIndex(index, tasks.size());
            Task removed = tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removed);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("I have no idea what you are talking about, pal.");
        }
    }

    private static void validateIndex(int index, int size) throws JohnChatBotException {
        if (index < 0 || index >= size) {
            throw new JohnChatBotException("That task index doesn't exist, cowboy.");
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File f = new File(FILE_PATH);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f); // Use the file object
        for (Task t : tasks) {
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

    private static void loadTasks(ArrayList<Task> tasks) {
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                return;
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] p = s.nextLine().split(" \\| ");
                Task t;
                if (p[0].equals("T")) {
                    t = new Todo(p[2]);
                } else if (p[0].equals("D")) {
                    t = new Deadline(p[2], p[3]);
                } else {
                    t = new Event(p[2], p[3], p[4]);
                }
                if (p[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, starting with empty list, boss.");
        }
    }
}