package gui;

import database.transactions.AccountInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountScreen extends JFrame {
    private JPanel accountPanel;
    private JLabel usernameLabel;
    private JLabel moneyLabel;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton depositButton;
    private JButton logoutButton;
    private JButton settingsButton;

    public AccountScreen() {
        setContentPane(accountPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        accountPanel.setFocusable(true);
        GUISettings.buttonHover(withdrawButton);
        GUISettings.buttonHover(transferButton);
        GUISettings.buttonHover(depositButton);
        GUISettings.buttonHover(logoutButton);
        GUISettings.buttonHover(settingsButton);

        usernameLabel.setText(AccountInfo.getInstance().getName());
        moneyLabel.setText(AccountInfo.getInstance().getBalance() + "$");

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new WithdrawScreen();
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DepositScreen();
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TransferScreen();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(accountPanel, "Are you sure?", "Logout?", JOptionPane.YES_NO_OPTION) == 0) {
                    AccountInfo.getInstance().logout();
                    setVisible(false);
                    new LoginScreen();
                }
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SettingsScreen();
            }
        });
    }
}
