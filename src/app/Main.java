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
				System.out.println("3. Salir");
				System.out.println();
				System.out.print("Elige una opción: ");

				String option = scanner.nextLine();
				switch (option) {
				case "1":
					System.out.println("Has elegido añadir tarea");
					break;
				case "2":
					System.out.println("Has elegido listar tareas");
					break;
				case "3":
					running = false;
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida");
					break;
				}
				
			}
			
		}
		
		}



