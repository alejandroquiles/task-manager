package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Task;
import service.TaskManager;

public class TaskManagerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Color BACKGROUND_COLOR = new Color(18, 18, 22);
	private static final Color CARD_COLOR = new Color(30, 30, 36);
	private static final Color INPUT_COLOR = new Color(42, 42, 50);
	private static final Color BORDER_COLOR = new Color(65, 65, 75);
	private static final Color TEXT_COLOR = new Color(240, 240, 245);
	private static final Color MUTED_TEXT_COLOR = new Color(160, 160, 170);
	private static final Color ACCENT_COLOR = new Color(88, 101, 242);
	private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
	private static final Color ERROR_COLOR = new Color(248, 113, 113);

	private JTextField taskInput;
	private JButton addButton;
	private JButton completeButton;

	private JLabel counterLabel;
	private JLabel feedbackLabel;

	private TaskManager taskManager;
	private DefaultListModel<String> taskListModel;
	private JList<String> taskList;

	public TaskManagerFrame() {
		this.taskManager = new TaskManager();

		this.setTitle("Task Manager");
		this.setSize(760, 520);
		this.setMinimumSize(new Dimension(680, 460));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.initComponents();
	}

	private void initComponents() {
		JPanel mainPanel = new JPanel(new BorderLayout(0, 18));
		mainPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.setBorder(new EmptyBorder(26, 28, 26, 28));

		this.setContentPane(mainPanel);

		JPanel headerPanel = createHeaderPanel();
		JPanel listCardPanel = createListCardPanel();
		JPanel bottomPanel = createBottomPanel();

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(listCardPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	}

	private JPanel createHeaderPanel() {
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(BACKGROUND_COLOR);

		JLabel titleLabel = new JLabel("Task Manager", SwingConstants.LEFT);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		titleLabel.setForeground(TEXT_COLOR);

		JLabel subtitleLabel = new JLabel("Organiza tus tareas y marca tu progreso.");
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		subtitleLabel.setForeground(MUTED_TEXT_COLOR);
		subtitleLabel.setBorder(new EmptyBorder(6, 2, 0, 0));

		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBackground(BACKGROUND_COLOR);
		textPanel.add(titleLabel, BorderLayout.NORTH);
		textPanel.add(subtitleLabel, BorderLayout.CENTER);

		this.counterLabel = new JLabel("No hay tareas todavía", SwingConstants.RIGHT);
		this.counterLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.counterLabel.setForeground(MUTED_TEXT_COLOR);

		headerPanel.add(textPanel, BorderLayout.CENTER);
		headerPanel.add(this.counterLabel, BorderLayout.EAST);

		return headerPanel;
	}

	private JPanel createListCardPanel() {
		JPanel cardPanel = new JPanel(new BorderLayout(0, 12));
		cardPanel.setBackground(CARD_COLOR);
		cardPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(18, 18, 18, 18)
		));

		JLabel listTitleLabel = new JLabel("Mis tareas");
		listTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		listTitleLabel.setForeground(TEXT_COLOR);

		this.taskListModel = new DefaultListModel<>();
		this.taskList = new JList<>(this.taskListModel);
		this.taskList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		this.taskList.setFixedCellHeight(42);
		this.taskList.setBackground(CARD_COLOR);
		this.taskList.setForeground(TEXT_COLOR);
		this.taskList.setSelectionBackground(new Color(55, 65, 95));
		this.taskList.setSelectionForeground(Color.WHITE);
		this.taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.taskList.setBorder(new EmptyBorder(8, 4, 8, 4));

		JScrollPane scrollPane = new JScrollPane(this.taskList);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(45, 45, 54)));
		scrollPane.getViewport().setBackground(CARD_COLOR);

		cardPanel.add(listTitleLabel, BorderLayout.NORTH);
		cardPanel.add(scrollPane, BorderLayout.CENTER);

		return cardPanel;
	}

	private JPanel createBottomPanel() {
		JPanel bottomPanel = new JPanel(new BorderLayout(0, 12));
		bottomPanel.setBackground(BACKGROUND_COLOR);

		JPanel inputPanel = new JPanel(new BorderLayout(12, 0));
		inputPanel.setBackground(BACKGROUND_COLOR);

		this.taskInput = new JTextField();
		this.taskInput.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		this.taskInput.setForeground(TEXT_COLOR);
		this.taskInput.setBackground(INPUT_COLOR);
		this.taskInput.setCaretColor(TEXT_COLOR);
		this.taskInput.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR),
				new EmptyBorder(0, 12, 0, 12)
		));
		this.taskInput.setPreferredSize(new Dimension(420, 40));

		this.addButton = createButton("Añadir tarea", ACCENT_COLOR);
		this.completeButton = createButton("Marcar como completada", SUCCESS_COLOR);

		this.feedbackLabel = new JLabel(" ");
		this.feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.feedbackLabel.setForeground(MUTED_TEXT_COLOR);
		this.feedbackLabel.setBorder(new EmptyBorder(4, 2, 0, 0));

		this.addButton.addActionListener(e -> addTaskFromInput());
		this.completeButton.addActionListener(e -> completeSelectedTask());

		inputPanel.add(this.taskInput, BorderLayout.CENTER);
		inputPanel.add(this.addButton, BorderLayout.EAST);

		bottomPanel.add(inputPanel, BorderLayout.NORTH);
		bottomPanel.add(this.completeButton, BorderLayout.CENTER);
		bottomPanel.add(this.feedbackLabel, BorderLayout.SOUTH);

		return bottomPanel;
	}

	private JButton createButton(String text, Color backgroundColor) {
		JButton button = new JButton(text);

		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(backgroundColor);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setPreferredSize(new Dimension(210, 40));

		return button;
	}

	private void addTaskFromInput() {
		String title = this.taskInput.getText();

		if (title.trim().isEmpty()) {
			showFeedback("El título no puede estar vacío.", ERROR_COLOR);
			this.taskInput.requestFocus();
			return;
		}

		this.taskManager.addTask(title.trim());
		this.refreshTaskList();

		this.taskInput.setText("");
		this.taskInput.requestFocus();

		showFeedback("Tarea añadida correctamente.", SUCCESS_COLOR);
	}

	private void completeSelectedTask() {
		int selectedIndex = this.taskList.getSelectedIndex();

		if (selectedIndex == -1) {
			showFeedback("Selecciona una tarea primero.", ERROR_COLOR);
			return;
		}

		boolean completed = this.taskManager.completeTask(selectedIndex);

		if (completed) {
			this.refreshTaskList();
			this.taskList.setSelectedIndex(selectedIndex);
			showFeedback("Tarea marcada como completada.", SUCCESS_COLOR);
		} else {
			showFeedback("No se pudo completar la tarea.", ERROR_COLOR);
		}
	}

	private void refreshTaskList() {
		this.taskListModel.clear();

		int completedTasks = 0;

		for (Task task : this.taskManager.getTasks()) {
			if (task.isCompleted()) {
				completedTasks++;
			}

			this.taskListModel.addElement(formatTask(task));
		}

		updateCounter(completedTasks);
	}

	private String formatTask(Task task) {
		String status;
		String statusColor;

		if (task.isCompleted()) {
			status = "Completada";
			statusColor = "#22C55E";
		} else {
			status = "Pendiente";
			statusColor = "#FACC15";
		}

		return "<html>"
				+ "<span style='color:" + statusColor + "; font-weight:bold;'>"
				+ "[" + status + "]"
				+ "</span>"
				+ "<span style='color:#F0F0F5;'> "
				+ escapeHtml(task.getTitle())
				+ "</span>"
				+ "</html>";
	}

	private void updateCounter(int completedTasks) {
		int totalTasks = this.taskManager.getTasks().size();

		if (totalTasks == 0) {
			this.counterLabel.setText("No hay tareas todavía");
			return;
		}

		this.counterLabel.setText(totalTasks + " tareas · " + completedTasks + " completadas");
	}

	private void showFeedback(String message, Color color) {
		this.feedbackLabel.setText(message);
		this.feedbackLabel.setForeground(color);
	}

	private String escapeHtml(String text) {
		return text
				.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;");
	}
}