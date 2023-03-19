/**
 * Implementations of this interface can be used to validate ISBN numbers.
 */
public interface IISBNValidator {

    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
    public boolean validate(String isbn13);

}