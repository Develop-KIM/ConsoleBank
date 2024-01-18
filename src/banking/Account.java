package banking;

public class Account {
	
	private String accNumber;
	private String accName;
	private int balance;
	
	public Account(String accNumber, String accName, int balance) {
		this.accNumber = accNumber;
		this.accName = accName;
		this.balance = balance;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

    public void deposit(int amount) {
        if (amount >= 0) {
            balance += amount;
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("유효하지 않은 금액입니다. 입금이 취소되었습니다.");
        }
    }

    public void withdraw(int amount) {
        if (amount >= 0 && balance >= amount) {
            balance -= amount;
            System.out.println("출금이 완료되었습니다.");
        } else {
            System.out.println("유효하지 않은 금액이거나 잔액이 부족합니다. 출금이 취소되었습니다.");
        }
    }
}
