public class MatchHistory {
    private Match[] data = new Match[4];
    private int size = 0;

    public int size() { return size; }

    public Match get(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        return data[i];
    }

    public void add(Match m) {
        if (size == data.length) grow();
        data[size++] = m;
    }

    private void grow() {
        Match[] bigger = new Match[data.length * 2];
        for (int i = 0; i < data.length; i++) bigger[i] = data[i];
        data = bigger;
    }
}
