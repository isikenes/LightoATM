package gui;

import database.IControlInfo;
import database.transactions.AccountInfo;
import database.transactions.OpenAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAccountScreen extends JFrame implements IControlInfo {
    private JPanel openAccPanel;
    private JTextField nameTF;
    private JComboBox comboBox1;
    private JButton openAccountButton;
    private JTextField ssnTF;
    private JTextField phoneTF;
    private JTextField answerTF;
    private OpenAccount openAccountObject;

    public OpenAccountScreen() {
        setContentPane(openAccPanel);
        setTitle("Lighto ATM");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        openAccPanel.setFocusable(true);
        GUISettings.setOnlyLetter(nameTF);
        GUISettings.setOnlyNumber(ssnTF);
        GUISettings.setOnlyNumber(phoneTF);
        GUISettings.buttonHover(openAccountButton);
        GUISettings.setLimit(ssnTF, 9);
        GUISettings.setLimit(phoneTF, 11);

        openAccountObject = new OpenAccount();

        openAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInfoValid()) {
                    openAccountObject.setName(nameTF.getText().trim());
                    openAccountObject.setSsn(ssnTF.getText());
                    openAccountObject.setPhone(phoneTF.getText());
                    openAccountObject.setQuestion(String.valueOf(comboBox1.getSelectedItem()));
                    openAccountObject.setAnswer(answerTF.getText());

                    openAccountObject.setAccountNumber(String.valueOf(generateAccountNumber()));
                    openAccountObject.setPassword(String.valueOf((int) (Math.random() * 9000) + 1000));

                    if (openAccountObject.isApplied()) {
                        JOptionPane.showMessageDialog(openAccPanel, "Your account is ready!\nAccount Number: " + openAccountObject.getAccountNumber()
                                + "\nPassword: " + openAccountObject.getPassword());
                        setVisible(false);
                        new LoginScreen();
                    } else {
                        JOptionPane.showMessageDialog(openAccPanel, "Denied! Check your information!");
                    }
                } else {
                    JOptionPane.showMessageDialog(openAccPanel, "Fill in the blanks!");
                }
            }
        });
    }

    private int generateAccountNumber() {
        int x;
        do{
            x = (int) (Math.random() * 90000000) + 10000000;
        } while (openAccountObject.isDuplicate());
        return x;
    }

    @Override
    public boolean isInfoValid() {
        return GUISettings.isFilled(openAccPanel);
    }

    @Override
    public AccountInfo getAccountInfo() {
        return AccountInfo.getInstance();
    }
}
