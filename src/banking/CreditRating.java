package banking;

public enum CreditRating {
	A("A", 0.07), B("B", 0.04), C("C", 0.02);

	private final String level;
	private final double interest;

	CreditRating(String level, double interest) {
		this.level = level;
		this.interest = interest;
	}

	public String getLevel() {
		return level;
	}

	public double getInterest() {
		return interest;
	}

	public static CreditRating fromString(String level) {
		for (CreditRating rating : values()) {
			if (rating.getLevel().equals(level)) {
				return rating;
			}
		}
		return null;
	}
}
