package CTimer2026;

import javax.swing.JFrame;

public class TimerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public TimerFrame() {
        setTitle("Countdown Timer");
        setSize(460, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new TimerPanel());

        setVisible(true);
    }
}
