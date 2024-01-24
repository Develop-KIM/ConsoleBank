package save;

import service.AccountService;

public class AutoSaver extends Thread {
	private AccountService accountService;

	/**
	 * AutoSaver 객체를 생성하고 데몬 쓰레드로 설정
	 * 
	 * @param accountService 계좌 서비스 객체
	 */
	public AutoSaver(AccountService accountService) {
		this.accountService = accountService; // 계좌 서비스 객체를 받아서 내부 변수에 저장
		setDaemon(true); // 데몬 쓰레드로 설정
	}

	/**
	 * 계좌 정보를 자동으로 5초마다 저장
	 * 
	 */
	@Override
	public void run() {
		while (true) {
			accountService.saveAccountData("src/AutoSaveAccount.txt"); // 계좌 정보 파일에 저장
			try {
				Thread.sleep(5000); // 5초동안 쓰레드가 멈춤
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
