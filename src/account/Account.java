package account;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {
	private String accNumber;
	private String accName;
	int balance;
	private double interestRate;

	/**
	 * 계좌 생성자
	 * 
	 * @param accNumber    계좌 번호
	 * @param accName      계좌 이름
	 * @param balance      초기 잔액
	 * @param interestRate 이자율
	 */
	public Account(String accNumber, String accName, int balance, double interestRate) {
		this.accNumber = accNumber;
		this.accName = accName;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public void depositMoney(int amount) {
		int interest = (int) (balance * (interestRate / 100));
		balance += (amount + interest);
	}

	public void withdrawMoney(int amount) {
		if (balance >= amount) {
			balance -= amount;
		}
	}

	public String getAccNumber() {
		return accNumber;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * 계좌가 같은지 판단
	 * 
	 * @param obj 비교할 객체
	 * @return 같으면 true, 다르면 false를 반환
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account account = (Account) obj;
		return Objects.equals(accNumber, account.accNumber);
	}

	/**
	 * 계좌의 해시코드를 반환
	 * 
	 * @return 계좌의 해시코드
	 */
	@Override
	public int hashCode() {
		return Objects.hash(accNumber);
	}

	@Override
	public String toString() {
		return "계좌번호> " + accNumber + ", " + "고객이름> " + accName + ", " + "잔고> " + balance + ", " + "기본이자> "
				+ (int) interestRate + "% ";
	}
}
