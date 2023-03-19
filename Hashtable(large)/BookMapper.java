// --== CS400 Project One File Header ==--
// Name: Shaoyi Lu
// CSL Username: shaoyi
// Email: slu248@wisc.edu
// Lecture #: 001
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper{

    public static void main(String[] args) throws FileNotFoundException {
        BookLoader bookLoader = new BookLoader();
        // load the books from the data file

        List<IBook> bookList = bookLoader.loadBooks("books.csv");
        // instantiate the backend

        IBookMapperBackend backend = new BookMapperBackend();
        // add all the books to the backend
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        // instantiate the isbn validator

        ISBNValidator isbnValidator = new ISBNValidator();
        // instantiate the scanner for user input

        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it

        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();
    }

}