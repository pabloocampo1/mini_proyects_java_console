package library_java;

import java.util.ArrayList;
import java.util.Scanner;


public class Library {
    private ArrayList<User> userDB;
    private ArrayList<Book> booksDB;
    private Scanner input;
    
    public Library() {
        this.userDB = new ArrayList<>();
        this.booksDB = new ArrayList<>();
        this.input = new Scanner(System.in);
    }
    // method for valida
    public void isUserRegister(User user) {
        if( user == null) {
            System.out.println("no pudo crear la cuenta, vuelve a intentarlo. ");
            menuLogin();
        }else{
            System.out.println("agregado exitosamente¡");
        }
    }

    //method for logout

    public void logOut() {
        System.out.println("Tu nombre: ");
        String name = input.nextLine();
        int age = 0;

        while (true) {
            System.out.println("tu edad: ");
            int ageI = input.nextInt();

            if (ageI < 1 || ageI > 100) {
                System.out.println("edad no valida. vuelve a intentarlo");
            }else{
                age = ageI;
                break;
            }
        };

        int identification = 0;

        while (true) {
            System.out.println("tu identicacion: ");
            int identi = input.nextInt();
            input.nextLine();
            boolean isValid = false;

            if(userDB.size() == 0) {
                identification = identi;
                break;
            }

            for (User user : userDB) {
                if(user.getIdentification() == identi) {
                    continue;
                }else{
                    isValid = true;
                }
            }

            if (isValid) {
               identification = identi;
               break;
            }else{
                System.out.println("____El numero de identifacion ya esta registrado, intentalo de nuevo.____");
            }
        };
       
        String userName = "";

        while (true) {
            System.out.println("tu nombre de usuario: ");
            String nameUser = input.nextLine();

            boolean userNameValid = false;

            if (userDB.size() == 0) {
                userName = nameUser;
                break;
            }

            for (User user : userDB) {
                if (user.getUserName().equals(nameUser)) {
                    continue;
                }else{
                    userNameValid = true;
                }
            }

            if (userNameValid){
                userName = nameUser;
                break;
            }else{
                System.out.println("nombre de usuario no valido, intealo de nuevo.");
            }
        }

        
        String passWord = "";

        while (true) {
            System.out.println("tu contraseña de usuario: ");
            String passWordUser = input.nextLine();

            boolean passWordValid = false;

            if (userDB.size() == 0) {
                passWord = passWordUser;
                break;
            }

            for (User user : userDB) {
                if (user.getUserName().equals(passWordUser)) {
                    continue;
                }else{
                    passWordValid = true;
                }
            }

            if (passWordValid){
                passWord = passWordUser;
                break;
            }else{
                System.out.println("nombre de usuario no valido, intealo de nuevo.");
            }
        }

        UserSimple user = new UserSimple(name, identification, age, userName, passWord);
        
        userDB.add(user);
        isUserRegister(user);

    };

    // method for login

    public void logIn() {
        boolean isLogin = true;
        while (isLogin) { 
            System.out.println("____  login  _____");
            System.out.println("nombre de usuario: ");
            String userName = input.nextLine();
            System.out.println("_______________________________");
            System.out.println("tu contraseña: ");
            String password = input.nextLine();

            boolean isUser = false;

            for (User user : userDB) {
                if(user.getUserName().equals(userName) && user.getPassWord().equals(password)) {
                    if (user instanceof UserSimple) {
                        menuUser(user);
                    }else if(user instanceof userAdmin) {
                        menuAdmin(user);
                    }
                    isUser = true;
                    break;
                }
            }

            if (!isUser) {
                System.out.println("credenciales incorrectas, intentalo de nuevoo");
            }
        }
    }

    public void menuLogin(){
        boolean loginActive = true;
        while (loginActive) {
            System.out.println("___Bienvenido al inicio de sesion___");
            System.out.println("  1. Iniciar sesion  ");
            System.out.println("  2. registrarse \n ");

            System.out.println("  digite la opcion que desea: ");
            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    logIn();
                    break;
                case 2:
                    logOut();
                    logIn();
                    break;
            
                default:
                    System.out.println("opcion no valida.");
                    break;
            }
        }
    }

    //menu of admin

    public void menuAdmin(User user) {
        boolean menuActive = true;
        while (menuActive) {
            System.out.println("___ Menu  admin ____");
            System.out.println("\n 1. Cambiar credenciales \n 2. Agregar libros\n 3. Eliminar libros\n 4. Editar libro\n 5. ver todos los libros\n 6. prestar libro\n 7. Devolver libro\n 8. ver historial de prestamos\n 9. ver todos los usuarios.\n 10. salir ");
            
            System.out.println("\n digite la opcion que desea: ");
            int optionAdmin = input.nextInt();
            switch (optionAdmin) {
                case 1:
                    changeCredentials(user);
                    break;
                case 2:
                    addBooks();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    editBook();
                    break;
                case 5:
                    allBooks();
                    break;
                case 6:
                    lendBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    historyLends();
                    break;
                case 9:
                    allUser();
                    break;
                case 10:
                    menuActive = false;
                    break;
            
                default:
                    break;
            }
            break;
        }
    }

    public void changeCredentials(User user){

    }
    public void addBooks(){

    }
    public void deleteBook(){

    }
    public void editBook(){

    }
    public void allBooks(){

    }
    public void lendBook(){

    }
    public void returnBook(){

    }
    public void historyLends(){

    }
    public void allUser(){

    }


    public void menuUser(User user){
            System.out.println("menu de usuarios" + user.getName());
    }

    public void jjj(UserSimple user){
        userDB.add(user);
    }
    public void jjj(userAdmin user){
        userDB.add(user);
    }

    public static void main(String[] args) {
        Library library = new Library();
        
        UserSimple user = new UserSimple("jua", 2, 3, "ju", "123");
        userAdmin admin = new userAdmin("jua", 2, 3, "juan", "13");
        library.jjj(user);
        library.jjj(admin);
        library.menuLogin();
        
    }

}
