package library_java;

import java.time.LocalDateTime;

public class BorrowedBooks {
    private User user;
    private Book book;
    private static int id_general = 0;
    private int id;
    private LocalDateTime date;

    public BorrowedBooks(User user, Book book, LocalDateTime date) {
        this.user = user;
        this.book = book;
        this.id = ++id_general;
        this.date = date;
    }

    public String showBorrowed() {
        return " id " + this.id + " - libro " + this.book.getTitle() + " - usuario " + this.user.getName() + user.getIdentification() + " - fecha: " + date;
    }
    public int getIdLend(){
        return this.id;
    }

    public void qualityBook(){
        this.book.setQuality(book.getQuality() + 1);
    }
}
