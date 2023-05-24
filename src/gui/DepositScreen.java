package gui;

import database.transactions.AccountInfo;
import database.transactions.Deposit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DepositScreen extends JFrame {
    private JLabel moneyLabel;
    private JTextField amountTF;
    private JButton depositButton;
    private JButton backButton;
    private JPanel depositPanel;
    private int amount;

    private Deposit depositObject;

    public DepositScreen() {
        setContentPane(depositPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        depositPanel.setFocusable(true);
        GUISettings.buttonHover(backButton);
        GUISettings.buttonHover(depositButton);
        GUISettings.setOnlyNumber(amountTF);
        GUISettings.setLimit(amountTF, 5);

        moneyLabel.setText(AccountInfo.getInstance().getBalance() + "$");

        depositObject = new Deposit();

        amountTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String amountText = amountTF.getText();
                if (!amountText.isEmpty()) {
                    amount = Integer.parseInt(amountText);
                    if (amount > 50000) {
                        amount = 50000;
                        amountTF.setText(String.valueOf(amount));
                    }
                } else {
                    amount = 0;
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AccountScreen();
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(depositPanel, "Amount can't be zero!");
                    return;
                }
                if (depositObject.isDepositCompleted(amount)) {
                    JOptionPane.showMessageDialog(depositPanel, "Deposit completed!\nDeposit amount: " + amount + "$");
                    setVisible(false);
                    new AccountScreen();
                } else {
                    JOptionPane.showMessageDialog(depositPanel, "Error!");
                }

            }
        });
    }
}
