package library_java;

import java.util.ArrayList;
import java.util.Scanner;


public class Library {
    private ArrayList<User> userDB;
    private ArrayList<Book> booksDB;
    
    public Library() {
        this.userDB = new ArrayList<>();
        this.booksDB = new ArrayList<>();
    }

    public void login(){

    }

    public void jjj(UserSimple user){
        userDB.add(user);
    }
    public void jjj(userAdmin user){
        userDB.add(user);
    }


    public void menuAdmin(User user) {
        System.out.println("menu de usuarios");
    }
    public void showDb() {
        for (User user : userDB) {
            user.getInformation();
        }
    }

    public void menuUser(UserSimple user){
            System.out.println("menu de usuarios");
    }

    public static void main(String[] args) {
        Library library = new Library();

        UserSimple user = new UserSimple("juan", 23, 22, "123", "juan123");
        userAdmin admin = new userAdmin("j", 1, 12, "juan", "123");

        library.jjj(admin);
        library.jjj(user);
        
    }

}
