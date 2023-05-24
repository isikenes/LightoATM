package gui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUISettings {
    public static void setOnlyNumber(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
    }

    public static void setOnlyLetter(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(Character.isAlphabetic(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar()))) {
                    e.consume();
                }
            }
        });
    }

    private static Color originalButtonColor;

    public static void buttonHover(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                originalButtonColor = button.getBackground();
                button.setBackground(Color.darkGray);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(originalButtonColor);
            }
        });
    }


    public static void setLimit(JTextField textField, int limit) {

        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getLength() + str.length() <= limit) {
                    super.insertString(offs, str, a);
                }
            }
        });
    }

    public static boolean isFilled(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                JTextField textField = (JTextField) c;
                if (textField.getText().isEmpty())
                    return false;
            }
        }
        return true;
    }
}
