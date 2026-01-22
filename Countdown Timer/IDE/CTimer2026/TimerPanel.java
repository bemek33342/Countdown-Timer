package CTimer2026;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JLabel timeLabel;

    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;

    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private CountdownLogic countdown;

    public TimerPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        timeLabel.setForeground(new Color(40, 40, 40));
        add(timeLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        // NO LIMITS (max = null)
        hourSpinner = createSpinner(0);
        minuteSpinner = createSpinner(0);
        secondSpinner = createSpinner(0);

        inputPanel.add(createLabel("Hours"));
        inputPanel.add(hourSpinner);
        inputPanel.add(createLabel("Minutes"));
        inputPanel.add(minuteSpinner);
        inputPanel.add(createLabel("Seconds"));
        inputPanel.add(secondSpinner);

        add(inputPanel, BorderLayout.CENTER);

        countdown = new CountdownLogic(
                () -> timeLabel.setText(countdown.getFormattedTime()),
                () -> JOptionPane.showMessageDialog(this, "Time is up!")
        );

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        buttonPanel.setBackground(Color.WHITE);

        startButton = createButton("Start", new Color(33, 150, 243));
        stopButton = createButton("Stop", new Color(244, 67, 54));
        resetButton = createButton("Reset", new Color(158, 158, 158));

        startButton.addActionListener(e -> {
            int h = (int) hourSpinner.getValue();
            int m = (int) minuteSpinner.getValue();
            int s = (int) secondSpinner.getValue();

            countdown.start(h, m, s);
            timeLabel.setText(countdown.getFormattedTime());
        });

        stopButton.addActionListener(e -> countdown.stop());

        resetButton.addActionListener(e -> {
            countdown.reset();
            timeLabel.setText("00:00:00");
            hourSpinner.setValue(0);
            minuteSpinner.setValue(0);
            secondSpinner.setValue(0);
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JSpinner createSpinner(int startValue) {
        // min = 0, max = null → unlimited
        SpinnerNumberModel model =
                new SpinnerNumberModel(startValue, 0, null, 1);

        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(70, 32));
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return spinner;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(90, 90, 90));
        return label;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 38));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
}
