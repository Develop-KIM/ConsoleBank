package account;

public class HighCreditAccount extends NormalAccount {
    private char creditRating;

    /**
     * @param creditRating 신용 등급
     */
    public HighCreditAccount(String accNumber, String accName, int balance, double interestRate, char creditRating) {
        super(accNumber, accName, balance, interestRate);
        this.creditRating = creditRating;
    }

    /**
     * 신용 등급에 따른 추가 이자를 계산 및 입금
     * @param amount 입금할 금액
     */
    @Override
    public void depositMoney(int amount) {
        CreditRating rating = CreditRating.valueOf(String.valueOf(creditRating));
        double additionalInterestRate = rating.getAdditionalInterestRate();

        int interest = (int) ((getBalance() * getInterestRate() / 100.0) + (getBalance() * additionalInterestRate));
        setBalance(getBalance() + interest + amount);
    }

    @Override
    public String toString() {
        return super.toString() +
                "신용등급> " + creditRating;
    }

}