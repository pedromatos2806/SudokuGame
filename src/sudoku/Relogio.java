package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Relogio extends JLabel {

	private static final long serialVersionUID = 1L;
	private static String time;
	private static Relogio instance;
	private static int counter;

	public static int getCounter() {
		return counter;
	}

	public static String getTime() {
		return time;
	}

	public void setTime(String time) {
		super.setText(time);
	}

	private Relogio() {
		super("00:00:00");
		counter = 0;
	}

	public static Relogio getInstance() {
		if (instance == null) {
			instance = new Relogio();
		}
		return instance;
	}

	private Timer t = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			counter++;
			int hours = counter / 3600;
			int minutes = (counter % 3600) / 60;
			int seconds = counter % 60;
			time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			setTime(time);
		}
	});

	public void startTimer() {
		t.start();
	}

	public void stopTimer() {
		t.stop();
		setTime("00:00:00");
		counter = 0;
	}
}