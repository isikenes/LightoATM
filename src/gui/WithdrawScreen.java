package gui;

import database.transactions.AccountInfo;
import database.transactions.Withdraw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WithdrawScreen extends JFrame {
    private JPanel withdrawPanel;
    private JLabel moneyLabel;
    private JTextField amountTF;
    private JButton backButton;
    private JButton withdrawButton;

    private int amount = 0;

    private Withdraw withdrawObject;

    public WithdrawScreen() {
        setContentPane(withdrawPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        withdrawPanel.setFocusable(true);
        GUISettings.buttonHover(backButton);
        GUISettings.buttonHover(withdrawButton);
        GUISettings.setOnlyNumber(amountTF);
        GUISettings.setLimit(amountTF, 5);
        withdrawObject = new Withdraw();
        moneyLabel.setText(AccountInfo.getInstance().getBalance() + "$");
        amountTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String amountText = amountTF.getText();
                if (!amountText.isEmpty()) {
                    amount = Integer.parseInt(amountText);
                    if (amount > 10000) {
                        amount = 10000;
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
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(withdrawPanel, "Amount can't be zero!");
                    return;
                }
                if (AccountInfo.getInstance().getBalance() < amount) {
                    JOptionPane.showMessageDialog(withdrawPanel, "Insufficient balance!");
                    return;
                }
                if (withdrawObject.isWithdrawCompleted(amount)) {
                    JOptionPane.showMessageDialog(withdrawPanel, "Withdraw completed!\nWithdrawal amount: " + amount + "$");
                    setVisible(false);
                    new AccountScreen();
                } else {
                    JOptionPane.showMessageDialog(withdrawPanel, "Error!");
                }


            }
        });
    }
}
