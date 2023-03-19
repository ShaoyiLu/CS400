// --== CS400 Project One File Header ==--
// Name: Shaoyi Lu
// CSL Username: shaoyi
// Email: slu248@wisc.edu
// Lecture #: 001
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.List;

public class BackendDeveloperTest{

    /**
     * tester for method addBook()
     *
     * @return true if this method is correct
     */
    public static boolean test1(){
        BookMapperBackend addBook = new BookMapperBackend();

        Book book1 = new Book("Treasure Island", "Robert Louis Stevenson", "9780753453803");
        addBook.addBook(book1);
        if(addBook.getNumberOfBooks() != 1){
            return false;
        }

        Book book2 = new Book("The Known World", "Edward P. Jones", "9780061159176");
        addBook.addBook(book2);
        if(addBook.getNumberOfBooks() != 2){
            return false;
        }

        Book book3 = new Book("In a Sunburned Country", "Bill Bryson", "9780767903868");
        addBook.addBook(book3);
        if(addBook.getNumberOfBooks() != 3){
            return false;
        }

        return true;
    }

    /**
     * tester for method bookSize()
     *
     * @return true if this method is correct
     */
    public static boolean test2(){
        BookMapperBackend bookSize = new BookMapperBackend();

        Book book1 = new Book("The Changeling","Kate Horsley","9781590301944");
        bookSize.addBook(book1);
        if(bookSize.getNumberOfBooks() != 1){
            return false;
        }

        Book book2 = new Book("Uncommon Carriers", "John McPhee", "9780374280390");
        bookSize.addBook(book2);
        if(bookSize.getNumberOfBooks() != 2){
            return false;
        }

        Book book3 = new Book("Shout Out Loud! 5", "Satosumi Takaguchi", "9781598163209");
        Book book4 = new Book("The Arabian Nightmare", "Robert Irwin", "9781585672172");
        bookSize.addBook(book3);
        bookSize.addBook(book4);
        if(bookSize.getNumberOfBooks() != 4){
            return false;
        }

        return true;
    }

    /**
     * tester for method setAuthorFilter()
     *
     * @return true if this method is correct
     */
    public static boolean test3(){
        BookMapperBackend setAuthor = new BookMapperBackend();

        setAuthor.setAuthorFilter("Russell Banks");
        if(!setAuthor.filter.equals("Russell Banks")){
            return false;
        }

        setAuthor.setAuthorFilter("Tim Downs");
        if(setAuthor.filter.equals("Tim Downs Tim")){
            return false;
        }

        setAuthor.setAuthorFilter("Henry Chadwick");
        if(setAuthor.filter.equals("henry chadwick")){
            return false;
        }

        return true;
    }

    /**
     * tester for method getAuthorFilter()
     *
     * @return true if this method is correct
     */
    public static boolean test4(){
        BookMapperBackend getAuthor = new BookMapperBackend();

        getAuthor.filter = "Phillip Lopate";
        if(!getAuthor.getAuthorFilter().equals("Phillip Lopate")){
            return false;
        }

        getAuthor.filter = "John Hersey";
        if(getAuthor.getAuthorFilter().equals("Hersey John")){
            return false;
        }

        getAuthor.filter = "Jonathan Harr";
        if(getAuthor.getAuthorFilter().equals("JonathanHarr")){
            return false;
        }

        return true;
    }

    /**
     * tester for method resetAuthorFilter()
     *
     * @return true if this method is correct
     */
    public static boolean test5(){
        BookMapperBackend resetAuthor = new BookMapperBackend();

        Book book = new Book("The Commanders","Bob Woodward","9780743234757");
        resetAuthor.addBook(book);
        Book book2 = new Book("Mortals","Norman Rush","9780679737117");
        resetAuthor.addBook(book2);
        Book book3 = new Book("Touch Not the Cat", "Mary  Stewart", "9780060823726");
        resetAuthor.addBook(book3);

        resetAuthor.resetAuthorFilter();
        if (resetAuthor.filter != ""){
            return false;
        }

        return true;
    }

    public static boolean test6(){
        BookLoader loader = new BookLoader();
        try {
            List<IBook> list = loader.loadBooks("books.csv");
            BookMapperBackend BD = new BookMapperBackend();
            for (IBook book: list){
                BD.addBook(book);
            }
            if (list.size() != BD.getNumberOfBooks()){
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean test7(){
        BookLoader loader2 = new BookLoader();
        try {
            List<IBook> bookList = loader2.loadBooks("books.csv");
            BookMapperBackend BD2 = new BookMapperBackend();
            for (IBook book: bookList){
                BD2.addBook(book);
            }
            List<IBook> search = BD2.searchByTitleWord("Anthem");
            for (IBook book : search){
                if (!book.getTitle().contains("Anthem")){
                    return false;
                }
            }
            ISBNValidator validator = new ISBNValidator();
            boolean x = validator.validate("3336669990");
            if (x != false){
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean test8(){
        Book firstBook = new Book("User", "Blake Nelson", "9780970481719");
        if (firstBook.getTitle() != "User"){
            return false;
        }
        if (firstBook.getAuthors() != "Blake Nelson"){
            return false;
        }
        if (firstBook.getISBN13() != "9780970481719"){
            return false;
        }

        return true;
    }
    public static boolean test9(){
        Book nextBook = new Book("User", "Blake Nelson", "9780970481719");
        if (nextBook.getTitle() == null || nextBook.getTitle() == ""){
            return false;
        }
        if (nextBook.getAuthors() == null || nextBook.getAuthors() == ""){
            return false;
        }
        if (nextBook.getISBN13() == null || nextBook.getISBN13() == ""){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        if (test1()){
            System.out.println("Backend Developer Individual Test 1: passed");
        }else{
            System.out.println("Backend Developer Individual Test 1: failed");
        }
        if (test2()){
            System.out.println("Backend Developer Individual Test 2: passed");
        }else{
            System.out.println("Backend Developer Individual Test 2: failed");
        }
        if (test3()){
            System.out.println("Backend Developer Individual Test 3: passed");
        }else{
            System.out.println("Backend Developer Individual Test 3: failed");
        }
        if (test4()){
            System.out.println("Backend Developer Individual Test 4: passed");
        }else{
            System.out.println("Backend Developer Individual Test 4: failed");
        }
        if (test5()){
            System.out.println("Backend Developer Individual Test 5: passed");
        }else{
            System.out.println("Backend Developer Individual Test 5: failed");
        }
        if (test6()){
            System.out.println("Data Wrangler Integration Test 1: passed");
        }else{
            System.out.println("Data Wrangler Integration Test 1: failed");
        }
        if (test7()){
            System.out.println("Data Wrangler Integration Test 2: passed");
        }else{
            System.out.println("Data Wrangler Integration Test 2: failed");
        }
        if (test8()){
            System.out.println("Data Wrangler Partner Test 1: passed");
        }else{
            System.out.println("Data Wrangler Partner Test 1: failed");
        }
        if (test9()){
            System.out.println("Data Wrangler Partner Test 2: passed");
        }else{
            System.out.println("Data Wrangler Partner Test 2: failed");
        }
    }
}
