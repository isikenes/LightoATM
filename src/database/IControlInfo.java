package database;

import database.transactions.AccountInfo;

public interface IControlInfo {
    public boolean isInfoValid();

    public AccountInfo getAccountInfo();
}
