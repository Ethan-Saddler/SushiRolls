public class Restaurant {
    public static SushiRoll[] mergeSortRolls(SushiRoll[] rolls) {

        if (rolls.length < 2) {
            return rolls;
        }

        int c = (rolls.length) / 2;

        SushiRoll[] copy1 = new SushiRoll[c];
        SushiRoll[] copy2 = new SushiRoll[rolls.length - c];

        for (int n = 0; n < c; n++) {
            copy1[n] = rolls[n];
        }
        for (int j = 0; j < rolls.length - c; j++) {
            copy2[j] = rolls[j + c];
        }

        SushiRoll[] x = mergeSortRolls(copy1);
        SushiRoll[] y = mergeSortRolls(copy2);

        SushiRoll[] z = MergeHelper.merge(x, y);

        return z;

    }

    public static void main(String[] args) {
        SushiRoll a = new SushiRoll("1", "Blue");
        SushiRoll b = new SushiRoll("2");
        SushiRoll c = new SushiRoll("3", "Red");
        SushiRoll d = new SushiRoll("4");
        SushiRoll e = new SushiRoll("5");
        SushiRoll f = new SushiRoll("6");
        SushiRoll g = new SushiRoll("7");
        SushiRoll h = new SushiRoll("8");

        SushiRoll[] rolls = new SushiRoll[] { b, c, d, e, a, g, h };
        SushiRoll[] rolls1 = new SushiRoll[] { e, c, a, e, a, g, h };
        SushiRoll[] rolls2 = new SushiRoll[] { b, c, c, e, a, g, h };
        SushiRoll[] rolls3 = new SushiRoll[] { b, c, d, e, e, g, h };

        SushiRoll[] sortedRolls = mergeSortRolls(rolls);

        SushiRoll[][] rolls2x2 = new SushiRoll[][] { rolls, rolls1, rolls2, rolls3 };

        SushiRoll[] mergedRolls = mergeOrders(rolls2x2);

        mergedRolls = platesOfColor(mergedRolls, "Blue");

        for (SushiRoll roll : mergedRolls) {
            System.out.println(roll.getColor());
        }

        System.out.println(totalPrice(rolls));
    }

    public static SushiRoll[] mergeOrders(SushiRoll[][] rolls) {

        int total = 0;

        for (int n = 0; n < rolls.length; n++) {
            total += rolls[n].length;
        }

        SushiRoll[] flatRolls = new SushiRoll[total];
        int count = 0;

        for (int n = 0; n < rolls.length; n++) {
            for (int j = 0; j < rolls[n].length; j++) {
                flatRolls[count++] = rolls[n][j];
            }
        }

        return mergeSortRolls(flatRolls);
    }

    public static SushiRoll[] platesOfColor(SushiRoll[] rolls, String color) {
        platesHelper(rolls, color, 0);

        int length = 0;
        for (int n = 0; n < rolls.length; n++) {
            if (rolls[n] != null) {
                length++;
            }
        }

        if (length == 0) {
            return null;
        }

        SushiRoll[] validRolls = new SushiRoll[length];

        for (int n = 0; n < rolls.length; n++) {
            if (rolls[n] != null) {
                for (int j = 0; j < validRolls.length; j++) {
                    if (validRolls[j] == null) {
                        validRolls[j] = rolls[n];
                    }
                }
            }
        }

        return validRolls;

    }

    private static void platesHelper(SushiRoll[] rolls, String color, int n) {
        if (n == rolls.length) {
            return;
        } else {
            if (!rolls[n].getColor().equals(color)) {
                rolls[n] = null;
            }
            n++;
            platesHelper(rolls, color, n);
        }
    }

    public static double totalPrice(SushiRoll[] rolls) {
        return priceHelper(rolls, 0);
    }

    private static double priceHelper(SushiRoll[] rolls, int n) {
        if (n == rolls.length) {
            return 0.0;
        }

        double price;

        if (rolls[0].getColor().equals("Red")) {
            price = 7.0;
        } else if (rolls[0].getColor().equals("Blue")) {
            price = 6.0;
        } else {
            price = 6.5;
        }

        return price + priceHelper(rolls, ++n);
    }

    public static void flip(SushiRoll[] rolls) {
        flipHelper(rolls, 0, rolls.length - 1);
    }

    private static void flipHelper(SushiRoll[] rolls, int n, int j) {
        if (n < j) {
            SushiRoll temp = rolls[n];
            rolls[n++] = rolls[j];
            rolls[j--] = temp;
            flipHelper(rolls, n, j);
        }
    }
}