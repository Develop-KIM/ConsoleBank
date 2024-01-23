package account;

public enum MenuOption {
    MAKE(1),
    DEPOSIT(2),
    WITHDRAW(3),
    INQUIRE(4),
    DELETE(5),
    SAVE(6),
    EXIT(7);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

