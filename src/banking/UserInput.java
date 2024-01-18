package banking;

import java.util.Scanner;

public class UserInput {
    
	private Scanner input;

    public UserInput() {
        this.input = new Scanner(System.in);
    }

    public int getInt() {
        return input.nextInt();
    }

    public String getString() {
        return input.next();
    }

    public double getDouble() {
        return input.nextDouble();
    }
}