import java.util.Scanner;

public class JohnChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String line = "";

        String[] tasks = new String[100];
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
            else {
                tasks[taskcount] = line;
                taskcount++;
                System.out.println("added: " + line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        myObj.close();
    }
}
