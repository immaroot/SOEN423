package Core;

public enum Campus {
    KKL (1),
    WST (2),
    DVL (3);

    private final int index;

    Campus(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}
