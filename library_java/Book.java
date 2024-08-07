package library_java;

public class Book {
    private String title;
    private String description;
    private String author;
    private int year;
    private int quality;
    private static int idGlobalBook = 0;
    private int id;

    public Book(String title, String description, String author, int year, int quality ) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.year = year;
        this.quality = quality;
        this.id = ++idGlobalBook;
    }

    //method getter for book
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public String getAuthor(){
        return this.author;
    }
    public int getQuality(){
        return this.quality;
    }
    public int getYear(){
        return this.year;
    }
    public int getId(){
        return this.id;
    }

    //methods setter for book

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void getInfoBook() {

        System.out.println("_______________________________________________");
        System.out.println("--  "+this.title);
        System.out.println("--  "+this.year);
        System.out.println("--  "+this.author);
        System.out.println("--  "+this.description);
    }
}
