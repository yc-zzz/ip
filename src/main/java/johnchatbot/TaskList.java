package johnchatbot;
import java.util.ArrayList;

/**
 * A list of current tasks in the application.
 * Wraps the tasks as an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructs the task list with the current task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the tasklist.
     * @param t
     */
    public void add(Task t){
        tasks.add(t);
    }

    /**
     * Removes a task from the tasklist.
     * @param index the task index
     * @return returns the tasklist with the specified task removed.
     */
    public Task remove(int index){
        return tasks.remove(index);
    }

    /**
     * Return the particular task the users inquired on.
     * @param index the index of the specified task
     * @return the desired task given its index
     */
    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Returns the size of the tasklist.
     * @return the size of the task list.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Checks whether there are any tasks in the task list.
     * @return the boolean value of whether the task is empty
     */
    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    /**
     * Acquire the full list of tasks.
     * @return the current tasklist.
     */
    public ArrayList<Task> getAll(){
        return tasks;
    }
}
