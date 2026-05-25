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
import javax.swing.JOptionPane;
import model.Task;

import service.TaskManager;

public class TaskManagerFrame extends JFrame {

	private JTextField taskInput;
	private JButton addButton;
	private JButton completeButton;
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

		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel();

		this.taskInput = new JTextField(20);
		this.addButton = new JButton("Añadir tarea");
		this.completeButton = new JButton("Marcar como completada");

		this.taskListModel = new DefaultListModel<>();
		this.taskList = new JList<>(this.taskListModel);
		JScrollPane scrollPane = new JScrollPane(this.taskList);

		this.addButton.addActionListener(e -> {
			String title = this.taskInput.getText();

			if (title.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
				return;
			}

			this.taskManager.addTask(title);
			this.taskListModel.addElement("[Pendiente] " + title);
			this.taskInput.setText("");
			this.taskInput.requestFocus();
		});
		this.completeButton.addActionListener(e -> {
			int selectedIndex = this.taskList.getSelectedIndex();

			if (selectedIndex == -1) {
				JOptionPane.showMessageDialog(this, "Selecciona una tarea primero.");
				return;
			}

			boolean completed = this.taskManager.completeTask(selectedIndex);

			if (completed) {
				Task task = this.taskManager.getTasks().get(selectedIndex);
				this.taskListModel.setElementAt("[Completada] " + task.getTitle(), selectedIndex);
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo completar la tarea.");
			}
		});
		
		inputPanel.add(this.taskInput);
		inputPanel.add(this.addButton);

		bottomPanel.add(inputPanel, BorderLayout.NORTH);
		bottomPanel.add(this.completeButton, BorderLayout.SOUTH);

		this.add(titleLabel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
}