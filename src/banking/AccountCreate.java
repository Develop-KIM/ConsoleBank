package banking;

public class AccountCreate {
	
	public Account createAccount(int choice, String accNumber, String accName, int balance, double interestRate, String creditRating) {
        switch (choice) {
            case 1:
                return new NormalAccount(accNumber, accName, balance, interestRate);
            case 2:
                return new HighCreditAccount(accNumber, accName, balance, interestRate, creditRating);
            default:
            	System.out.println("올바른 계좌 종류를 선택하세요.");
                return null;
        }
    }
}
