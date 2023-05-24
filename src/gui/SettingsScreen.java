package gui;

import database.transactions.AccountInfo;
import database.transactions.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsScreen extends JFrame {
    private JPanel settingsPanel;
    private JButton backButton;
    private JTextField phoneTF;
    private JTextField passwordTF;
    private JButton phoneButton;
    private JButton passwordButton;
    private JButton saveButton;
    private JTextField answerTF;
    private JLabel securityQuestionLabel;
    private Settings settingsObject;

    private int counter1 = 0;
    private int counter2 = 0;
    private String oldPhone;
    private String oldPassword;

    public SettingsScreen() {
        setContentPane(settingsPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        settingsPanel.setFocusable(true);
        GUISettings.buttonHover(backButton);
        GUISettings.buttonHover(phoneButton);
        GUISettings.buttonHover(passwordButton);
        GUISettings.setOnlyNumber(phoneTF);
        GUISettings.setLimit(phoneTF, 11);
        settingsObject = new Settings();
        oldPhone = AccountInfo.getInstance().getPhone();
        oldPassword = AccountInfo.getInstance().getPassword();
        phoneTF.setText(oldPhone);
        passwordTF.setText(oldPassword);

        securityQuestionLabel.setText(AccountInfo.getInstance().getSecurityQuestion());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AccountScreen();
            }
        });

        phoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter1 == 0) {
                    phoneTF.setEditable(true);
                    phoneButton.setIcon(new ImageIcon(getClass().getResource("/resources/confirm.png")));
                    counter1++;
                } else {
                    if (phoneTF.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(settingsPanel, "Phone number cannot be empty!");
                        return;
                    }
                    phoneTF.setEditable(false);
                    phoneButton.setIcon(new ImageIcon(getClass().getResource("/resources/edit.png")));
                    counter1 = 0;

                }
            }
        });
        passwordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter2 == 0) {
                    passwordTF.setEditable(true);
                    passwordButton.setIcon(new ImageIcon(getClass().getResource("/resources/confirm.png")));
                    counter2++;
                } else {
                    passwordTF.setEditable(false);
                    passwordButton.setIcon(new ImageIcon(getClass().getResource("/resources/edit.png")));
                    counter2 = 0;
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!answerTF.getText().equals(AccountInfo.getInstance().getSecurityAnswer())) {
                    JOptionPane.showMessageDialog(settingsPanel, "Security answer is wrong!");
                    return;
                }
                String message = "";
                if (!phoneTF.getText().equals(oldPhone) && settingsObject.isPhoneNumberUpdated(phoneTF.getText())) {
                    message += "Phone number is updated!\n";
                }
                if (!passwordTF.getText().equals(oldPassword) && settingsObject.isPasswordUpdated(passwordTF.getText())) {
                    message += "Password is updated!\n";
                }
                if (!message.isEmpty()) {
                    JOptionPane.showMessageDialog(settingsPanel, message);
                    setVisible(false);
                    new AccountScreen();
                }
            }
        });
    }
}
