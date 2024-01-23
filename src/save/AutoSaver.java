package save;

import service.AccountService;

public class AutoSaver extends Thread {
    private AccountService accountService;

    /**
     * AutoSaver 객체를 생설하고 데몬 쓰레드로 설정
     * @param accountService 계좌 서비스 객체
     */
    public AutoSaver(AccountService accountService) {
        this.accountService = accountService;
        setDaemon(true);  // 데몬 쓰레드로 설정
    }

    /**
     * 계좌 정보를 자동으로 5초마다 저장
     */
    @Override
    public void run() {
        while (true) {
            accountService.saveAccountData("src/AutoSaveAccount.txt");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

