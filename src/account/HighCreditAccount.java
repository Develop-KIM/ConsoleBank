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
	 * 
	 * @param amount 입금할 금액
	 */
	@Override
	public void depositMoney(int amount) {
		CreditRating rating = CreditRating.valueOf(String.valueOf(creditRating)); // 신용등급을 문자열로 변환 후, 해당하는 등급의 enum 객체를
																					// 가져옴
		double additionalInterestRate = rating.getAdditionalInterestRate(); // 해당 신용등급의 추가 이자율을 가져옴

		int interest = (int) ((getBalance() * getInterestRate() / 100) + (getBalance() * additionalInterestRate));
		setBalance(getBalance() + interest + amount); // 계산한 이자와 입금액을 현재 잔액에 더해 업데이트
	}

	@Override
	public String toString() {
		return super.toString() + "신용등급> " + creditRating;
	}

}