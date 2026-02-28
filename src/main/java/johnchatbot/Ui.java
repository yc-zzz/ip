package johnchatbot;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you, friend?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon, chief!");
    }

    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list, bud.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void showFoundTasks(TaskList tasks, String keyword){
        ArrayList<Task> matches = new ArrayList<>();
        for(int i = 0;i < tasks.size(); i++){
            if (tasks.get(i).description.contains(keyword)){
                matches.add(tasks.get(i));
            }
        }
        if (matches.isEmpty()){
            System.out.println("No matching task, amigo.");
        } else {
            System.out.println("Here are the matching tasks: ");
            for (int i = 0; i < matches.size(); i++){
                System.out.println((i+1) + ". " + matches.get(i));
            }
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}