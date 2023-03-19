/**
 * This assignment is to write code that can form palindromes, palindrome is a paragraph of words
 * which reads the same regardless of whether the letters are written forward or backward.
 */
public class Palindrome {

    /**
     * Recursively create a simple alphabet pattern, starting at the provided character, moving
     * backward to the beginning of the alphabet, and then forward again to the provided letter,
     * separating each letter with a space.
     *
     * @param start: this letter that appears at the beginning of a palindrome and reappears at the
     *               end.
     * @return: return a value of mirrorA.
     * @throws IllegalArgumentException: when invalid arguments given then it will raise illegal
     *                                   argument exception.
     */
    public static String mirrorA(char start) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z') {
            throw new IllegalArgumentException("Please enter a correct capital letter");
        } else {
            if (start == 'A') {
                return start + "";
            } else {
                return start + " " + mirrorA((char) (start - 1)) + " " + start;
            }
        }
    }

    /**
     * Recursively create an alphabet pattern, starting at the provided character, and moving back and
     * forth to the beginning of the alphabet by steps of size step.
     *
     * @param start: this letter that appears at the beginning of a palindrome and reappears at the
     *               end.
     * @param step:  the value of the different between each adjacent symbol.
     * @return return a value of mirrorA.
     * @throws IllegalArgumentException: when invalid arguments given then it will raise illegal
     *                                   argument exception.
     */
    public static String mirrorA(char start, int step) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z' || step <= 0) {
            throw new IllegalArgumentException("Please enter a correct capital letter!");
        }
        if (start == 'A') {
            return start + "";
        }
        if (start - step >= 'A') {
            return start + " " + mirrorA((char) (start - step), step) + " " + start;
        } else {
            return start + " " + start;
        }
    }

    /**
     * Recursively create a simple alphabet pattern, starting the provided character, and moving
     * forward to the end of the alphabet, and then backward again to the provided letter, separating
     * each letter with a space.
     *
     * @param start: this letter that appears at the beginning of a palindrome and reappears at the
     *               end.
     * @return return a value of mirrorZ.
     * @throws IllegalArgumentException: when invalid arguments given then it will raise illegal
     *                                   argument exception.
     */
    public static String mirrorZ(char start) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z') {
            throw new IllegalArgumentException("Please enter a correct capital letter");
        } else {
            if (start == 'Z') {
                return start + "";
            } else {
                return start + " " + mirrorZ((char) (start + 1)) + " " + start;
            }
        }
    }

    /**
     * Recursively create an alphabet pattern, starting at the provided character, and moving forward
     * and back to the end of the alphabet by steps of size step.
     *
     * @param start: this letter that appears at the beginning of a palindrome and reappears at the
     *               end.
     * @param step:  the value of the different between each adjacent symbol.
     * @return return a value of mirrorZ.
     * @throws IllegalArgumentException: when invalid arguments given then it will raise illegal
     *                                   argument exception.
     */
    public static String mirrorZ(char start, int step) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z'|| step <= 0) {
            throw new IllegalArgumentException("Please enter a correct capital letter!");
        }
        if (start == 'Z') {
            return start + "";
        }
        if (start + step <= 'Z') {
            return start + " " + mirrorZ((char) (start + step), step) + " " + start;
        } else {
            return start + " " + start;
        }
    }
}
