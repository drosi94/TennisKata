package tennis.model;

public enum Point {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANCE("ADVANCE");

    private final String val;

    private Point(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
