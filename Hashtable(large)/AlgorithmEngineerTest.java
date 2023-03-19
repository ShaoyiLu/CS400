/**
 * Tests the functionality of IterableHashtableMap and IISBNChecker classes
 *
 * @author Michael Song
 *
 */
public class AlgorithmEngineerTest {
    private static IterableHashtableMap<Integer, String> hashtable;

    // Tests the validate method of the IISBNChecker class
    public static boolean test1() {
        ISBNValidator number = new ISBNValidator();
        String test = "978-0-306-40615-7"; // test with a valid ISBN13 number
        try {
            if (number.validate(test) != true) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        test = ""; // test with a blank number
        try {
            if (number.validate(test) != false) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        test = null; // test when string is null
        try {
            if (number.validate(test) != false) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        test = "978-0-306-40615-4"; // test with invalid ISBN13 number
        try {
            if (number.validate(test) != false) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Tests basic functionality of next() method in IterableHashtableMap class
    public static boolean test2() {
        hashtable = new IterableHashtableMap<Integer, String>();
        hashtable.put(0, "a");
        hashtable.put(1, "b");
        hashtable.put(2, "c");

        hashtable.iterator();
        try {
            for (String value : hashtable) {
                if (hashtable.iterator().next() != "a") {
                    return false;
                }
                if (hashtable.iterator().next() != "b") {
                    return false;
                }
                if (hashtable.iterator().next() != "c") {
                    return false;
                }
                try {
                    hashtable.iterator().next();
                    return false;
                } catch (Exception e) {
                    // expected exception caught
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Tests the basic functionality of hasNext() method in IterableHashtableMap class
    public static boolean test3() {
        hashtable = new IterableHashtableMap<Integer, String>();
        hashtable.put(0, "a");
        hashtable.put(1, "b");
        hashtable.put(2, "c");

        hashtable.iterator();
        try {
            if (!hashtable.iterator().hasNext()) {
                return false;
            }
            hashtable.iterator().next();
            if (!hashtable.iterator().hasNext()) {
                return false;
            }
            hashtable.iterator().next();
            if (!hashtable.iterator().hasNext()) {
                return false;
            }
            if (hashtable.iterator().hasNext()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Tests hasNext() method for a variety of cases
    public static boolean test4() {
        hashtable = new IterableHashtableMap<Integer, String>();
        hashtable.put(2, null);
        hashtable.iterator();
        try {
            if (hashtable.iterator().hasNext()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        hashtable.put(0, "a");
        hashtable.put(1, null);

        hashtable.iterator();
        try {
            if (!hashtable.iterator().hasNext()) {
                return false;
            }
            hashtable.iterator().next();
            if (hashtable.iterator().hasNext()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        hashtable.remove(1);
        hashtable.remove(0);
        hashtable.remove(2);
        if (hashtable.iterator().hasNext()) {
            return false;
        }
        return true;
    }

    // Tests next() method for a variety of cases
    public static boolean test5() {
        hashtable = new IterableHashtableMap<Integer, String>();
        hashtable.put(1, "p");
        hashtable.put(2, null);
        hashtable.iterator();

        try {
            hashtable.iterator().next();
            hashtable.iterator().next();
            return false;
        }
        catch (Exception e) {
            //expected exception caught
        }

        hashtable.remove(1);
        hashtable.remove(2);
        try {
            hashtable.iterator().next();
            return false;
        }
        catch (Exception e) {
            //expected exception caught
        }
        hashtable.put(1, "a");
        try {
            hashtable.iterator().next();
            hashtable.iterator().next();
            return false;
        }
        catch (Exception e) {
            //expected exception caught
        }

        hashtable.put(2,"b");
        try {
            if (!hashtable.iterator().next().equals("b")) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        if (test1() && test2() && test3() && test4() && test5()) {
            System.out.println("All tests have passed!");
        }
    }
}