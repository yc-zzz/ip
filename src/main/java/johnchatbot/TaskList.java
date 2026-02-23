package johnchatbot;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t){
        tasks.add(t);
    }

    public Task remove(int index){
        return tasks.remove(index);
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public int size(){
        return tasks.size();
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAll(){
        return tasks;
    }
}
