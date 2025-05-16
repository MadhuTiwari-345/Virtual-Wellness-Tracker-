package WellnessApp.Utils;

import javax.swing.*;

import WellnessApp.basepanel;

import java.awt.*;
import java.awt.event.ActionListener;

public class TransitionUtils {
    public static void fadeTransition(JPanel panel, ActionListener onComplete) {
        Timer timer = new Timer(20, e -> {
            onComplete.actionPerformed(e);
            ((Timer)e.getSource()).stop();
        });
        timer.start();
    }
    
    public static void bounceEffect(JComponent component) {
        Timer timer = new Timer(20, null);
        final int[] y = {component.getY()};
        final int offset = 5;
        timer.addActionListener(e -> {
            if (timer.getDelay() < 40) {
                timer.stop();
                component.setLocation(component.getX(), y[0]);
                return;
            }
            component.setLocation(component.getX(), y[0] + offset * (timer.getDelay() % 20 < 10 ? -1 : 1));
            timer.setDelay(timer.getDelay() + 10);
        });
        timer.start();
    }
    
    public static void pulseEffect(JComponent component) {
        Timer timer = new Timer(20, null);
        final float[] scale = {1.0f};
        timer.addActionListener(e -> {
            if (timer.getDelay() < 40) {
                component.setSize(component.getPreferredSize());
                timer.stop();
                return;
            }
            scale[0] = 1.0f + 0.1f * (timer.getDelay() % 20 < 10 ? 1 : -1);
            component.setSize(
                (int)(component.getPreferredSize().width * scale[0]),
                (int)(component.getPreferredSize().height * scale[0])
            );
            timer.setDelay(timer.getDelay() + 10);
        });
        timer.start();
    }
    
    public static void shakeComponent(JComponent component) {
        Timer timer = new Timer(10, null);
        final int[] x = {component.getX()};
        final int offset = 5;
        timer.addActionListener(e -> {
            if (timer.getDelay() < 40) {
                timer.stop();
                component.setLocation(x[0], component.getY());
                return;
            }
            component.setLocation(x[0] + offset * (timer.getDelay() % 20 < 10 ? 1 : -1), component.getY());
            timer.setDelay(timer.getDelay() + 10);
        });
        timer.start();
    }

	public static void fadeTransition(basepanel panel, Object onComplete) {
		// TODO Auto-generated method stub
		
	}
}