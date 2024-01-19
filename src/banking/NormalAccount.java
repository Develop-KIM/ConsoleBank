package banking;

public class NormalAccount extends Account {

	public NormalAccount(String accNumber, String accName, int balance, double interestRate) {
		super(accNumber, accName, balance, interestRate);
	}

	@Override
	public double calculateInterest() {
		return getBalance() * (getInterestRate() / 100);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
