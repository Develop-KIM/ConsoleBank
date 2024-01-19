package banking;

public class BankSystemImpl {
	private static final String FILENAME = "src/banking/AccountInfo.obj";

	private AccountManager accountManager;
	private AutoSaver autoSaver;
	private Thread autoSaverThread;
	private MenuHandler menuHandler;

	public BankSystemImpl() {
		this.accountManager = new AccountManager(50);
		this.autoSaver = new AutoSaver(accountManager);
		this.autoSaverThread = new Thread(autoSaver);
		this.menuHandler = new MenuHandler();
		initBankSystem();
	}

	public void handleBankSystem() {
		while (true) {
			menuHandler.showMenu();
			int choice = menuHandler.getMenuSelection();
			MenuOption option = MenuOption.fromInt(choice);
			handleMenuSelection(option);
		}
	}

	private void initBankSystem() {
		accountManager.loadAccountData(FILENAME);
		autoSaverThread.setDaemon(true);
	}

	private void handleMenuSelection(MenuOption option) {
		if (option == null) {
			System.out.println("올바른 메뉴를 입력하세요.");
			return;
		}

		switch (option) {
		case MAKE_ACCOUNT:
			accountManager.makeAccount();
			break;
		case DEPOSIT_MONEY:
			accountManager.depositMoney();
			break;
		case WITHDRAW_MONEY:
			accountManager.withdrawMoney();
			break;
		case SHOW_ACCOUNT_INFO:
			accountManager.showAccInfo();
			break;
		case DELETE_ACCOUNT:
			accountManager.showAccDel();
			break;
		case SAVE_OPTION:
			handleSaveOption();
			break;
		case EXIT:
			exitBankSystem();
			return;
		}
	}

	private void handleSaveOption() {
		System.out.println("1. 자동저장 On");
		System.out.println("2. 자동저장 Off");
		int saveOptionValue = menuHandler.getMenuSelection();
		SaveOption saveOption = SaveOption.fromInt(saveOptionValue);
		if (saveOption == SaveOption.AUTO_SAVE_ON) {
			turnOnAutoSave();
		} else if (saveOption == SaveOption.AUTO_SAVE_OFF) {
			turnOffAutoSave();
		}
	}

	private void turnOnAutoSave() {
		if (!autoSaverThread.isAlive()) {
			autoSaverThread.start();
		} else {
			System.out.println("이미 자동저장이 실행 중입니다.");
		}
	}

	private void turnOffAutoSave() {
		autoSaver.stop();
	}

	private void exitBankSystem() {
		autoSaver.stop();
		accountManager.saveAccountData(FILENAME);
		System.out.println("프로그램 종료");
		System.exit(0);
	}
}
