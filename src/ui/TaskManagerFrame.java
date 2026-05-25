package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import service.TaskManager;

public class TaskManagerFrame extends JFrame {

	private JTextField taskInput;
	private JButton addButton;
	private TaskManager taskManager;
	private DefaultListModel<String> taskListModel;
	private JList<String> taskList;

	public TaskManagerFrame() {
		this.taskManager = new TaskManager();

		this.setTitle("Task Manager");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel("Task Manager", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

		JPanel inputPanel = new JPanel();

		this.taskInput = new JTextField(20);
		this.addButton = new JButton("Añadir tarea");

		this.taskListModel = new DefaultListModel<>();
		this.taskList = new JList<>(this.taskListModel);
		JScrollPane scrollPane = new JScrollPane(this.taskList);

		this.addButton.addActionListener(e -> {
			String title = this.taskInput.getText();

			if (title.trim().isEmpty()) {
				System.out.println("El título no puede estar vacío.");
			} else {
				this.taskManager.addTask(title);
				this.taskListModel.addElement("[Pendiente] " + title);
				this.taskInput.setText("");
				System.out.println("Tarea añadida: " + title);
			}
		});

		inputPanel.add(this.taskInput);
		inputPanel.add(this.addButton);

		this.add(titleLabel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(inputPanel, BorderLayout.SOUTH);
	}
}