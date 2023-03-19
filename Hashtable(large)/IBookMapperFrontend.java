import java.util.List;

public interface IBookMapperFrontend {

    /**
     * The constructor that the implementation this interface will
     * provide. It takes the Scanner that will read user input as
     * a parameter as well as the backend and the ISBNnalidator.
     */
    // BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator)

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    public void displayMainMenu(); // prints command options to System.out

    public void displayBooks(List<IBook> books); // displays a list of books

    public void isbnLookup(); // reads word from System.in, displays results

    public void titleSearch(); // reads author name from System.in, displays results

}