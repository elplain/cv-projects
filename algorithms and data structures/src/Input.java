import java.util.Scanner;

public final class Input {
//triggers if blank
    public static String readNonEmpty(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input required.");
        }
    }
//triggers if over or less range and
    public static int readIntInRange(Scanner sc, String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.println("Enter number between " + min + " and " + max);
        }
    }
}
