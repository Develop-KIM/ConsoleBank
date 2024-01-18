package banking;

public abstract class Account {
    private String accNumber; // 계좌번호
    private String accName; // 계좌주
    private int balance; // 잔액
    private double interestRate; // 이자율

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
    public String toString() {
        return "계좌번호> " + accNumber + "\n" +
                "고객이름> " + accName + "\n" +
                "잔고> " + balance + "\n" +
                "기본이자> " + (int) interestRate + "%\n";
    }
}
