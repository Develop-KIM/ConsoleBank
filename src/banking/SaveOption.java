package banking;

public enum SaveOption {
	AUTO_SAVE_ON(1), AUTO_SAVE_OFF(2);

	private final int value;

	SaveOption(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static SaveOption fromInt(int i) {
		for (SaveOption option : SaveOption.values()) {
			if (option.getValue() == i) {
				return option;
			}
		}
		return null;
	}
}
