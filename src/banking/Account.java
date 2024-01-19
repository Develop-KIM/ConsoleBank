package banking;

import java.util.Objects;

public abstract class Account {
    private String accNumber; 
    private String accName;
    private int balance;
    private double interestRate;

    public Account(String accNumber, String accName, int balance, double interestRate) {
        this.accNumber = accNumber;
        this.accName = accName;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public String getAccName() {
        return accName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public abstract double calculateInterest();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null || getClass() != obj.getClass())
        	return false;
        Account account = (Account) obj;
        return accNumber.equals(account.accNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accNumber);
    }
    
    @Override
    public String toString() {
        return "계좌번호> " + accNumber + "\n" +
                "고객이름> " + accName + "\n" +
                "잔고> " + balance + "\n" +
                "기본이자> " + (int) interestRate + "%\n";
    }
}
