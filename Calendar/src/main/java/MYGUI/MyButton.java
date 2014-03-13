package MYGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MyButton extends JButton {

        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;

        public MyButton() {
            this(null);
            
        }

        public MyButton(String text) {
        	
            super(text);
            super.setContentAreaFilled(false);
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            this.setBorderPainted(false);
            this.setFocusPainted(false);
            this.setHoverBackgroundColor(ConfigGUICLient._bHBC);
            this.setPressedBackgroundColor(ConfigGUICLient._bPBC);
            this.setForeground(ConfigGUICLient._bFG);
            this.setBackground(ConfigGUICLient._bBG);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    }