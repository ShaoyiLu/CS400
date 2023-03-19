/**
 * This interface defines getter methods for each book's data attributes
 * and is implemented by classes that represent a book and its associated
 * data.
 */
public interface IBook {

    /**
     * Returns the title of the book.
     * @return title of the book
     */
    String getTitle();

    /**
     * Returns a string that contains the authors of the book
     * as a single string with different authors separated by /.
     * @return author names as single string
     */
    String getAuthors();

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     * @return ISBN number of book
     */
    String getISBN13();

}