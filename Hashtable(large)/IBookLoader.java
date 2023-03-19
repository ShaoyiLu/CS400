import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to load book data from a CSV file.
 */
public interface IBookLoader {

    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException;

}