import java.util.Scanner;

public class JohnChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String line = "";

        while (!line.equals("bye")) {
            line = myObj.nextLine();

            if (!line.equals("bye")) {
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        myObj.close();
    }
}
