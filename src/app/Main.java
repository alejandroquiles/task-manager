package app;

import java.util.Scanner;

import model.Task;
import service.TaskManager;



public class Main {
		
	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while(running){
			
				System.out.println("==== TASK MANAGER ====");
				System.out.println();
				System.out.println("1. Añadir tarea");
				System.out.println("2. Listar tareas");
				System.out.println("3. Marcar tarea como completada");
				System.out.println("4. Salir");
				
				System.out.println();
				System.out.print("Elige una opción: ");

				String option = scanner.nextLine();
				
				switch (option) {
				
				case "1":
					System.out.print("Introduce el título de la tarea: ");
					String title = scanner.nextLine();
					taskManager.addTask(title);
					System.out.println("Tarea añadida correctamente.");
					break;
					
				case "2":
					printTasks(taskManager);
					break;
					
				case "3":
					if (taskManager.getTasks().isEmpty()) {
						System.out.println("No hay tareas para completar.");
					} else {
						printTasks(taskManager);

						System.out.print("Introduce el número de la tarea a completar: ");

						try {
							int taskNumber = Integer.parseInt(scanner.nextLine());
							int index = taskNumber - 1;

							boolean completed = taskManager.completeTask(index);

							if (completed) {
								System.out.println("Tarea completada correctamente.");
							} else {
								System.out.println("Número de tarea no válido.");
							}

						} catch (NumberFormatException e) {
							System.out.println("Número no válido. Debes introducir un número.");
						}
					}
					break;
					
				case "4":
					running = false;
					System.out.println("Saliendo del programa...");
					break;
					
				default:
					System.out.println("Opción no válida");
					break;
				}
				
			}
		scanner.close();
		}
		
	private static void printTasks(TaskManager taskManager) {
		if (taskManager.getTasks().isEmpty()) {
			System.out.println("No hay tareas todavía.");
		} else {
			System.out.println("==== TAREAS ====");
			System.out.println();

			for (int i = 0; i < taskManager.getTasks().size(); i++) {
				Task task = taskManager.getTasks().get(i);

				String status;

				if (task.isCompleted()) {
					status = "Completada";
				} else {
					status = "Pendiente";
				}

				System.out.println((i + 1) + ". [" + status + "] " + task.getTitle());
			}
		}
	}
		
	}



