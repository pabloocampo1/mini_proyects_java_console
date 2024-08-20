package library_java;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class User {
    private String name;
    private int identification;
    private int age;
    private String userName;
    private String passWord;
    private static int idGlobal = 1;
    private int id;
    private String rol;

    public User(String name, int identification, int age, String userName, String passWord){
        this.name = name;
        this.identification = identification;
        this.age = age;
        this.userName = userName;
        this.passWord = passWord;
        this.id = ++idGlobal;
    }

    //methods getter()

    public String getName () {
        return this.name;
    };
    public String getUserName () {
        return this.userName;
    }
    public String getPassWord () {
        return this.passWord;
    }
    public String getRol() {
        return rol;
    }
    public int getIdentification () {
        return this.identification;
    }
    public int getAge() {
        return this.age;
    }
    public int getId () {
        return this.id;
    }

    //method setter()

    public void setName(String name){
        this.name = name;
    }
    public void setage(int age){
        this.age = age;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPasword(String passWord){
        this.passWord = passWord;
    }

    //method for show information about user

    public void getInformation(){
        System.out.println("Id " + this.id + " - Nombre : "+ getName() + "- edad: " + getAge() + " - identificacion " + getIdentification());
        
    };

    // method for change credential
    
}


class UserSimple extends User{
    private ArrayList<String> booksHistory;
    private ArrayList<BorrowedBooks> borrowedBooksUser;
    private String rol;

    public UserSimple(String name, int identification, int age, String userName, String passWord) {
        super(name, identification, age, userName, passWord );
        this.rol = "user";
        this.booksHistory = new ArrayList<>();
        this.borrowedBooksUser = new ArrayList<>();
    };

    public String getRol(){
        return this.rol;
    };

    public void showHistory () {
        if (booksHistory.size() < 1){
            System.out.println("No hay libros en el historial.");
            return;
        }
        System.out.println("Historial de libros prestados");
        int index = 1;
        for (String book : booksHistory) {
            System.out.println("--" + index + "--"+ book);
        }

    }

    public void getborrowedBooks(){
        if (borrowedBooksUser.size() < 1) {
            System.out.println("_____  No tienes libros prestados.  ____ ");
            return;
        }

        System.out.println("____   Libros actuales prestados.   ___");
        for (BorrowedBooks book : borrowedBooksUser) { 
           String loanBook = book.showBorrowed();
           System.out.println(loanBook);
           System.out.println("_____________________________________________________________________");
        }
    }

    public void addLoan(BorrowedBooks loan) {
        borrowedBooksUser.add(loan);
    }

    public void deleteLoan(BorrowedBooks loan){
        borrowedBooksUser.remove(loan);
    }

    //funcion of the menu
    public void showAllBooks(ArrayList<Book> bookDB) {
        for (Book book : bookDB) {
            book.getInfoBook();
        }
    }
    //method for lean a book
    public void LoanBookUser(ArrayList<Book> bookDB, Scanner input, ArrayList<BorrowedBooks> borrowedBooks, ArrayList<String> historyLoan, UserSimple user) {
        boolean loanBookActive = true;
        boolean findBook = false;
        while (loanBookActive) {
            System.out.println(" Ingresa el nombre del libro a prestar: (presiona 1 para salir)");
            String nameBook = input.nextLine().toUpperCase();

            if (nameBook.equals("1")) {
                loanBookActive = false;
            };
           
            for (Book book : bookDB) {
                if (book.getTitle().toUpperCase().equals(nameBook)) {
                    if (book.getQuality() > 1) {
                        System.out.println(" Estas seguro que deseas prestar el libro: "+ book.getTitle()+"? : (1. si/  2.no)");
                        int acceptLoan = input.nextInt();
                        input.nextLine();
                        if (acceptLoan == 1) {
                            book.setQuality(book.getQuality() - 1);
                            LocalDateTime dataTime =LocalDateTime.now();
                            BorrowedBooks newBorrowedBooks = new BorrowedBooks(user, book, dataTime);
                            borrowedBooks.add(newBorrowedBooks);
                            addLoan(newBorrowedBooks);
                            String newHistoryLoan = newBorrowedBooks.showBorrowed();
                            historyLoan.add(newHistoryLoan);
                            booksHistory.add(newHistoryLoan);
                            findBook = true;
                            System.out.println("    prestamo exitos");
                            break;
                        }else if(acceptLoan == 2) {
                            System.out.println("Deseas intentarlo de nuevo? (1. si / 2. no)");
                            int optionToBack = input.nextInt();
                            input.nextLine();
                            if (optionToBack == 1) {
                                continue;
                            }else if(optionToBack == 2){
                                loanBookActive = false;
                            }
                        }
                            
                    }else{
                        System.out.println("  El libro no se encuentra disponible. ");

                    }
                }
            };

            if (!findBook) {
                System.out.println("NO se encontro el libro, Deseas intenarlo de nuevo? (1. si / 2. no)");
                int optionToBack = input.nextInt();
                input.nextLine();
                if (optionToBack == 1) {
                    continue;
                }else{
                    loanBookActive = false;
                }
            }
            
        };
    };

    public void returnBook() {
        System.out.println("        Debes de dirigirte a la biblioteca a devolver el libro. ");
    };

    public void showAllLoan() {
        if (borrowedBooksUser.size()<1) {
            System.out.println("        No hay prestamos.        ");
            return;
        }
        System.out.println("    Prestamos actuales      ");
        for (BorrowedBooks loan : borrowedBooksUser) {
            String loanText = loan.showBorrowed();
            System.out.println(loanText);
        }
    };

    public void showHistoryLoan() {
        if (booksHistory.size()< 1) {
            System.out.println("    El historial de prestamos esta vacio.   ");
            return;
        };
        System.out.println("    Historial de prestamos");
        for (String books : booksHistory) {
            System.out.println(books);
        }
    }

    
}



class userAdmin extends User{
    private String rol;
    public userAdmin(String name, int identification, int age, String userName, String passWord) {
        super(name, identification, age, userName, passWord );
        this.rol = "admin";
    }

    public String getRol(){
        return this.rol;
    }
}