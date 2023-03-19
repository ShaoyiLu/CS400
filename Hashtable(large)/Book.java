/**
 * this class represents a IBook object and its corresponding data attributes, which are title, author and ISBN13
 */
public class Book implements IBook{

    //data attributes
    private String title;
    private String author;
    private String ISBN13;

    /**
     * Constructor of the IBook with no parameter.
     */
    Book() {
        this.title = null;
        this.author = null;
        this.ISBN13 = null;
    }

    /**
     * Constructor of the IBook.
     *
     * @param title: title of the book; author: author of the book; ISBN13: ISBN number of the book
     */
    Book(String title, String author, String ISBN13) {
        this.title = title;
        this.author = author;
        this.ISBN13 = ISBN13;
    }

    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string that contains the authors of the book as a single string with different authors separated by /.
     *
     * @return author names as single string
     */
    @Override
    public String getAuthors() {
        return author;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13() {
        return ISBN13;
    }

}
