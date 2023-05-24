package gui;

import database.DBConnection;
import database.IControlInfo;
import database.transactions.AccountInfo;
import database.transactions.UserLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements IControlInfo {

    private JPanel loginPanel;
    private JTextField accNumTF;
    private JPasswordField passwordTF;
    private JButton loginButton;
    private JButton openAccountButton;
    private UserLogin userLoginObject;

    public LoginScreen() {
        setContentPane(loginPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        loginPanel.setFocusable(true);
        GUISettings.buttonHover(loginButton);
        GUISettings.buttonHover(openAccountButton);
        GUISettings.setOnlyNumber(accNumTF);
        userLoginObject = new UserLogin();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInfoValid()) {
                    String accountNumber = accNumTF.getText().trim();
                    if (userLoginObject.isLoginInfoTrue(accountNumber, String.valueOf(passwordTF.getPassword()))) {
                        getAccountInfo().login(accountNumber);
                        setVisible(false);
                        new AccountScreen();
                    } else {
                        JOptionPane.showMessageDialog(loginPanel, "Denied! Check your information!");
                    }
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Fill in the blanks!");
                }

            }
        });
        openAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new OpenAccountScreen();
            }
        });
    }

    public static void main(String[] args) {
        new LoginScreen();
    }

    @Override
    public boolean isInfoValid() {
        return GUISettings.isFilled(loginPanel);
    }

    @Override
    public AccountInfo getAccountInfo() {
        return AccountInfo.getInstance();
    }
}
