package banking;

public class HighCreditAccount extends Account implements ICustomDefine {
    private String creditGrade;

    public HighCreditAccount(String accNumber, String accName, int balance, double interestRate, String creditGrade) {
        super(accNumber, accName, balance, interestRate);
        this.creditGrade = creditGrade;
    }

    private double calculateAdditionalInterestRate() {
        switch (creditGrade) {
            case "A":
                return CREDIT_GRADE_A;
            case "B":
                return CREDIT_GRADE_B;
            case "C":
                return CREDIT_GRADE_C;
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
                "신용등급> " + creditGrade + "\n";
    }
}