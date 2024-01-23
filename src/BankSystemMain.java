import service.AccountService;
import service.AccountManager;
import ui.UserInterface;

public class BankSystemMain {
    public static void main(String[] args) {
        AccountService accountService = new AccountManager();
        UserInterface userInterface = new UserInterface(accountService);
        userInterface.run();
    }
}
