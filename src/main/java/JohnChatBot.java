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
            }
            else if (line.equals("list")) {
                if (taskcount == 0) {
                    System.out.println("Nothing in list");
                } else {
                    for (int i = 0; i < taskcount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            }
            else if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
            }
            else if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
            }
            else {
                tasks[taskcount] = new Task(line);
                taskcount++;
                System.out.println("added: " + line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        myObj.close();
    }
}
