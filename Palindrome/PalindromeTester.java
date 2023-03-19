//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Palindrome
// Course: CS 300 Summer 2022
//
// Author: Shaoyi Lu
// Email: slu248@wisc.edu
// Lecturer: Michelle Jensen
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////

/**
 * These methods should test valid and invalid input against expected results, and not throw
 * exceptions.
 *
 * @author Shaoyi Lu
 *
 */
public class PalindromeTester {

    /**
     * Create a tester with exception to test the mirrorA() method
     *
     * @return: return true or false according entered characters meet requirements.
     */
    public static boolean testMirrorA() {
        try {
            if (!Palindrome.mirrorA('E').equals("E D C B A B C D E")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            String enter = Palindrome.mirrorA('e');
            return false;
        } catch (IllegalArgumentException e) {

        }

        try {
            String enter = Palindrome.mirrorA('8');
        } catch (IllegalArgumentException e) {
            return true;
        }

        try {
            String enter = Palindrome.mirrorA('%');
        } catch (IllegalArgumentException e) {
            return true;
        }

        return true;
    }

    /**
     * Create a tester with exception to test the mirrorA() with step method
     *
     * @return: return true or false according entered characters meet requirements.
     */
    public static boolean testMirrorAStep() {
        try {
            if (!Palindrome.mirrorA('E', 2).equals("E C A C E")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            if (!Palindrome.mirrorA('E', 3).equals("E B B E")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            String enter = Palindrome.mirrorA('e', 3);
        } catch (IllegalArgumentException e) {
            return true;
        }

        try {
            String enter = Palindrome.mirrorA('E', -3);
        } catch (IllegalArgumentException e) {
            return true;
        }

        try {
            String enter = Palindrome.mirrorA('#', 3);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return true;
    }

    /**
     * Create a tester with exception to test the mirrorZ() method
     *
     * @return: return true or false according entered characters meet requirements.
     */
    public static boolean testMirrorZ() {
        try {
            if (!Palindrome.mirrorZ('V').equals("V W X Y Z Y X W V")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            String enter = Palindrome.mirrorZ('v');
        } catch (IllegalArgumentException e) {
            return true;
        }

        try {
            String enter = Palindrome.mirrorZ('0');
        } catch (IllegalArgumentException e) {
            return true;
        }

        try {
            String enter = Palindrome.mirrorZ('$');
        } catch (IllegalArgumentException e) {
            return true;
        }

        return true;
    }

    /**
     * Create a tester with exception to test the mirrorZ() with step method
     *
     * @return: return true or false according entered characters meet requirements.
     */
    public static boolean testMirrorZStep() {
        try {
            if (!Palindrome.mirrorZ('V', 1).equals("V W X Y Z Y X W V")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            if (!Palindrome.mirrorZ('V', 2).equals("V X Z X V")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            if (!Palindrome.mirrorZ('V', 3).equals("V Y Y V")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            String enter = Palindrome.mirrorZ('v', 3);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return true;
    }

    /**
     * This method must call ALL of your test methods and return true if and only if all methods
     * return true. If you add additional methods besides the four listed above, be sure to call them
     * here.
     *
     * @return return true when MirrorA and MirrorZ all correct.
     */
    public static boolean runAllTest() {
        return testMirrorA() && testMirrorZ() && testMirrorAStep() && testMirrorZStep();
    }

    /**
     * The only line in this method should be a call to the runAllTests method.
     */
    public static void main(String[] args) {
        System.out.println(runAllTest());
    }
}