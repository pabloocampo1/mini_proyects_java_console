package library_java;
import java.util.ArrayList;

public class User {
    private String name;
    private int identification;
    private int age;
    private String userName;
    private String passWord;
    private static int idGlobal = 10000;
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
        System.out.println(" - Nombre : "+ getName() + "- edad: " + getAge() + " - identificacion " + getIdentification());
        
    };

    // method for change credential
    
}


class UserSimple extends User{
    private ArrayList<String> booksHistory;
    private ArrayList<BorrowedBooks> borrowedBooks;
    private String rol;

    public UserSimple(String name, int identification, int age, String userName, String passWord) {
        super(name, identification, age, userName, passWord );
        this.rol = "user";
        this.booksHistory = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    };

    public String getRol(){
        return this.rol;
    };

    public void showHistory () {
        if (booksHistory.size() < 1){
            System.out.println("no hay libros en el historial.");
            return;
        }
        System.out.println("historial de libros prestados");
        int index = 1;
        for (String book : booksHistory) {
            System.out.println("--" + index + "--"+ book);
        }

    }

    public void getborrowedBooks(){
        if (borrowedBooks.size() < 1) {
            System.out.println("_____  No tienes libros prestados.  ____ ");
            return;
        }

        System.out.println("____ Libros actuales prestados.  ___");
        for (BorrowedBooks book : borrowedBooks) {
            System.out.println("________________________________________");
           String loanBook = book.showBorrowed();
           System.out.println(loanBook);
        }
    }

    public void addLoan(BorrowedBooks loan) {
        borrowedBooks.add(loan);
    }

    public void deleteLoan(BorrowedBooks loan){
        borrowedBooks.remove(loan);
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