// --== CS400 Project One File Header ==--
// Name: Yingjie Dai
// CSL Username: ydai
// Email: ydai69@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class generate the Book map frontend function.
 */
public class BookMapperFrontend implements IBookMapperFrontend {

    //initialize scanner, backend, and validator
    Scanner userInputScanner;
    IBookMapperBackend backend;
    ISBNValidator validator;


    /**
     * The constructor that the implementation this interface will provide. It takes the Scanner
     * that will read user input as a parameter as well as the backend and the ISBNnalidator.
     *
     * @param userInputScanner scanner to get user input
     * @param backend          book mapper's backend method
     * @param validator        class to check isbn is correct
     */
    public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend,
                              ISBNValidator validator) {

        //create new backend, scanner, and validator
        this.userInputScanner = userInputScanner;
        this.backend = backend;
        this.validator = validator;
    }

    /**
     * This method starts the command loop for the frontend, and will terminate when the user exists
     * the app.
     */
    @Override
    public void runCommandLoop() {

        //display window header and main menu for application
        System.out.println("Welcome to the Book Mapper Application!");
        System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
        System.out.println();
        //show the main menu
        displayMainMenu();

        //get user command and turn it into integer
        String userInput;
        if (userInputScanner.hasNextInt()) {
            userInput = userInputScanner.next();
        } else {
            System.out.println("not a number, problem failed");
            userInput = null;
        }
        userInputScanner.nextLine();
        int command = Integer.parseInt(userInput);

        //loop through the main window to get every command
        while (command != 4) {
            //generate to look up isbn for user
            if (command == 1) {
                isbnLookup();
                //generate title search
            } else if (command == 2) {
                titleSearch();
                //generate author filter
            } else if (command == 3) {
                authorFilter();
            }

            //after finish command, generate main menu again
            displayMainMenu();
            //get the next command
            userInput = userInputScanner.next();
            userInputScanner.nextLine();
            command = Integer.parseInt(userInput);

        }

        //if user enter 4, exit and print goodbye
        System.out.println();
        System.out.println("Goodbye!");

    }

    // to help make it easier to test the functionality of this program,
    // the following helper methods will help support runCommandLoop():

    /**
     * This method display main menu for the program
     */
    @Override
    public void displayMainMenu() {

        //print out all the main menu
        System.out.println("You are in the Main Menu:");
        System.out.println("          1) Lookup ISBN");
        System.out.println("          2) Search by Title Word");
        System.out.println("          3) Set Author Name Filter");
        System.out.println("          4) Exit Application");

    }

    /**
     * This method generate isbn lookup method for users
     */
    @Override
    public void isbnLookup() {

        //print user hint to get input
        System.out.println("You are in the Lookup ISBN Menu:");
        System.out.println("          Enter ISBN to look up:");
        //get user input
        String ISBN = userInputScanner.nextLine();
        ISBN =ISBN.replaceAll(" ", "");

        //check if input is valid
        if (validator.validate(ISBN)) {
            //if valid, print the book
            IBook resultBook = backend.getByISBN(ISBN);
            //System.out.println();
            System.out.println("1. " + bookSummary(resultBook));
        } else {
            //if not valid, print invalid
            System.out.println("ISBN is invalid");
        }

    }

    /**
     * This method generate book summary from each book
     *
     * @param resultBook book that need to be print
     * @return a string of one book summary
     */
    public String bookSummary(IBook resultBook) {

        //get isbn, title, and author
        String ISBN = resultBook.getISBN13();
        String title = resultBook.getTitle();
        String author = resultBook.getAuthors();
        //combine it together
        String summary = "\"" + title + "\" by " + author + ", ISBN: " + ISBN;

        //return the result string
        return summary;
    }

    /**
     * This method search the book by user input title
     */
    @Override
    public void titleSearch() {

        //print the search menu:
        System.out.println("You are in the Search for Title Word Menu: ");
        System.out.println("          Enter a word to search for in book " +
                "titles (empty for " + "all books): ");

        //get user input
        String title = userInputScanner.nextLine();

        //check if title is empty
        String spaceTitle = title.replaceAll(" ", "");
        if (spaceTitle.equals("")) {
            title = " ";
        }

        //check title, and print all the books if user enter nothing
        if (title == " " || title == null) {

            //load book form data and print all whole book
            List<IBook> wholeBooks = backend.searchByTitleWord("");

            //get number of all books
            if (wholeBooks != null) {
                int wholeBookSize = wholeBooks.size();
                System.out.println("Matches " + wholeBookSize + " of " + wholeBookSize);
                //print all books
                displayBooks(wholeBooks);
            }

            //if there is a title, find the book use backend method
        } else {
            //find specific book
            List<IBook> listResult = backend.searchByTitleWord(title);

            //check author exist a filter or not, and add to the output
            String authorFilter;
            if (backend.getAuthorFilter() == null) {
                authorFilter = "(any author) ";
            } else {
                authorFilter = "(" + backend.getAuthorFilter() + ") ";
            }

            //get number of all books
            int wholeBookSize = backend.getNumberOfBooks();
            System.out.println("Matches " + authorFilter + listResult.size() + " of 11124");
            //print all the list book
            displayBooks(listResult);
            System.out.println("Matches " + authorFilter + listResult.size() + " of 11124");
        }
    }


    /**
     * This method set the author filter from user input
     */
    public void authorFilter() {

        //get author filter for current backend
        String authorFilter = backend.getAuthorFilter();
        if (authorFilter == null) {
            authorFilter = "none";
        }

        //print the set author menu
        System.out.println("You are in the Set Author Filter Menu: ");
        System.out.println("          Author name must currently contain: " + authorFilter);
        System.out.println("          Enter a new string for author names " + "to contain (empty "
                + "for any): ");

        //get user input
        String name;
        if (userInputScanner.hasNextLine()) {
            name = userInputScanner.nextLine();
        } else {
            name = null;
        }

        //check if there is a author filter
        if (backend.getAuthorFilter() != null) {
            backend.resetAuthorFilter();
        }
        backend.setAuthorFilter(name); //set user input to the filter

    }

    /**
     * This method display each book in the list
     *
     * @param books list of books that need to be displayed
     */
    @Override
    public void displayBooks(List<IBook> books) {
        int bookCount = 1;
        //loop through each book in the book list
        for (IBook book : books) {
            System.out.println(bookCount + ". " + bookSummary(book));
            System.out.println();
            bookCount++;
        }
    }
}