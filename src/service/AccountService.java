package service;

import account.Account;
import exception.InitException;
import exception.InsufficientBalanceException;

/**
 * 계좌 서비스를 위한 인터페이스
 */
public interface AccountService {

	void createNormalAccount(String accNumber, String accName, int balance, double interestRate);

	void createHighCreditAccount(String accNumber, String accName, int balance, double interestRate, char creditRating);

	void createSpecialAccount(String accNumber, String accName, int balance, double interestRate);

	void depositMoney(String accNumber, int amount) throws InitException;

	void withdrawMoney(String accNumber, int amount) throws InitException, InsufficientBalanceException;

	void withdrawAllMoney(String accNumber);

	void deleteAccount(String accNumber);

	void saveAccountData();

	void saveAccountData(String autoSaver);

	Account[] getAllAccounts();

}
