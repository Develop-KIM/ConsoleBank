package banking;

public class HighCreditAccount extends Account {
    private String creditRating;

    public HighCreditAccount(String accNumber, String accName, int balance, double interestRate, String creditRating) {
        super(accNumber, accName, balance, interestRate);
        this.creditRating = creditRating;
    }

    private double calculateAdditionalInterestRate() {
        switch (creditRating) {
            case "A":
                return 0.07;
            case "B":
                return 0.04;
            case "C":
                return 0.02;
            default:
                return 0;
        }
    }

    @Override
    public double calculateInterest() {
        double baseInterest = getBalance() * (getInterestRate() / 100);
        double additionalInterest = getBalance() * calculateAdditionalInterestRate();
        return baseInterest + additionalInterest;
    }
    
    @Override
    public String toString() {
        return super.toString() +
                "신용등급> " + creditRating + "\n";
    }
}
