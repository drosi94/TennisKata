package tennis.model;

public enum Point {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("ADVANTAGE");

    private final String val;

    private Point(String val) {
        this.val = val;
    }

    public static Point fromInteger(int n) {
        switch (n) {
            case 0:
                return Point.LOVE;
            case 15:
                return Point.FIFTEEN;
            case 30:
                return Point.THIRTY;
            case 40:
                return Point.FORTY;
        }
        return null;
    }

    @Override
    public String toString() {
        return val;
    }
}
