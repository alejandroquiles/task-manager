package app;
import model.Task;
import service.TaskManager;

public class Main {

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		taskManager.addTask("Estudiar Git");
		taskManager.addTask("Practicar Java");
		
		for (Task task : taskManager.getTasks()) {
		    System.out.println(task.getTitle());
		}

	}

}
