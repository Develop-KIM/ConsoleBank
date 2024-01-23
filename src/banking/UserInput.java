package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

	private Scanner input;

	public UserInput() {
		this.input = new Scanner(System.in);
	}

	public int getInt() {
		while (true) {
			try {
				return input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요.");
				input.nextLine();
			}
		}
	}

	public String getString() {
		return input.next();
	}

	public double getDouble() {
		return input.nextDouble();
	}

	public String getNextLine() {
		return input.nextLine();
	}
}