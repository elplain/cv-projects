public final class Scoring {
    private Scoring() {}

    public static int score(int total, int target) {
        if (total == target) return -5;          // reward for getting in to the target
        if (total <= target) return target - total;// undershoot
        return 2 * (total - target);            // overshoot penalty
    }

    public static BestTotal optimise(Card[] hand, int count, int target) {
        int nonAce = 0, aces = 0;

        for (int i = 0; i < count; i++) {//separetes ace cards from others
            if (hand[i].isAce()) aces++;
            else nonAce += hand[i].valueIfAce11();
        }

        int bestTotal = nonAce + aces * 11;//checks if all aces = 11
        int bestScore = score(bestTotal, target);

        int total = bestTotal;
        //tried to convert aces from 11 to 1 for the best score
        for (int i = 1; i <= aces; i++) {
            total -= 10; // convert one ace from 11 to 1
            int s = score(total, target);
            if (s < bestScore) {
                bestScore = s;
                bestTotal = total;
            }
        }

        return new BestTotal(bestTotal, bestScore);
    }
}
