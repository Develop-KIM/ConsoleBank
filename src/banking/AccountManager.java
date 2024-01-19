package banking;

import java.util.HashSet;

public class AccountManager {
	private HashSet<Account> accountSet;
	private UserInput userInput;
	private AccountCreate accountCreate;
	    
	public AccountManager(int size) {
		accountSet = new HashSet<>();
		userInput = new UserInput();
		accountCreate = new AccountCreate();
	}
	
    public void makeAccount() {
        System.out.println("***신규계좌개설***");
        System.out.println("-----계좌선택------");
        System.out.println("1. 보통계좌");
        System.out.println("2. 신용신뢰계좌");
        System.out.print("선택: ");
        int choice = userInput.getInt();
        System.out.print("계좌번호: ");
        String accNumber = userInput.getString();
        System.out.print("고객이름: ");
        String accName = userInput.getString();
        System.out.print("잔고: ");
        int balance = userInput.getInt();
        System.out.print("기본이자%(정수형태로입력): ");
        double interestRate = userInput.getDouble();
        
        String creditGrade = null;
        if (choice == 2) {
            System.out.print("신용등급(A, B, C등급): ");
            creditGrade = userInput.getString();
        }
        
        Account newAccount = accountCreate.createAccount(choice, accNumber, accName, balance, interestRate, creditGrade);
        
        if (accountSet.contains(newAccount)) {
            System.out.println("중복계좌발견됨. 덮어쓸까요?(YES or NO)");
            String response = userInput.getString();
            if (response.equalsIgnoreCase("YES")) {
                accountSet.remove(newAccount);
                accountSet.add(newAccount);
                System.out.println("계좌 정보를 덮어썼습니다.");
            } else {
                System.out.println("기존 계좌 정보를 유지합니다.");
            }
        } else {
            accountSet.add(newAccount);
            System.out.println("계좌계설이 완료되었습니다.");
        }
    }

    public Account findAccount(String accNumber) {
        for (Account account : accountSet) {
            if (account.getAccNumber().equals(accNumber)) {
                return account;
            }
        }
        return null;
    }
    
    public void depositMoney() {
        System.out.println("***입   금***");
        System.out.println("계좌번호와 입금할 금액을 입력하세요");
        System.out.print("계좌번호: ");
        String accNumber = userInput.getString();

        int depositMoney = 0;
        while (true) {
            System.out.print("입금액: ");
            depositMoney = userInput.getInt();
            
            if (depositMoney < 0) {
            	System.out.println("입금액은 음수일 수 없습니다. 다시 입력해주세요.");
            	continue;
            }
                
            if (depositMoney % 500 != 0) {
                System.out.println("입금액은 500원 단위로 가능합니다. 다시 입력해주세요.");
                continue;
            }
        break;      
        }

        Account account = findAccount(accNumber);
        if (account != null) {
            double interest = account.calculateInterest();
            account.setBalance(account.getBalance() + depositMoney + (int)interest);
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("해당 계좌번호가 없습니다.");
        }
    }
 
    public void withdrawMoney() {
        System.out.println("***출   금***");
        System.out.println("계좌번호와 출금할 금액을 입력하세요");
        System.out.print("계좌번호: ");
        String accNumber = userInput.getString();

        int withdrawMoney = 0;
        while (true) {
            System.out.print("출금액: ");

                withdrawMoney = userInput.getInt();
                
                if (withdrawMoney < 0) {
                    System.out.println("출금액은 음수일 수 없습니다. 다시 입력해주세요.");
                    continue;
                }
                
                if (withdrawMoney % 1000 != 0) {
                    System.out.println("출금액은 1000원 단위로 가능합니다. 다시 입력해주세요.");
                    continue;
                }
                break;
            
        }

        Account account = findAccount(accNumber);
        if (account != null) {
            if (account.getBalance() < withdrawMoney) {
                System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요? (YES/NO)");
                String response = userInput.getString();
                if (response.equalsIgnoreCase("YES")) {
                    account.setBalance(0);
                    System.out.println("금액 전체를 출금하였습니다.");
                } else {
                    System.out.println("출금 요청이 취소되었습니다.");
                }
            } else {
                account.setBalance(account.getBalance() - withdrawMoney);
                System.out.println("출금이 완료되었습니다.");
            }
        } else {
            System.out.println("해당 계좌번호가 없습니다.");
        }
    }


    public void showAccInfo() {
        for (Account account : accountSet) {
            System.out.println(account.toString());
            System.out.println("-------------");
        }
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
    
    public void showAccDel() {
        System.out.println("-----계좌삭제------");
        System.out.print("삭제할 계좌번호: ");
        String accNumber = userInput.getString();
        Account account = findAccount(accNumber);
        if (account != null) {
            accountSet.remove(account);
            System.out.println("계좌가 삭제되었습니다.");
        } else {
            System.out.println("해당 계좌번호가 없습니다.");
        }
    }
}