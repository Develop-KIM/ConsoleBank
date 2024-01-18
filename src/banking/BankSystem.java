package banking;

public class BankSystem {
	private static final int MAX_ACCOUNTS = 50;
	private Account[] accounts = new Account[MAX_ACCOUNTS];
	private int numAccounts = 0;
	
	//계좌개설
	public void makeAccount(String accNumber, String accName, int balance) {
		if (numAccounts < MAX_ACCOUNTS) {
			accounts[numAccounts++] = new Account(accNumber, accName, balance);
			System.out.println("계좌개설이 완료되었습니다.");
		} else {
			System.out.println("더 이상 계좌를 개설할 수 없습니다");
		}
	}
	
	//계좌조회
	public Account findAccount(String accNumber) {
		for (int i = 0; i < numAccounts; i++) {
			if (accounts[i].getAccNumber().equals(accNumber)) {
				return accounts[i];
			}
		}
		return null;
	}
	
	//전체계좌 정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("-----------------");
		
		for(int i = 0; i < numAccounts; i++) {
			System.out.println("계좌번호: " + accounts[i].getAccNumber());
			System.out.println("고객이름: " + accounts[i].getAccName());
			System.out.println("잔고: " + accounts[i].getBalance());
			System.out.println("-----------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

}
