package banking;

public class BankSystemMain implements ICustomDefine {

    public static void main(String[] args) {
    	AccountManager accountManager = new AccountManager(50);
    	UserInput userInput = new UserInput();

        while (true) {
            System.out.println("-----Menu------");
            System.out.println("1.계좌개설");
            System.out.println("2.입	금");
            System.out.println("3.출	금");
            System.out.println("4.계좌정보출력");
            System.out.println("5.프로그램종료");
            System.out.print("선택: ");
            
            int choice = userInput.getInt();
            	switch (choice) {
            		case MAKE:
            		accountManager.makeAccount();
            		break;
            		case DEPOSIT:
            		accountManager.depositMoney();
            		break;
            		case WITHDRAW:
            		accountManager.withdrawMoney();
            		break;
            		case INQUIRE:
            		accountManager.showAccInfo();
            		break;
            		case EXIT:
            		System.out.println("프로그램 종료");
            		return;
            		default:
            		System.out.println("올바른 메뉴를 입력하세요.");
                	break;
            	}
        }

    }
}
