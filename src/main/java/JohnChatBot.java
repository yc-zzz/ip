import java.util.Scanner;

public class JohnChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[100];
        int taskcount = 0;

        while (!line.equals("bye")) {
            line = myObj.nextLine();

            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                if (taskcount == 0) {
                    System.out.println("Nothing in list");
                } else {
                    for (int i = 0; i < taskcount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
            } else if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
            } else if (line.startsWith("todo ")) {
                String description = line.substring(5);
                tasks[taskcount] = new Todo(description);
                taskcount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskcount - 1]);

            } else if (line.startsWith("deadline ")) {
                String[] parts = line.split(" /by ");
                String description = parts[0].substring(9);
                String by = parts[1];

                tasks[taskcount] = new Deadline(description, by);
                taskcount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskcount - 1]);

            } else if (line.startsWith("event ")) {
                String[] parts = line.split(" /from | /to ");
                String description = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];

                tasks[taskcount] = new Event(description, from, to);
                taskcount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskcount - 1]);

            } else {
                System.out.println("Whatchu talking fam?");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        myObj.close();
    }
}
