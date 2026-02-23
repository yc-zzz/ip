package johnchatbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return tasks;
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
                if (p[1].equals("1")) t.mark();
                tasks.add(t);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, starting fresh.");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String type = (t instanceof Todo) ? "T" : (t instanceof Deadline) ? "D" : "E";
            String line = type + " | " + (t.isDone ? "1" : "0") + " | " + t.description;
            if (t instanceof Deadline) line += " | " + ((Deadline) t).by;
            else if (t instanceof Event) line += " | " + ((Event) t).from + " | " + ((Event) t).to;
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }
}