package library_java;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Comparator;
public class Library {
    private ArrayList<User> userDB;
    private ArrayList<Book> booksDB;
    private ArrayList<BorrowedBooks> borrowedBooks;
    private ArrayList<String> historyLoan;
    private Scanner input;
    
    public Library() {
        this.userDB = new ArrayList<>();
        this.booksDB = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        this.historyLoan = new ArrayList<>();
        this.input = new Scanner(System.in);
    }

    public void closeScanner() {
        input.close();
    };

    public void sortBooks() {
        booksDB.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle()); 
            }
        });
    };

    // method for valida
    public void isUserRegister(User user) {
        if( user == null) {
            System.out.println("no pudo crear la cuenta, vuelve a intentarlo. ");
            menuLogin();
        }else{
            System.out.println("agregado exitosamente¡");
        }
    };

    //method for create new acount

    public void createNewAcount() {
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
            boolean userNameValid = false;
            System.out.println("tu nombre de usuario: ");
            String nameUser = input.nextLine();
            // this "try" is for valid userName
            try {
                Utils.veritifyUserName(nameUser);
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
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

        User user = new UserSimple(name, identification, age, userName, passWord);
        
        userDB.add(user);
        isUserRegister(user);

    };

    // method for login

    public void logIn() {
        boolean isLogin = true;
        while (isLogin) { 
            System.out.println("        login      ");
            System.out.println("nombre de usuario: ");
            String userName = input.nextLine();
            System.out.println("_______________________________");
            System.out.println("tu contraseña: ");
            String password = input.nextLine();

            boolean isUser = false;

            for (User user : userDB) {
                if(user.getUserName().equals(userName) && user.getPassWord().equals(password)) {
                    if (user instanceof UserSimple) {
                        UserSimple userSimple = (UserSimple) user;
                        menuUser(userSimple, booksDB);
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
    };

    public void menuLogin(){
        boolean loginActive = true;
        while (loginActive) {
            System.out.println("    Menu inicio de sesion      ");
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
                    createNewAcount();
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
            System.out.println("                     MENU           ");
            System.out.println("\n 1. Cambiar credenciales \n 2. Agregar libros\n 3. Eliminar libros\n 4. Editar libro\n 5. ver todos los libros\n 6. prestar libro\n 7. Devolver libro\n 8. Ver todos los prestamos actuales\n 9. ver todos los usuarios.\n 10. crear un usuario\n 11. ver historial de prestamos\n 12. Cerrar sesion ");
            
            System.out.println("\n digite la opcion que desea: ");
            int optionAdmin = input.nextInt();
            input.nextLine();
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
                    boolean activeEditBook = true;
                    boolean findBook = false;
                    while (activeEditBook) {
                        System.out.println("ingrese el id del libro: ");
                        int idBook =input.nextInt();
                        input.nextLine();
                        outerLoop:
                        for (Book book : booksDB) {
                            if (book.getId() == idBook) {
                                editBook(book);
                                findBook = true;
                                break outerLoop;
                            }
                            
                        }
                        if(!findBook) {
                            while (true) {
                                System.out.println("no se encontro el libro. deseas intentarlo denuevo? (si/no) : ");
                                String tryAgain = input.nextLine().toLowerCase();
                                if (tryAgain.equals("si")) {
                                    break;
                                }else if(tryAgain.equals("no")) {
                                    activeEditBook = false;
                                    break;
                                }else{
                                    System.out.println("opcion no valida");
                                }
                            }
                        }
                        activeEditBook = false;                           
                    }
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
                    allLoan();
                    break;
                case 9:
                    allUser();
                    break;
                case 10:
                    createNewAcount();
                    break;
                case 11:
                    showHistoryLoan();
                    break;
                case 12:
                    menuActive = false;
                    break;
            
                default:
                    System.out.println("opcion no valida");
                    break;
            }
        }
    };

    public void changeCredentials(User user){
        boolean menuActive = true;
        while(menuActive) {
            System.out.println("______________________________________________________________________");
            System.out.println("   1.Cambiar nombre de usuario\n   2. cambiar contraseña \n   3. salir");
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println("-Digite el nuevo nombre de usuario:   ");
                    String name = input.nextLine();
                    try {
                        Utils.veritifyUserName(name);
                        user.setUserName(name);
                        System.out.println("--- el nombre de usuario se cambio correctamente --");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("-Digite el nueva contraseña:   ");
                    String passWord = input.nextLine();
                    try {
                        Utils.veritifyPassWord(passWord);
                        user.setPasword(passWord);
                        System.out.println("--- la contraseña se cambio correctamente --");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    menuActive = false;
                    break;
            
                default:
                    break;
            }
        }
    };
    
    public void addBooks(){
        System.out.println(" - titulo del libro: ");
        String title = input.nextLine();

        System.out.println(" - Descripcion del libro: ");
        String description = input.nextLine();

        System.out.println(" - Autor del libro: ");
        String author = input.nextLine();

        int year = 0;

        while (true) {
            System.out.println(" - Año del libro: ");
            int yearCurrent = input.nextInt();
            try {
                Utils.veritifyYearBook(yearCurrent);
                year = yearCurrent;
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(" - Cantidad del libro: ");
        int quality = input.nextInt();
        input.nextLine();

        
        String categoryBook = Utils.chooseCategoryBook(input);

        Book book = new Book(title, description, author, year, quality, categoryBook);
        booksDB.add(book);
        System.out.println(" el libro se agrego correctamente. ");
        //sortBooks();
    };

    public void deleteBook(){
        System.out.println(" Digite el id del libro a borrar: ");
        int idBook = input.nextInt();

        for (Book book : booksDB) {
            outerLoop:
            if(book.getId() == idBook) {
                System.out.println("Estas seguro de eliminar el libro: " + book.getTitle() + "(1.si / 2.no)");
                int response = input.nextInt();
                input.nextLine();
                switch (response) {
                    case 1:
                        booksDB.remove(book);
                        System.out.println(" se elimino el libro correctamente");
                        break outerLoop;
                    case 2:
                        break outerLoop;
                    default:
                        break;
                }
                
                
            }
        }
    };

    public void editBook(Book book){

        System.out.println("Que desea editar?\n 1.Titulo\n 2. descripcion\n 3. Autor\n 4. Año\n 5. cantidad\n 6.salir al menu.");
        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                System.out.println("ingrese el nuevo titulo :");
                String title =input.nextLine();
                book.setTitle(title);
                break;
            case 2:
                System.out.println("ingrese la nueva descripcion :");
                String description =input.nextLine();
                book.setDescription(description);
                break;
            case 3:
                System.out.println("ingrese el autor :");
                String author =input.nextLine();
                book.setAuthor(author);
                break;
            case 4:
                System.out.println("ingrese el año :");
                int year =input.nextInt();
                input.nextLine();
                book.setYear(year);
                break;
            case 5:
                System.out.println("ingrese la cantidad:");
                int quality =input.nextInt();
                input.nextLine();
                book.setQuality(quality);
                book.veritifyStateBook();
                break;
            case 6:
                
                break;
        
            default:
                System.out.println("option no valida");
                break;
        }
    };

    public void allBooks(){
        if (booksDB.size() < 1) {
            System.out.println("  no hay libros.  ");
            return;
        }
        sortBooks();
        System.out.println("    LIBROS   ");
        for (Book book : booksDB) {
            book.getInfoBook();
        }
    }

    public void lendBook(){
        System.out.println("digite la identificacion del usuario. ");
        int identification = input.nextInt();
        input.nextLine();

        boolean userFound = false;
        boolean bookFound = false;

        for (User user : userDB) {
            if (user.getIdentification() == identification) {
                if (user instanceof UserSimple) {
                    UserSimple userSimple = (UserSimple) user;
                    userFound = true;
                    System.out.println("ingrese el numero de id del libro a prestar");
                    int idBook = input.nextInt();
                    input.nextLine();
                    for (Book book : booksDB) {
                        if (book.getId() == idBook) {
                            bookFound = true;
                            if(book.getQuality() < 1){
                                System.out.println(" el libro no se encuntra disponible.");
                                break;
                            }else{
                                book.setQuality(book.getQuality() - 1);
                                LocalDateTime dataTime =LocalDateTime.now();
                                BorrowedBooks newBorrowedBook = new BorrowedBooks(userSimple, book, dataTime);
                                borrowedBooks.add(newBorrowedBook);
                                userSimple.addLoan(newBorrowedBook);
                                String loan = newBorrowedBook.showBorrowed();
                                historyLoan.add(loan);
                                System.out.println("prestamo exitoso.");
                            }
                        }
                    }
                }
            }
        }

        if(!userFound) {
            while (true) {
                System.out.println("no se encontro el usuario. deseas intentarlo de nuevo? (si / no) ");
                String optionBack = input.nextLine().toLowerCase();
                if (optionBack.equals("si")) {
                    lendBook();
                }else if(optionBack.equals("no")){
                    break;
                }
            }
        }else if(!bookFound) {
            System.out.println("no se encontro el libro.");
        } 
    };

    public void returnBook(){

        if (borrowedBooks.size() <1 ) {
            System.out.println("    No hay prestamos    ");
            return;
        }

        System.out.println("digite la identificacion del usuario. ");
        int identification = input.nextInt();
        input.nextLine();

        boolean userFound = false;
        boolean loanFound = false;

        for (User user : userDB) {
            if (user.getIdentification() == identification) {
                if (user instanceof UserSimple) {
                    UserSimple userSimple = (UserSimple) user;
                    userFound = true;
        
                    userSimple.getborrowedBooks();
                    System.out.println("ingrese el id del prestamo a devolver: ");
                    int id_loand =input.nextInt();
                    input.nextLine();
                    ArrayList<BorrowedBooks> toRemove = new ArrayList<>();
                    for (BorrowedBooks lend : borrowedBooks) {
                        if (lend.getIdLend() == id_loand) {
                            loanFound = true;
                            lend.qualityBook();
                            userSimple.deleteLoan(lend);
                            toRemove.add(lend);
                            System.out.println("        Entrega exitosa");
                        }
                    }
                    borrowedBooks.removeAll(toRemove);
                }
            }
        }

        if(!userFound) {
            while (true) {
                System.out.println("no se encontro el usuario. deseas intentarlo de nuevo? (si / no) ");
                String optionBack = input.nextLine().toLowerCase();
                if (optionBack.equals("si")) {
                    lendBook();
                }else if(optionBack.equals("no")){
                    break;
                }
            }
        }else if(!loanFound) {
            System.out.println("no se encontro el prestamo.");
        }
    };

    public void allLoan(){
        if(borrowedBooks.size() < 1) {
            System.out.println("        no hay prestamos.     ");
            return;
        }

        System.out.println("       Prestamos actuales    ");
        for (BorrowedBooks loan : borrowedBooks) {
            System.out.println("_______________________________________");
            String loanText = loan.showBorrowed();
            System.out.println(loanText);
        }
    }
    public void allUser(){
        if(userDB.size() < 1) {
            System.out.println("         No hay usuarios registrados.       ");

        }
        System.out.println("     USUARIOS       ");
        for (User user : userDB) {
            user.getInformation();
        }
    };
    
    public void addManyBook(){
        Book book1 = new Book(
            "1984",
            "A dystopian novel set in a totalitarian regime controlled by the Party.",
            "George Orwell",
            1949,
            2,
            "Ficcion"
        );

        Book book2 = new Book(
            "The Great Gatsby",
            "A novel about the American Dream and the disillusionment of the Jazz Age.",
            "F. Scott Fitzgerald",
            1925,
            3,
            "Ficcion"
        );

        Book book3 = new Book(
            "Pride and Prejudice",
            "A classic novel that explores the issues of marriage, morality, and misconceptions.",
            "Jane Austen",
            1813,
            3,
            "No Ficcion"
        );

        Book book4 = new Book(
            "Moby Dick",
            "A novel about the voyage of the Pequod, led by Captain Ahab in his quest for revenge against the white whale, Moby Dick.",
            "Herman Melville",
            1851,
            1,
            "Biografia y autobiografia"
        );

        Book book5 = new Book(
            "The Catcher in the Rye",
            "A story about a teenager, Holden Caulfield, and his experiences in New York City.",
            "J.D. Salinger",
            1951,
            4,
            "Ficcion"
        );

        booksDB.add(book1);
        booksDB.add(book2);
        booksDB.add(book3);
        booksDB.add(book4);
        booksDB.add(book5);
    };

    public void showHistoryLoan(){
        if (historyLoan.size() < 1) {
            System.out.println("   no hay prestamos    ");
            return;
        }

        for (String loan : historyLoan) {
            System.out.println(loan);
        }
    }
        //        MENUUU OF USER     

    public void menuUser(UserSimple user, ArrayList<Book> BookDB){
        boolean menuUserActive = true;
        while (menuUserActive) {
            System.out.println("\n        Menu, Hola " + user.getName() + " Bienvenido a la biblioteca fullHD. \n");
            System.out.println(" 1. Cambiar credenciales\n 2. buscar libro\n 3. ver todos los libros\n 4. Prestar libros\n 5. Devolver libro\n 6. Ver prestamos actuales\n 7 Ver historial de prestamos\n 8 Cerrar sesion.");
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    changeCredentials(user);
                    break;
                case 2:
                    boolean menuSearbook = true;
                    while (menuSearbook) {
                        System.out.println("   Como deseas buscar el libro?   \n 1.Por titulo\n 2. Por autor\n 3. Por categoria\n 4. Salir");
                        int optionSearch = input.nextInt();
                        input.nextLine();
                        switch (optionSearch) {
                            case 1:
                                searchBookTitle();
                                menuSearbook = false;
                                break;
                            case 2:
                                searchBookAuthor();
                                menuSearbook = false;
                                break;
                            case 3:
                                searchBookCategory();
                                menuSearbook = false;
                                break;
                            case 4:
                                menuSearbook = false;
                                break;
                            default:
                                System.out.println("   Debes de ingresar una opcion valida.   ");
                                break;
                        }
                    }
                    break;
                case 3:
                    user.showAllBooks(BookDB);
                    break;
                case 4:
                    user.LoanBookUser(booksDB, input, borrowedBooks, historyLoan,user);
                    break;
                case 5:
                    user.returnBook();
                    break;
                case 6:
                    user.showAllLoan();
                    break;
                case 7:
                    user.showHistoryLoan();
                    break;
                case 8:
                    menuUserActive = false;
                    break;
                default:
                    System.out.println("    Debes de agregar una opcion correcta.  ");
                    break;
            }
        }
    }
    public void searchBookTitle() {
        System.out.println(" Ingresa el titulo del libro: ");
        String title = input.nextLine().toUpperCase();
        try {
            boolean findBook = false;
            for (Book book : booksDB) {
                if (book.getTitle().toUpperCase().equals(title)) {
                    findBook = true;
                    book.getInfoBook();
                }
            }
            if (findBook == false) {
                throw new IllegalArgumentException("No se Encontro ningun libro. ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Quieres intentarlo de nuevo? (1. si / 2. no)");
        int optionBack = input.nextInt();
        input.nextLine();
        if (optionBack == 1) {
            searchBookTitle();
        }
    }; 

    public void searchBookAuthor() {
        System.out.println(" Ingresa el nombre del autor: ");
        String authorName = input.nextLine().toUpperCase();
        try {
            boolean findBook = false;
            for (Book book : booksDB) {
                if (book.getAuthor().toUpperCase().equals(authorName)) {
                    findBook = true;
                    book.getInfoBook();
                }
            }
            if (findBook == false) {
                throw new IllegalArgumentException("No se Encontro ningun autor. ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Quieres intentarlo de nuevo? (1. si / 2. no)");
        int optionBack = input.nextInt();
        input.nextLine();
        if (optionBack == 1) {
            searchBookAuthor();
        }
    }
    public void searchBookCategory() {
        String category = Utils.chooseCategoryBook(input);
        boolean findCategory = false;
        try {
            for (Book book : booksDB) {
                if (book.getCategoryBook().equals(category)) {
                    findCategory = true;
                    book.getInfoBook();
                }
            };
            if (!findCategory) {
                throw new IllegalArgumentException("No hay libros en la categoria: " + category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Quieres intentarlo de nuevo? (1. si / 2. no)");
        int optionBack = input.nextInt();
        input.nextLine();
        if (optionBack == 1) {
            searchBookCategory();
        }
    };
    public void adminInit(userAdmin user){
        userDB.add(user);
    }
    public static void main(String[] args) {
        Library library = new Library();
        library.addManyBook();
        library.sortBooks();
        userAdmin admin = new userAdmin("juan", 2, 3, "juan123", "juan12345");
        library.adminInit(admin);
        library.menuLogin();
        library.closeScanner();
    }
}



