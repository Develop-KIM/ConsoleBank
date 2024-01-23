package banking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AutoSaver implements Runnable {
	private AccountManager accountManager;
	private boolean keepRunning = true;

	public AutoSaver(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public void stop() {
		keepRunning = false;
	}

	public void saveAccountData(String filename) {
		File file = new File(filename);
		try {
			file.createNewFile();
			try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
				String accountData = accountManager.getAccountData();
				out.println(accountData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (keepRunning) {
			saveAccountData("src/banking/AutoSaveAccount.txt");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				keepRunning = false;
			}
		}
	}
}
