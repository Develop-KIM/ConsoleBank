package banking;

public class MenuHandler {
	private UserInput userInput;

	public MenuHandler() {
		this.userInput = new UserInput();
	}

	public void showMenu() {
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

	public int getMenuSelection() {
		return userInput.getInt();
	}
}
