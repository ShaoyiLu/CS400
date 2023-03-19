import java.util.*;

public class TestPlaceholder implements IBookMapperBackend{

    String author = null;

    @Override
    public void addBook(IBook book) {

    }

    @Override
    public int getNumberOfBooks() {
        return 0;
    }

    @Override
    public void setAuthorFilter(String filterBy) {
        author = filterBy;
    }

    @Override
    public String getAuthorFilter() {
        return author;
    }

    @Override
    public void resetAuthorFilter() {
        author = null;
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        IBook book1 = new Book("A Portrait of the Artist as a Young Man",
                "James Joyce/Jim Norton","9780688060695");
        IBook book2 = new Book("Dubliners",
                "James Joyce/Frank McCourt/Donal Donnelly" +
                        "/Ciaran Hinds/Colm Meaney/Malachy McCourt","9780060789565");
        IBook book3 = new Book("A Portrait of the Artist As a Young Man",
                "ichael Davitt/Canon  Doyle/Pádraic Pearse/Ignatius of Loyola/Giovanni " +
                        "Pietro Pinamont/Walter Pater/Oscar Wilde/Douglas Hyde/Kenneth Burke/Umberto " +
                        "Eco/Hugh Kenner/Hélène Cixous/Karen Lawrence/Maud Ellmann/Bonnie Kime Scott/Joseph" +
                        " Valente/Marian Eide/Pericles Lewis/Jonathan Mulrooney/J.M. Synge","9780393926798");
        IBook book4 = new Book("Ulysses",
                "James Joyce/Craig Raine","9780679455134");
        IBook book5 = new Book("A Portrait of the Artist as a Young Man",
                "James Joyce/Seamus Deane","9780142437346");
        IBook book6 = new Book("A Portrait of the Artist as a Young Man",
                "James Joyce/Langdon Hammer","9780451530158");
        IBook book7 = new Book("Dubliners: Text  Criticism  and Notes",
                "Dubliners: Text  Criticism  and Notes","9780140247749");
        IBook book8 = new Book("Dubliners",
                "James Joyce/Jeri Johnson/Thiên Lương","9780192839992");
        IBook book9 = new Book("Finnegans Wake",
                "James Joyce","9780571217359");
        IBook book10 = new Book("Dubliners",
                "James Joyce/Margot Norris/Hans Walter Gabler/Walter Hettche",
                "9780393978513");
        IBook book11 = new Book("The Portable James Joyce",
                "James Joyce/Harry Levin","9780517618875");
        IBook book12 = new Book("Ulysses",
                "James Joyce/Declan Kiberd","9780141182803");

        List<IBook> returnList = new ArrayList<>();
        returnList.add(book1);
        returnList.add(book2);
        returnList.add(book3);
        returnList.add(book4);
        returnList.add(book5);
        returnList.add(book6);
        returnList.add(book7);
        returnList.add(book8);
        returnList.add(book9);
        returnList.add(book10);
        returnList.add(book11);
        returnList.add(book12);

        if (word == null && author.contains("Joyce")){
            return returnList;
        }
        return null;
    }

    @Override
    public IBook getByISBN(String ISBN) {
        return null;
    }
}
