import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Leaderboard leaderboard;
    private final Random rng = new Random();

    public Game(Leaderboard lb) {
        leaderboard = lb;
    }

    public Player playMatch(Scanner sc, Player p1, Player p2, boolean verbose, boolean persist) {
        int target = 40;
        Deck deck = new Deck();

        //randomly selects player and changes turn each round
        boolean p1First = rng.nextBoolean();

        int totalScoreP1 = 0;
        int totalScoreP2 = 0;

        for (int round = 1; round <= 4; round++) {
            deck.reset();

            Player first = p1First ? p1 : p2;
            Player second = p1First ? p2 : p1;

            if (verbose) {
                System.out.println("\nROUND " + round + " | target = " + target + " | deck reshuffled");
                System.out.println(first.getName() + " goes first.");
            }

            TurnResult firstRes = takeTurn(sc, deck, first, target, true, 0, verbose);
            TurnResult secondRes = takeTurn(sc, deck, second, target, false, firstRes.guess, verbose);

            // round winner announcement
            if (verbose) {
                if (firstRes.score < secondRes.score) {
                    System.out.println("Round winner: " + first.getName());
                } else if (secondRes.score < firstRes.score) {
                    System.out.println("Round winner: " + second.getName());
                } else {
                    System.out.println("Round is a draw.");
                }
            }

            //total wins
            if (first == p1) {
                totalScoreP1 += firstRes.score;
                totalScoreP2 += secondRes.score;
            } else {
                totalScoreP2 += firstRes.score;
                totalScoreP1 += secondRes.score;
            }

            // target update (spec):
            // if both undershoot/equal (<= target) target += 5
            // if both overshoot (> target) target -= 5
            // else unchanged
            boolean firstUnderOrEqual = firstRes.total <= target;
            boolean secondUnderOrEqual = secondRes.total <= target;

            boolean firstOver = firstRes.total > target;
            boolean secondOver = secondRes.total > target;

            if (firstUnderOrEqual && secondUnderOrEqual) {
                target += 5;
                if (verbose) System.out.println("Both undershot/equal -> target increases to " + target);
            } else if (firstOver && secondOver) {
                target -= 5;
                if (verbose) System.out.println("Both overshot -> target decreases to " + target);
            } else {
                if (verbose) System.out.println("Mixed outcome -> target stays " + target);
            }

            p1First = !p1First;
        }

        // determine match winner (lowest cumulative score)
        Player winner = null;
        if (totalScoreP1 < totalScoreP2) winner = p1;
        else if (totalScoreP2 < totalScoreP1) winner = p2;

        if (verbose) {
            System.out.println("\n=== MATCH OVER ===");
            System.out.println(p1.getName() + " total: " + totalScoreP1);
            System.out.println(p2.getName() + " total: " + totalScoreP2);
            System.out.println(winner == null ? "Match is a draw!" : ("Winner: " + winner.getName()));
        }


        if (persist) {
            p1.addMatch();
            p2.addMatch();

            if (winner == p1) p1.addWin();
            if (winner == p2) p2.addWin();

            p1.getHistory().add(new Match(p2.getName(), totalScoreP1, winner == p1));
            p2.getHistory().add(new Match(p1.getName(), totalScoreP2, winner == p2));
        }

        return winner;
    }


    private TurnResult takeTurn(Scanner sc, Deck deck, Player p, int target,
                                boolean isFirst, int firstGuess, boolean verbose) {
        int guess;

        if (p.isSimulated()) {
            if (isFirst) {
                guess = rng.nextInt(5) + 3; // 3-7
            } else {
                guess = firstGuess + 2;
            }
            if (guess < 1) guess = 1;
            if (guess > 52) guess = 52;

            if (verbose) {
                System.out.println(p.getName() + " guesses " + guess + " cards.");
            }
        } else {
            if (sc == null) throw new IllegalStateException("Scanner required for real player input.");
            guess = Input.readIntInRange(sc, p.getName() + " guess cards: ", 1, 52);
        }

        Card[] hand = new Card[guess];
        for (int i = 0; i < guess; i++) hand[i] = deck.draw();

        BestTotal best = Scoring.optimise(hand, guess, target);

        if (verbose) {
            System.out.print(p.getName() + ": ");
            for (int i = 0; i < guess; i++) System.out.print(hand[i] + " ");
            System.out.println(" total=" + best.getTotal() + " score=" + best.getScore()
                    + (best.getTotal() == target ? " **perfect!**" : ""));
        }

        return new TurnResult(guess, best.getTotal(), best.getScore());
    }

    private static class TurnResult {
        final int guess;
        final int total;
        final int score;

        TurnResult(int guess, int total, int score) {
            this.guess = guess;
            this.total = total;
            this.score = score;
        }
    }
}
