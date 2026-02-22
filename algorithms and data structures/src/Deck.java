import java.util.Random;

public class Deck {
    private final Card[] cards = new Card[52];
    private int top = 0;
    private final Random rng = new Random();

    public Deck() {
        build();
        shuffle();
    }

    public void reset() {
        build();
        shuffle();
        top = 0;
    }

    public Card draw() {  //return the next available card in the deck and move the pointer forward
        if (top >= cards.length) throw new IllegalStateException("Deck empty");
        return cards[top++];
    }

    private void build() {
        String[] suits = {"♠","♥","♦","♣"};// all possible suits in a standard deck
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};// All possible ranks
        int i = 0;
        //nested loops generate every combination of rank + suit (4 suits × 13 ranks = 52 cards)
        for (String s : suits)
            for (String r : ranks)
                cards[i++] = new Card(r, s); // create and store each card
    }

    //fisher–Yates shuffle
    private void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            Card t = cards[i];
            cards[i] = cards[j];
            cards[j] = t;
        }
    }
}
