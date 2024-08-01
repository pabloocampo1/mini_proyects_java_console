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

    
}
