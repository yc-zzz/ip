package johnchatbot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the user-facing input/output.
 * Displays error messages, task list, search result and reads user commands.
 */
public class Ui {
    private Scanner scanner;
    /**
     * Initiate UI instance and input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm John ChatBot");
        System.out.println("What can I do for you, friend?");
    }
    /**
     * Shows a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon, chief!");
    }
    /**
     * Shows an error message.
     */
    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }
    /**
     * Shows a general message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
    /**
     * Display all tasks recorded, starting from number 1.
     * @param tasks the task list to display
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list, bud.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    /**
     * Shows the task found from a search by matching keywords.
     * @param tasks the task list for searching
     * @param keyword the keyword to search the tasks
     */
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

    /**
     * Read the command entered by the user.
     * @return the command string at the start.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Close the input scanner.
     */
    public void close() {
        scanner.close();
    }
}