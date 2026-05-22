package service;
import java.util.ArrayList;
import model.Task;

public class TaskManager {
	private ArrayList<Task> tasks;
	public TaskManager () {
		this.tasks = new ArrayList<>();
	}
	public void addTask(String title) {
		Task task = new Task(title);
		this.tasks.add(task);
	}
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	public boolean completeTask(int index) {
		if (index < 0 || index >= this.tasks.size()) {
			return false;
		}

		Task task = this.tasks.get(index);
		task.markAsCompleted();
		return true;
	}
}
