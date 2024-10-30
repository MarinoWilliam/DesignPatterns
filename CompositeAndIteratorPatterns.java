import java.util.*;

abstract class LibraryComponent {
    public void add(LibraryComponent book) {
        throw new UnsupportedOperationException();
    }

    public void remove(LibraryComponent book) {
        throw new UnsupportedOperationException();
    }

    public LibraryComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getTitle() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }
    
    public String getAuthor() {
        return "";
    }

    public boolean isAudble() {
        throw new UnsupportedOperationException();
    }

    public void print(){
        throw new UnsupportedOperationException();
    }

    public Iterator<LibraryComponent> createIterator() {
        throw new UnsupportedOperationException();
    }
}

class Genre extends LibraryComponent {
    ArrayList<LibraryComponent> books = new ArrayList<>();
    String name;
    String description;
    Iterator<LibraryComponent> iterator;

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(LibraryComponent book) {
        books.add(book);
    }

    public void remove(LibraryComponent book) {
        books.remove(book);
    }

    public LibraryComponent getChild(int i) {
        return (LibraryComponent) books.get(i);
    }

    public String getTitle() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAudble() {
        return false;
    }

    public void print(){
       System.out.println("\n "+ getTitle() + "   " +"a department for "+ getDescription());
       System.out.println(" ......................... \n");
       Iterator<LibraryComponent> tempIterator = books.iterator();
       while (tempIterator.hasNext()) {
           tempIterator.next().print();
       }

    }

    public Iterator<LibraryComponent> createIterator() {
        if (iterator == null) {
            iterator = new LibraryComponentIterator(books.iterator());
        }
        return iterator;
    }
}

class Book extends LibraryComponent {
    String title;
    String description;
    String author;
    double price;
    boolean audable;
    Iterator<LibraryComponent> iterator;

    public Book(String title, String description, double price, boolean audable,  String author) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.audable = audable;
        this.author= author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAudble() {
        return audable;
    }

    public String getAuthor() {
        return author;
    }

    public void print(){
       System.out.println(getTitle() + "   " + getPrice());
       System.out.println( getDescription());
    }

    public Iterator<LibraryComponent> createIterator() {
        if (iterator == null) {
            iterator = new NullIterator();
        }
        return iterator;
    }
}

class LibraryComponentIterator implements Iterator<LibraryComponent> {
    Stack<Iterator<LibraryComponent>> stack = new Stack<>();

    public LibraryComponentIterator(Iterator<LibraryComponent> iterator){
        stack.push(iterator);
    }

    @Override
    public LibraryComponent next() {
        if (hasNext()) {
            Iterator<LibraryComponent> iterator =  stack.peek();
            LibraryComponent libraryComponent = (LibraryComponent) iterator.next();
            if (libraryComponent instanceof Genre) {
                stack.push((LibraryComponentIterator) libraryComponent.createIterator());
            }
            return libraryComponent;
        } else {
            return null;
        }

    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        }
        Iterator iterator = (Iterator) stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        } else {
            return true;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

class NullIterator implements Iterator {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}


class Librarian {
    LibraryComponent allOfTheLibrary;

    public Librarian(LibraryComponent allOfTheLibrary){
        this.allOfTheLibrary = allOfTheLibrary;
    }

    public void printAllBooks(){
        allOfTheLibrary.print();
    }

    public void printAudable(){
        Iterator iterator = allOfTheLibrary.createIterator();
        while (iterator.hasNext()) {
            LibraryComponent tempLibraryComponent = (LibraryComponent) iterator.next();
            if (tempLibraryComponent.isAudble()) {
                System.out.println(tempLibraryComponent.getTitle());
            }
        }
    }
    
    public void printBooksOfAuthor(String author){
        Iterator<LibraryComponent> iterator = allOfTheLibrary.createIterator();
        while (iterator.hasNext()) {
            LibraryComponent tempLibraryComponent = (LibraryComponent) iterator.next();
            if (tempLibraryComponent.getAuthor() == author ) {
                System.out.println(tempLibraryComponent.getTitle());
            }
        }
    }

    

}

public class CompositeAndIteratorPatterns {
    public static void main(String[] args) {

        LibraryComponent novels = new Genre("Novels", "Fictional stories");
        LibraryComponent drama = new Genre("Drama", "Dramatic works");
        LibraryComponent fantasy = new Genre("Fantasy", "Fantasy works");
        LibraryComponent romantic = new Genre("Romantic", "Romantic stories");
        LibraryComponent comics = new Genre("Comics", "Illustrated stories");

        drama.add(new Book("Hamlet", "A tragedy by William Shakespeare", 10.99, false, "William Shakespeare"));
        fantasy.add(new Book("A Wizard of Earthsea", "A classic fantasy novel by Ursula K. Le Guin", 14.99, false, "Ursula K. Le Guin"));
        fantasy.add(new Book("The Hobbit", "A fantasy novel by J.R.R. Tolkien", 12.99, false, "J.R.R. Tolkien"));
        romantic.add(new Book("Pride and Prejudice", "A romantic novel by Jane Austen", 8.99, false, "Jane Austen"));

        novels.add(drama);
        novels.add(fantasy);
        novels.add(romantic);

        comics.add(new Book("Spider-Man", "A comic book about Spider-Man", 4.99, false, "Stan Lee"));
        comics.add(new Book("Batman", "A comic book about Batman", 5.99, false, "Bob Kane"));

        Genre library = new Genre("Library", "A collection of books and genres");
        library.add(novels);
        library.add(comics);

        Librarian librarian = new Librarian(library);

        System.out.println("\nPrinting All Books");
        librarian.printAllBooks();
        System.out.println("**************************");
        
        System.out.println("\nAudible Books:");
        librarian.printAudable();
        System.out.println("**************************");
        
        System.out.println("\nBooks by J.R.R. Tolkien:");
        librarian.printBooksOfAuthor("J.R.R. Tolkien");
    
    }
}
