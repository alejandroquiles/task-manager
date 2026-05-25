package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TaskManagerFrame extends JFrame {

	private JTextField taskInput;
	private JButton addButton;

	public TaskManagerFrame() {
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

		inputPanel.add(this.taskInput);
		inputPanel.add(this.addButton);

		this.add(titleLabel, BorderLayout.NORTH);
		this.add(inputPanel, BorderLayout.CENTER);
	}
}