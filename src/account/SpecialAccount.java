package account;

public class SpecialAccount extends NormalAccount {
	private int depositCount;

	public SpecialAccount(String accNumber, String accName, int balance, double interestRate) {
		super(accNumber, accName, balance, interestRate);
		this.depositCount = 0;
	}

	/**
	 * 짝수 번째의 입금마다 500원의 축하금을 지금
	 */
	@Override
	public void depositMoney(int amount) {
		super.depositMoney(amount);
		this.depositCount++;
		if (this.depositCount % 2 == 0) {
			this.balance += 500;
			System.out.println(this.depositCount + "번째 입금으로 500원의 축하금이 지급되었습니다.");
		} else {
			System.out.println("현재 입금 횟수: " + this.depositCount);
		}
	}

	@Override
	public String toString() {
		return super.toString() + "입금횟수> " + depositCount;
	}
}
