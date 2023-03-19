import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this is a test class to test the function of class Book and BookLoader
public class DataWranglerTest {

    /**
     * test the function of getter methods in class Book.
     *
     *
     * @return true if they functions well, otherwise false
     */
    public static boolean test1() {
        Book book1 = new Book("Apple", "Dog", "123456");

        if (!book1.getTitle().equals("Apple")) {
            return false;
        }

        if (!book1.getAuthors().equals("Dog")) {
            return false;
        }

        if (!book1.getISBN13().equals("123456")) {
            return false;
        }

        return true;

    }

    /**
     * test the function of loadBooks in class BookLoader - case1 pass an empty file to it to see it
     * throws FileNotFoundException properly
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean test2() {

        List<IBook> testList = new ArrayList<IBook>();
        BookLoader myLoader = new BookLoader();

        try {
            testList = myLoader.loadBooks("test.csv"); // the test.csv did not exists
        } catch (FileNotFoundException e) {
            return true;

        } catch (Exception e) {
            return false;
        }

        return true;

    }

    /**
     * test the function of loadBooks in class BookLoader - case2 check if the returned list of book
     * stored the correct elements.
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean test3() {
        List<IBook> testList = new ArrayList<IBook>();
        BookLoader myLoader = new BookLoader();
        try {
            testList = myLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        IBook mybook = new Book();
        mybook = testList.get(5);

        if (!mybook.getAuthors().equals("W. Frederick Zimmerman")) {
            return false;
        }

        mybook = testList.get(20);

        if (!mybook.getISBN13().equals("9780380715435")) {
            return false;
        }

        mybook = testList.get(30);

        if (!mybook.getTitle().equals("Guts: The True Stories behind Hatchet and the Brian Books")) {
            return false;
        }


        return true;
    }

    /**
     * test the function of loadBooks in class BookLoader - case3 check if the returned list of book
     * contains the full dataset
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean test4() {

        List<IBook> testList = new ArrayList<IBook>();
        BookLoader myLoader = new BookLoader();
        try {
            testList = myLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        IBook mybook = new Book();
        mybook = testList.get(testList.size() - 1);

        // to see if the last element of the dataset is stored in our list.
        // if so, then it is reasonable to conclude that our list contains all the elements of the
        // dataset.
        if (!mybook.getTitle().equals("Las aventuras de Tom Sawyer")) {
            return false;
        }

        return true;
    }

    /**
     * test the function of loadBooks in class BookLoader - case4 check if the String data attribute
     * stored in returned list of book is UTF-8 encoding
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean test5() {
        List<IBook> testList = new ArrayList<IBook>();
        BookLoader myLoader = new BookLoader();
        byte[] myBytes = null;

        try {
            testList = myLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        IBook mybook = new Book();
        mybook = testList.get(0);

        try {
            myBytes = mybook.getAuthors().getBytes("UTF-8");
        } // it should not throw any exceptions
        catch (UnsupportedEncodingException e) {
            return false;

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * test the function of ISBN search and validation
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean integratedTest1() {

        BookLoader bookLoader = new BookLoader();
        List<IBook> bookList;
        try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        IBookMapperBackend backend = new BookMapperBackend();

        for (IBook book : bookList) {
            backend.addBook(book);
        }

        ISBNValidator isbnValidator = new ISBNValidator();

        if (!backend.getByISBN(bookList.get(2).getISBN13()).equals(bookList.get(2))) {
            return false;
        }

        if (isbnValidator.validate(bookList.get(6).getISBN13()) != true) {
            return false;
        }

        if (isbnValidator.validate("252423423252352253") == true) {
            return false;
        }

        return true;
    }

    /**
     * test the function of title search before and after filtering author
     *
     *
     * @return true if it functions well, otherwise false
     */
    public static boolean integratedTest2() {

        BookLoader bookLoader = new BookLoader();
        List<IBook> bookList;
        try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        IBookMapperBackend backend = new BookMapperBackend();

        for (IBook book : bookList) {
            backend.addBook(book);
        }

        ISBNValidator isbnValidator = new ISBNValidator();

        if (!backend.searchByTitleWord("Azkaban").get(1).getTitle().equals(bookList.get(3).getTitle())) {
            return false;
        }

        backend.setAuthorFilter("W. Frederick Zimmerman");

        if (backend.searchByTitleWord("Azkaban").size() != 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        if (test1()) {
            System.out.println("Data Wrangler Individual Test 1: passed");
        }

        if (test2()) {
            System.out.println("Data Wrangler Individual Test 2: passed");
        }

        if (test3()) {
            System.out.println("Data Wrangler Individual Test 3: passed");
        }

        if (test4()) {
            System.out.println("Data Wrangler Individual Test 4: passed");
        }

        if (test5()) {
            System.out.println("Data Wrangler Individual Test 5: passed");
        }

        if (integratedTest1()) {
            System.out.println("Data Wrangler Integration Test 1: passed");
        }

        if (integratedTest2()) {
            System.out.println("Data Wrangler Integration Test 2: passed");
        }


    }



}