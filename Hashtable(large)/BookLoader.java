import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;


/**
 * this class is used to load book data from a CSV file.
 */
public class BookLoader implements IBookLoader {

    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException{

        List<IBook> myList = new ArrayList<IBook>();
        BufferedReader csvReader = null;

        String line = null;
        int indexOfTitle = 0;
        int indexOfAuthors = 0;
        int indexOfISBN13 = 0;

        File file = new File(filepathToCSV);
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        try {

            csvReader = new BufferedReader(new FileReader(filepathToCSV, StandardCharsets.UTF_8));

            if((line=csvReader.readLine()) != null) {
                String [] item = line.split(",");
                for(int i = 0; i < item.length; i++) {
                    if(item[i].equals("title")) {
                        indexOfTitle = i;
                    }
                    if(item[i].equals("authors")) {
                        indexOfAuthors = i;
                    }
                    if(item[i].equals("isbn13")) {
                        indexOfISBN13 = i;
                    }
                }

            }

            while ((line=csvReader.readLine()) != null){
                String [] item = line.split(",");
                IBook currentBook = new Book(item[indexOfTitle],item[indexOfAuthors],item[indexOfISBN13]);
                myList.add(currentBook);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return myList;

    }

}