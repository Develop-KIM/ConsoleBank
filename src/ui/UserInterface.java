package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import account.Account;
import account.MenuOption;
import exception.InitException;
import exception.InsufficientBalanceException;
import save.AutoSaver;
import service.AccountService;

/**
 * 사용자 인터페이스 클래스
 */
public class UserInterface {

	Scanner scanner = new Scanner(System.in);
	private AccountService accountService;
	private AutoSaver autoSaver;

	/**
	 * 객체를 생성하고 계좌 서비스를 설정
	 */
	public UserInterface(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * 메뉴를 실행하고 사용자의 선택에 따른 기능을 수행
	 */
	public void run() {
		while (true) {
			try {
				showMenu();
				int choice = scanner.nextInt();
				if (1 > choice || choice > 7) {
					throw new InitException("1~7 사이의 숫자를 입력해주세요.");
				}
				// 배열의 인덱스는 0부터 시작하기 때문에 1을 빼야함
				MenuOption menuOption = MenuOption.values()[choice - 1];
				switch (menuOption) {
				case MAKE:
					makeAccount();
					break;
				case DEPOSIT:
					depositMoney();
					break;
				case WITHDRAW:
					withdrawMoney();
					break;
				case INQUIRE:
					showAllAccounts();
					break;
				case DELETE:
					deleteAccount();
					break;
				case SAVE:
					showSaveOptionMenu();
					break;
				case EXIT:
					accountService.saveAccountData();
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println("메뉴 선택은 숫자(1 ~ 7)로 해주세요.");
				scanner.nextLine();
			} catch (InitException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 메뉴 출력
	 */
	private void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입	금");
		System.out.println("3. 출	금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 저장옵션");
		System.out.println("7. 프로그램종료");
		System.out.print("선택: ");
	}

	/**
	 * 저장 옵션 메뉴
	 */
	private void showSaveOptionMenu() {
		System.out.println("1. 자동저장On");
		System.out.println("2. 자동저장Off");

		System.out.print("선택 : ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			if (autoSaver != null && autoSaver.isAlive()) {
				System.out.println("이미 자동저장이 실행중입니다");
			} else {
				autoSaver = new AutoSaver(accountService);
				autoSaver.start();
			}
			break;
		case 2:
			if (autoSaver != null) {
				autoSaver.interrupt();
				autoSaver = null;
			}
			break;
		}
	}

	/**
	 * 계좌 생성
	 */
	private void makeAccount() {

		int accountType;
		System.out.println("***신규계좌개설***");

		while (true) {
			System.out.println("1. 보통 계좌");
			System.out.println("2. 신용신뢰계좌");
			System.out.println("3. 특판 계좌");
			System.out.print("선택: ");
			if (scanner.hasNextInt()) {
				accountType = scanner.nextInt();
				if (accountType == 1 || accountType == 2 || accountType == 3) {
					break;
				} else {
					System.out.println("1 ~ 3을 입력해주세요.");
				}
			} else {
				System.out.println("메뉴 선택은 숫자(1 ~ 3)로 해주세요.");
				scanner.next();
			}
		}

		String accountNumber;

		while (true) {
			System.out.print("계좌번호: ");
			accountNumber = scanner.next();
			// 문자열이 0 부터 9까지의 숫자와 하이픈(-)으로 이루어져야함
			if (accountNumber.matches("[0-9-]+")) {
				break;
			}
			System.out.println("계좌번호는 숫자와 '-'로만 입력해주세요.");
		}

		System.out.print("고객이름: ");
		String name = scanner.next();
		System.out.print("잔고: ");
		int balance = scanner.nextInt();
		int interestRate;

		while (true) {
			System.out.print("기본이자%(정수형태로입력): ");
			if (scanner.hasNextInt()) {
				interestRate = scanner.nextInt();
				break;
			} else {
				System.out.println("기본 이자는 정수로 입력해주세요.");
				scanner.next();
			}
		}

		if (accountType == 3) {
			accountService.createSpecialAccount(accountNumber, name, balance, interestRate);
		} else if (accountType == 2) {
			char creditRating;
			while (true) {
				System.out.print("신용등급(A, B, C등급): ");
				creditRating = scanner.next().toUpperCase().charAt(0);
				if (creditRating == 'A' || creditRating == 'B' || creditRating == 'C') {
					break;
				} else {
					System.out.println("신용등급은 A, B, C 중 하나로 입력해주세요.");
				}
			}
			accountService.createHighCreditAccount(accountNumber, name, balance, interestRate, creditRating);
		} else {
			accountService.createNormalAccount(accountNumber, name, balance, interestRate);
		}
	}

	/**
	 * 입금하는 과정
	 */
	private void depositMoney() {

		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		String accNumber = scanner.next();

		while (true) {
			System.out.print("입금액: ");
			try {
				int amount = scanner.nextInt();
				accountService.depositMoney(accNumber, amount);
				break;
			} catch (InputMismatchException e) {
				System.out.println("입금액은 숫자로 입력해주세요.");
				scanner.nextLine();
			} catch (InitException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 출금하는 과정
	 */
	private void withdrawMoney() {
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		String accNumber = scanner.next();

		while (true) {
			System.out.print("출금액: ");
			try {
				int amount = scanner.nextInt();
				accountService.withdrawMoney(accNumber, amount);
				break;
			} catch (InputMismatchException e) {
				System.out.println("출금액은 숫자로 입력해주세요.");
				scanner.nextLine();
			} catch (InitException e) {
				System.out.println(e.getMessage());
			} catch (InsufficientBalanceException e) {
				while (true) {
					System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요? (YES/NO)");
					String decision = scanner.next();
					if (decision.equals("YES")) {
						accountService.withdrawAllMoney(accNumber);
						System.out.println("전체 금액이 출금되었습니다.");
						return;
					} else if (decision.equals("NO")) {
						System.out.println("출금 요청이 취소되었습니다.");
						return;
					} else {
						System.out.println("YES 또는 NO를 입력해주세요.");
					}
				}
			}
		}
	}

	/**
	 * 모든 계좌의 정보 출력
	 */
	private void showAllAccounts() {
		System.out.println("***계좌정보출력***");
		for (Account account : accountService.getAllAccounts()) {
			System.out.println(account);
			System.out.println("-------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	/**
	 * 계좌 삭제 과정
	 */
	private void deleteAccount() {
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌의 계좌번호를 입력하세요");
		System.out.print("계좌번호: ");
		String accNumber = scanner.next();
		accountService.deleteAccount(accNumber);
	}
}