package database.transactions;

import database.DBConnection;
import database.IControlInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OpenAccount extends DBConnection implements IControlInfo {
    private String name;
    private String ssn;
    private String phone;
    private String question;
    private String answer;

    private String accountNumber;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isInfoValid() {
        return !(name.isEmpty() || ssn.isEmpty() || phone.isEmpty() || question.isEmpty() || answer.isEmpty());
    }

    public boolean isApplied() {
        if (isInfoValid()) {
            String query = "INSERT INTO users(name,ssn, phone, security_question, security_answer, account_number, user_password)" +
                    " VALUES(?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, ssn);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, question);
                preparedStatement.setString(5, answer);
                preparedStatement.setString(6, accountNumber);
                preparedStatement.setString(7, password);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicate() {
        String query = "SELECT account_number FROM users WHERE account_number='" + accountNumber + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public AccountInfo getAccountInfo() {
        return AccountInfo.getInstance();
    }
}
