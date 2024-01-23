package account;

/**
 * 신용 등급
 */
public enum CreditRating {
    A(0.07),
    B(0.04),
    C(0.02);

    private final double additionalInterestRate;

    /**
     * 신용 등급과 추가 이자율을 설정하는 생성자
     * @param additionalInterestRate 추가 이자율
     */
    CreditRating(double additionalInterestRate) {
        this.additionalInterestRate = additionalInterestRate;
    }

    /**
     * 추가 이자율을 가져옴
     * @return 추가 이자율
     */
    public double getAdditionalInterestRate() {
        return additionalInterestRate;
    }
}