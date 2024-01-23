package banking;

public enum MenuOption {
	MAKE_ACCOUNT(1), DEPOSIT_MONEY(2), WITHDRAW_MONEY(3), SHOW_ACCOUNT_INFO(4), DELETE_ACCOUNT(5), SAVE_OPTION(6),
	EXIT(7);

	private final int value;

	MenuOption(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static MenuOption fromInt(int i) {
		for (MenuOption option : values()) {
			if (option.getValue() == i) {
				return option;
			}
		}
		return null;
	}
}
