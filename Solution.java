import java.io.*;
import java.util.*;

public class Solution {

    // 0 Alvin
    // 1 Bob
    // 2 Ellen
    // 3 Enrico
    // 4 Gayus
    // 5 Gihon
    // 6 Hady
    // 7 Hendry
    // 8 Judo
    // 9 Kezia
    // 10 Peter
    // 11 Steph
    // 12 Theresa
    // 13 Weni

    // Car 0 Innova
    // Car 1 Avanza

    static abstract class Constraint {
        public Constraint(int f, int s) {
            this.first = f;
            this.second = s;
        }

        protected int first;
        protected int second;

        public abstract boolean violated(Set<Integer> first, Set<Integer> second);
    }

    static class ConstraintEqual extends Constraint {
        public ConstraintEqual(int f, int s) {
            super(f, s);
        }

        @Override
        public boolean violated(Set<Integer> first, Set<Integer> second) {
            if (first.contains(this.first)) {
                if (first.contains(this.second)) {
                    return false;
                } else {
                    return true;
                }
            } else if (second.contains(this.first)) {
                if (second.contains(this.second)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    static class ConstraintNotEqual extends Constraint {
        public ConstraintNotEqual(int f, int s) {
            super(f, s);
        }

        @Override
        public boolean violated(Set<Integer> first, Set<Integer> second) {
            if (first.contains(this.first)) {
                if (first.contains(this.second)) {
                    return true;
                } else {
                    return false;
                }
            } else if (second.contains(this.first)) {
                if (second.contains(this.second)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    static class ConstraintMustBeIn extends Constraint {
        public ConstraintMustBeIn(int f, int s) {
            super(f, s);
        }

        @Override
        public boolean violated(Set<Integer> first, Set<Integer> second) {
            if (this.second == 0) {
                if (first.contains(this.first)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (second.contains(this.first)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    static class ConstraintMustHaveOneDriver extends Constraint {
        public ConstraintMustHaveOneDriver(int f, int s) {
            super(f, s);
        }

        @Override
        public boolean violated(Set<Integer> first, Set<Integer> second) {
            boolean result = true;
            for (Integer driver : drivers) {
                if (this.first == 0 && first.contains(driver)) {
                    result = false;
                }
                if (this.first == 1 && second.contains(driver)) {
                    result = false;
                }
            }
            if (this.first == 0 && first.size() > 7) {
                return true;
            }
            if (this.first == 1 && second.size() > 7) {
                return true;
            }
            return result;
        }
    }

    static List<Constraint> constraints;

    static List<Integer> drivers;

    static int validate(Set<Integer> first, Set<Integer> second) {
        int violation = 0;
        for (Constraint constraint : constraints) {
            if (constraint.violated(first, second)) {
                violation += 1;
            }
        }
        return violation;
    }

    static void fillConstraint() {
        constraints = new ArrayList<Constraint>();
        drivers = new ArrayList<Integer>();

        drivers.add(5);
        drivers.add(7);
        drivers.add(8);
        drivers.add(10);

        constraints.add(new ConstraintNotEqual(1, 4));
        constraints.add(new ConstraintNotEqual(0, 1));

        constraints.add(new ConstraintEqual(3, 12));
        constraints.add(new ConstraintEqual(5, 11));
        constraints.add(new ConstraintEqual(8, 9));
        constraints.add(new ConstraintEqual(10, 13));

        constraints.add(new ConstraintMustBeIn(1, 0));
        constraints.add(new ConstraintMustBeIn(3, 0));
        constraints.add(new ConstraintMustBeIn(4, 1));
        constraints.add(new ConstraintMustBeIn(8, 1));

        constraints.add(new ConstraintMustHaveOneDriver(0, 0));
        constraints.add(new ConstraintMustHaveOneDriver(1, 0));
    }

    static int recurse(int violations, Set<Integer> added, Set<Integer> notAdded, Set<Integer> first,
            Set<Integer> second) {
        int result = validate(first, second);
        if (result == 0 || result >= violations) {
            return result;
        }
        for (int i = 0; i < 14; i++) {
            if (added.contains(i)) {
                continue;
            }

            added.add(i);
            notAdded.remove(i);

            // Try add to first
            first.add(i);
            int tryFirst = recurse(result, added, notAdded, first, second);
            if (tryFirst == 0) {
                result = 0;
                break;
            }
            first.remove(i);

            // Try add to second
            second.add(i);
            int trySecond = recurse(result, added, notAdded, first, second);
            if (trySecond == 0) {
                result = 0;
                break;
            }
            second.remove(i);

            added.remove(i);
            notAdded.add(i);
        }
        return result;
    }

    static void print(String label, Set<Integer> set) {
        System.out.print(label + " : ");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static String label(int val) {
        switch (val) {
        case 0:
            return "Alvin";
        case 1:
            return "Bob";
        case 2:
            return "Ellen";
        case 3:
            return "Enrico";
        case 4:
            return "Gayus";
        case 5:
            return "Gihon";
        case 6:
            return "Hady";
        case 7:
            return "Hendry";
        case 8:
            return "Judo";
        case 9:
            return "Kezia";
        case 10:
            return "Peter";
        case 11:
            return "Steph";
        case 12:
            return "Theresa";
        case 13:
            return "Weni";
        default:
            return "?";
        }
    }

    static void solve() {
        Set<Integer> added = new HashSet<Integer>();
        Set<Integer> notAdded = new HashSet<Integer>();
        Set<Integer> first = new HashSet<Integer>();
        Set<Integer> second = new HashSet<Integer>();

        for (int i = 0; i < 14; i++) {
            notAdded.add(i);
        }

        int initialViolations = validate(first, second);
        recurse(initialViolations + 1, added, notAdded, first, second);
        print("First  : ", first);
        print("Second : ", second);
        print("Free   : ", notAdded);
        System.out.println();
        System.out.println("Innova :");
        for (int person : first) {
            System.out.println("- " + label(person));
        }
        System.out.println("Avanza :");
        for (int person : second) {
            System.out.println("- " + label(person));
        }
        System.out.println("Free :");
        for (int person : notAdded) {
            System.out.println("- " + label(person));
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        // String lines = br.readLine();
        // String[] tokens = lines.trim().split("\\s+");

        // int a = Integer.parseInt(tokens[0]);
        // int b = Integer.parseInt(tokens[1]);

        // System.out.println(a + b);
        fillConstraint();
        solve();
        long elapsed = System.nanoTime() - start;
        System.out.println(elapsed / 1000000);
    }
}