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
		super.depositMoney(amount); // 부모 클래스의 depositMoney 메소드를 호출하여 금액을 입금
		this.depositCount++; // 입금 횟수를 1 증가시킴
		if (this.depositCount % 2 == 0) { // 입금 횟수가 2의 배수일 경우 (즉, 짝수번째 입금일 경우)
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
