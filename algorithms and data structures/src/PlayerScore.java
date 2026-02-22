public class PlayerScore {
    private Player[] data = new Player[8];//initial capacity
    private int size = 0; //number of players stored from the start

    /////linear search through arrays////////
    public Player find(String name) {
        for (int i = 0; i < size; i++)
            if (data[i].getName().equalsIgnoreCase(name))
                return data[i];
        return null;
    }
    //*2 size array if full
    public void add(Player p) {
        if (size == data.length) grow();
        data[size++] = p;
    }

    public Player[] toArray() {
        Player[] a = new Player[size];
        for (int i = 0; i < size; i++) a[i] = data[i];
        return a;
    }
    //manually resizes
    private void grow() {
        Player[] bigger = new Player[data.length * 2];
        for (int i = 0; i < data.length; i++) bigger[i] = data[i];
        data = bigger;
    }
}
