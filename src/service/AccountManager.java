package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import account.Account;
import account.HighCreditAccount;
import account.NormalAccount;
import account.SpecialAccount;
import exception.InitException;
import exception.InsufficientBalanceException;

public class AccountManager implements AccountService {
	private static final int MAX_ACCOUNTS = 50;
	private static final String FILE_PATH = "src/AccountInfo.obj";
	private Set<Account> accountSet = new HashSet<>();
	public static Scanner scanner = new Scanner(System.in);

	public AccountManager() {
		loadAccountData();
	}

	/**
	 * 계좌 생성 여부확인
	 * 
	 * @return 생성이 가능하면 true, 50개가 넘으면 false를 반환하여 생성불가
	 */
	private boolean maxCreateAccount() {
		if (accountSet.size() >= MAX_ACCOUNTS) {
			System.out.println("더 이상 계좌를 생성할 수 없습니다. (최대 50개)");
			return false;
		}
		return true;
	}

	/**
	 * 보통 계좌 생성
	 */
	@Override
	public void createNormalAccount(String accNumber, String accName, int balance, double interestRate) {
		if (maxCreateAccount()) {
			Account newAccount = new NormalAccount(accNumber, accName, balance, interestRate);
			handleDuplicateAccount(newAccount);
		}
	}

	/**
	 * 신용 신뢰 계좌 생성
	 * 
	 * @param creditRating 신용 등급
	 */
	@Override
	public void createHighCreditAccount(String accNumber, String accName, int balance, double interestRate,
			char creditRating) {
		if (maxCreateAccount()) {
			Account newAccount = new HighCreditAccount(accNumber, accName, balance, interestRate, creditRating);
			handleDuplicateAccount(newAccount);
		}
	}

	/**
	 * 특별 계좌 생성
	 */
	@Override
	public void createSpecialAccount(String accNumber, String accName, int balance, double interestRate) {
		if (maxCreateAccount()) {
			Account newAccount = new SpecialAccount(accNumber, accName, balance, interestRate);
			handleDuplicateAccount(newAccount);
		}
	}

	/**
	 * 중복 계좌 처리
	 * 
	 * @param account 계좌 정보
	 * @return "y"를 작성하면 true, "n"를 작성하면 false를 반환
	 */
	private boolean handleDuplicateAccount(Account account) {
		// accountSet에 이미 동일한 계좌(account)가 있는지 검사
		if (accountSet.contains(account)) {
			String input;
			while (true) {
				System.out.println("중복된 계좌가 발견되었습니다. 덮어쓸까요? (y or n)");
				input = scanner.next();
				// 입력이 'y'라면 기존 계좌를 삭제하고 새 계좌를 추가
				if (input.equals("y")) {
					accountSet.remove(account);
					accountSet.add(account);
					System.out.println("계좌 정보가 덮어쓰기 되었습니다.");
					return true;
					// 입력이 'n'이라면, 기존 계좌를 유지하고 false를 반환
				} else if (input.equals("n")) {
					System.out.println("기존 정보를 유지합니다.");
					return false;
				} else {
					System.out.println("y 또는 n을 입력해주세요.");
				}
			}
			// 중복된 계좌가 없다면 바로 새 계좌를 추가하고 true를 반환
		} else {
			accountSet.add(account);
			return true;
		}
	}

	/**
	 * 계좌 번호 찾기
	 * 
	 * @return 찾은 계좌 객체. 계좌가 없으면 null로 반환
	 */
	private Account findAccountByNumber(String accountNumber) {
		for (Account account : accountSet) {
			// 계좌가 null이 아니고 계좌 번호가 입력받은 계좌 번호와 일치하면 해당 계좌를 반환
			if (account != null && account.getAccNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	/**
	 * 입금
	 * 
	 * @param amount 입금 금액
	 * @throws InitException 음수를 입금하거나 500원 단위가 아닐 경우 발생
	 */
	@Override
	public void depositMoney(String accNumber, int amount) throws InitException {
		if (amount < 0) {
			throw new InitException("음수를 입금할 수 없습니다.");
		}
		if (amount % 500 != 0) {
			throw new InitException("입금은 500원 단위로 가능합니다.");
		}
		Account account = findAccountByNumber(accNumber);
		if (account != null) {
			account.depositMoney(amount);
			System.out.println("입금이 완료되었습니다.");
		} else {
			System.out.println("해당 계좌번호를 찾을 수 없습니다.");
		}
	}

	/**
	 * 출금
	 * 
	 * @throws InitException                음수를 출금하거나 1000원 단위가 아닐 경우 발생
	 * @throws InsufficientBalanceException 잔고가 부족할 경우 발생
	 */
	@Override
	public void withdrawMoney(String accNumber, int amount) throws InitException, InsufficientBalanceException {
		if (amount < 0) {
			throw new InitException("음수를 출금할 수 없습니다.");
		}
		if (amount % 1000 != 0) {
			throw new InitException("출금은 1000원 단위로 가능합니다.");
		}
		Account account = findAccountByNumber(accNumber);
		if (account != null) {
			if (account.getBalance() < amount) {
				throw new InsufficientBalanceException();
			} else {
				account.withdrawMoney(amount);
				System.out.println("출금이 완료되었습니다.");
			}
		} else {
			System.out.println("해당 계좌번호를 찾을 수 없습니다.");
		}
	}

	/**
	 * 계좌에 있는 전액을 출금
	 */
	@Override
	public void withdrawAllMoney(String accNumber) {
		Account account = findAccountByNumber(accNumber);
		if (account != null) {
			int fullAmount = account.getBalance();
			account.withdrawMoney(fullAmount);
			System.out.println("전체 금액 " + fullAmount + "원이 출금되었습니다.");
		}
	}

	/**
	 * 모든 계좌를 가져옴
	 * 
	 * @return 모든 계좌의 배열
	 */
	@Override
	public Account[] getAllAccounts() {
		// accountSet에 저장된 모든 계좌 정보를 배열로 변환하여 반환
		// new Account[0]은 크기가 0인 Account 타입의 배열을 생성
		// 배열에 accountSet의 모든 요소를 복사하여 반환
		return accountSet.toArray(new Account[0]);
	}

	/**
	 * 계좌 삭제
	 */
	public void deleteAccount(String accNumber) {
		Account account = findAccountByNumber(accNumber);
		if (account != null) {
			accountSet.remove(account);
			System.out.println("계좌가 삭제되었습니다.");
		} else {
			System.out.println("해당 계좌번호를 찾을 수 없습니다.");
		}
	}

	/**
	 * 계좌 데이터 불러옴
	 */
	private void loadAccountData() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			// HashSet<Account> 타입으로 캐스팅하여 accountSet에 저장
			// 반환값이 Objectd이기 때문에 반드시 캐스팅을 해야함
			accountSet = (HashSet<Account>) in.readObject();
		} catch (FileNotFoundException e) {
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 계좌 데이터 저장
	 */
	public void saveAccountData() {
		try (ObjectOutputStream in = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			// ObjectOutputStream의 writeObject 메소드를 사용하면 객체를 직렬화하여 파일에 저장
			in.writeObject(accountSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 계좌 데이터를 지정된 파일 경로에 저장
	 * 
	 * @param filePath 파일 경로
	 */
	public void saveAccountData(String filePath) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			// accountSet에 저장된 모든 계좌 정보를 순회
			for (Account account : accountSet) {
				// 각 계좌의 정보를 문자열로 변환하여 파일에 씀
				writer.println(account.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}