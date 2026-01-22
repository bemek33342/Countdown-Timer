package CTimer2026;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownLogic {

    private int totalSeconds;
    private Timer timer;

    public CountdownLogic(Runnable onTick, Runnable onFinish) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalSeconds > 0) {
                    totalSeconds--;
                    onTick.run();
                } else {
                    timer.stop();
                    onFinish.run();
                }
            }
        });
    }

    public void start(int hours, int minutes, int seconds) {
        totalSeconds = hours * 3600 + minutes * 60 + seconds;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        stop();
        totalSeconds = 0;
    }

    public String getFormattedTime() {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
