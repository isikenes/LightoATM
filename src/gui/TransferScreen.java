package gui;

import database.transactions.AccountInfo;
import database.transactions.Transfer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransferScreen extends JFrame {
    private JButton backButton;
    private JLabel moneyLabel;
    private JTextField amountTF;
    private JButton transferButton;
    private JTextField receiverTF;
    private JPanel transferPanel;

    private int amount;
    private Transfer transferObject;

    public TransferScreen() {
        setContentPane(transferPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        transferPanel.setFocusable(true);
        GUISettings.buttonHover(backButton);
        GUISettings.buttonHover(transferButton);
        GUISettings.setOnlyNumber(amountTF);
        GUISettings.setOnlyNumber(receiverTF);
        GUISettings.setLimit(amountTF, 5);

        moneyLabel.setText(AccountInfo.getInstance().getBalance() + "$");
        transferObject = new Transfer();
        amountTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String amountText = amountTF.getText();
                if (!amountText.isEmpty()) {
                    amount = Integer.parseInt(amountText);
                    if (amount > 25000) {
                        amount = 25000;
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
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AccountInfo.getInstance().getBalance() < amount) {
                    JOptionPane.showMessageDialog(transferPanel, "Insufficient balance!");
                    return;
                }
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(transferPanel, "Amount can't be zero!");
                    return;
                }
                if (receiverTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(transferPanel, "Fill in the receiver account number!");
                    return;
                }
                if (transferObject.isTransferCompleted(amount, receiverTF.getText())) {
                    JOptionPane.showMessageDialog(transferPanel, "Transfer completed!\nTransferred amount: " + amount + "$");
                    setVisible(false);
                    new AccountScreen();
                } else {
                    JOptionPane.showMessageDialog(transferPanel, "Error!");
                }


            }
        });
    }
}
