// --== CS400 Project One File Header ==--
// Name: Shaoyi Lu
// CSL Username: shaoyi
// Email: slu248@wisc.edu
// Lecture #: 001
// Notes to Grader: none

import java.util.*;

public class BookMapperBackend implements IBookMapperBackend{
    private int size = 0;
    protected String filter = "";
    private List<IBook> filteredlist = new ArrayList<IBook>();
    private IterableHashtableMap<String, IBook> bookTableISBN = new IterableHashtableMap<String, IBook>();

    /**
     * Adds a new book to the backend's database and is stored in
     * a hash table internally.
     *
     * @param book the book to add
     */
    @Override
    public void addBook(IBook book) {
        bookTableISBN.put(book.getISBN13(), book);
        size += 1;

    }

    /**
     * Returns the number of books stored in the backend's database.
     *
     * @return the number of books
     */
    @Override
    public int getNumberOfBooks() {
        return size;
    }

    /**
     * This method can be used to set a filter for the author names
     * contained in the search results. A book is only returned as
     * a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     *
     * @param filterBy the string that the book's author names must contain
     */
    @Override
    public void setAuthorFilter(String filterBy) {

        filter=filterBy;
        bookTableISBN.iterator();
        for (IBook author : bookTableISBN) {
            String s1 = author.getAuthors().replace(" ","").toUpperCase();
            String s2 = filterBy.replace(" ","").toUpperCase();
            if(s1.contains(s2)) {
                filteredlist.add(author);
            }
        }

    }

    /**
     * Returns the string used as the author filter, null if no author
     * filter is currently set.
     *
     * @return the string used as the author filter, or null if none is set
     */
    @Override
    public String getAuthorFilter() {
        return filter;
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetAuthorFilter() {
        this.filter = "";
    }

    /**
     * Search through all the books in the title base and return books whose
     * title contains the string word (and that satisfies the author filter,
     * if an author filter is set).
     *
     * @param word word that must be contained in a book's title in result set
     * @return list of books found
     */

    @Override
    public List<IBook> searchByTitleWord(String word){

        List<IBook> allBook = new ArrayList<IBook>();

        if(filter != "") {


            if(word == "") {
                for(IBook mybook: filteredlist) {
                    allBook.add(mybook);
                }
                return allBook;
            }

            for(IBook mybook : filteredlist) {
                String s1 = mybook.getTitle().replace(" ","").toUpperCase();
                String s2 = word.replace(" ","").toUpperCase();

                if(s1.contains(s2)) {
                    allBook.add(mybook);
                }
            }

            return allBook;
        }

        bookTableISBN.iterator();


        if(word == "") {
            for(IBook mybook: bookTableISBN) {
                allBook.add(mybook);
            }
            return allBook;
        }

        for(IBook mybook: bookTableISBN) {
            String s1 = mybook.getTitle().replace(" ","").toUpperCase();
            String s2 = word.replace(" ","").toUpperCase();

            if(s1.contains(s2)) {
                allBook.add(mybook);
            }
        }

        return allBook;

    }

    /**
     * Return the book uniquely identified by the ISBN, or null if ISBN is not
     * present in the dataset.
     *
     * @param ISBN the book's ISBN number
     * @return the book identified by the ISBN, or null if ISBN not in database
     */
    @Override
    public IBook getByISBN(String ISBN) {
        IBook theBook = new Book();

        if(filter != "") {
            for(IBook mybook : filteredlist) {
                if(mybook.getISBN13().equals(ISBN)) {
                    theBook = mybook;
                }
            }

            return theBook;
        }

        if(bookTableISBN.containsKey(ISBN)) {
            theBook = bookTableISBN.get(ISBN);

            return theBook;
        }

        return theBook;
    }

}
