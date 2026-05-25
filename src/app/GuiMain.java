package app;

import javax.swing.SwingUtilities;

import ui.TaskManagerFrame;

public class GuiMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TaskManagerFrame frame = new TaskManagerFrame();
			frame.setVisible(true);
		});
	}

}
