public class ISBNValidator implements IISBNValidator {

    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     *
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
    @Override
    public boolean validate(String isbn13) {
        if (isbn13.equals("") || isbn13 == null) {
            return false;
        }

        isbn13 = isbn13.replaceAll("-", "");
        isbn13 = isbn13.replaceAll(" ", "");

        if(isbn13.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int curr = Integer.parseInt(isbn13.substring(i, i + 1));
            sum += (i % 2 == 0) ? curr * 1 : curr * 3;
        }
        int checkDigit = (10 - (sum % 10));
        if (checkDigit == 10) {
            checkDigit = 0;
        }
        return (checkDigit == Integer.parseInt(isbn13.substring(12)));
    }
}
