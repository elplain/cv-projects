public class Card {
    private final String rank;
    private final String suit;

    public Card(String r, String s) {
        rank = r;
        suit = s;
    }

    public boolean isAce() {

        return rank.equals("A");
    }

    public int valueIfAce11() {
        if (rank.equals("A")) return 11;//ace is 11
        if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) return 10;//K Q and J ar all equal 10
        return Integer.parseInt(rank);//converts string to integer
    }

    public String toString() {
        return rank + suit;
    }
}
