package WellnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitionUtils {
    public static void fadeIn(JComponent component) {
        component.setVisible(true);
        new Timer(20, new ActionListener() {
            private float opacity = 0f;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1f) {
                    opacity += 0.05f;
                    component.setBackground(new Color(0, 0, 0, opacity));
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }
    
    public static void fadeOut(JComponent component, Runnable afterAction) {
        new Timer(20, new ActionListener() {
            private float opacity = 1f;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity > 0f) {
                    opacity -= 0.05f;
                    component.setBackground(new Color(0, 0, 0, opacity));
                } else {
                    ((Timer) e.getSource()).stop();
                    component.setVisible(false);
                    if (afterAction != null) {
                        afterAction.run();
                    }
                }
            }
        }).start();
    }
    
    public static void slideIn(JComponent component, int direction) {
        // Here Implementation for slide animation
        // direction: SwingConstants.NORTH, SOUTH, EAST, WEST
    }
    
    public static void slideOut(JComponent component, int direction, Runnable afterAction) {
        // Here Implementation for slide animation
    }

	public static void fadeTransition11(JPanel mainPanel, Object object) {
		// TODO Auto-generated method stub
		
	}

	public static void fadeTransition1(JPanel mainPanel, Object object) {
		// TODO Auto-generated method stub
		
	}

	public static void fadeTransition(JPanel mainPanel, Object object) {
		// TODO Auto-generated method stub
		
	}

	public static void bounceEffect(JButton moodButton) {
		// TODO Auto-generated method stub
		
	}

	public static void bounceEffect(JLabel glassIcon) {
		// TODO Auto-generated method stub
		
	}

	public static void pulseEffect(JButton moodButton) {
		// TODO Auto-generated method stub
		
	}

	public static void shakeComponent(JButton loginButton) {
		// TODO Auto-generated method stub
		
	}
}