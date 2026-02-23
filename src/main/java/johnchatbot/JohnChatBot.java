package johnchatbot;

import java.io.IOException;

public class JohnChatBot {
    private static final String FILE_PATH = "data/johnchatbot.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public JohnChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            String line = ui.readCommand();
            if (line.equals("bye")) break;
            try {
                Parser.parse(line, tasks, ui);
                storage.save(tasks);
            } catch (JohnChatBotException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showError("Invalid task or format.");
            } catch (IOException e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
        }
        ui.showGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new JohnChatBot(FILE_PATH).run();
    }
}