package banking;

import java.util.Scanner;

public class BankSystemMain {

	public static void main(String[] args) {
		
		BankSystem bankSystem = new BankSystem();
		Scanner input = new Scanner(System.in);
		
		int choice;
		
		do {
			showMenu();
			choice = input.nextInt();
			
			switch(choice) {
				case 1:
					makeAccount(bankSystem, input);
					break;
				case 2:
					depositMoney(bankSystem, input);
					break;
				case 3:
					withdrawMoney(bankSystem, input);
					break;
				case 4:
					bankSystem.showAccInfo();
					break;
				case 5:
					System.out.println("프로그램을 종료합니다.");
				default:
					System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
			}
			
		} while (choice != 5);
		}
	
	
	
	public static void showMenu() {
		System.out.println("-----------------");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입   금");
		System.out.println("3. 출   금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램종료");
		System.out.print("선택: ");
	}

	public static void makeAccount(BankSystem bankSystem, Scanner input) {
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호: ");
		String accNumber = input.next();
		System.out.print("고객이름: ");
		String accName = input.next();
		System.out.print("잔고: ");
		int balnce = input.nextInt();
		
		bankSystem.makeAccount(accNumber, accName, balnce);
	}
	
	public static void depositMoney(BankSystem bankSystem, Scanner input) {
		System.out.println("***입  금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String accNumber = input.next();
		System.out.print("입금액: ");
		int amount = input.nextInt();
		
		Account account = bankSystem.findAccount(accNumber);
		if (account != null) {
			account.deposit(amount);
		} else {
			System.out.println("해당하는 계좌번호가 없습니다. 입금이 취소되었습니다.");
		}
	}
	
	public static void withdrawMoney(BankSystem bankSystem, Scanner input) {
		System.out.println("***출  금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		String accNumber = input.next();
		System.out.print("출금액: ");
		int amount = input.nextInt();
		
		Account account = bankSystem.findAccount(accNumber)
;
		if(account != null) {
			account.withdraw(amount);
			} else {
				System.out.println("해당하는 계좌번호가 없습니다. 출금이 취소되었습니다.");
			}
		}
}
