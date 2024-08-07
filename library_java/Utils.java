package library_java;

import java.time.LocalDate;

public class Utils {
    public static void veritifyUserName(String userName) {
        if (userName.length() < 5){
            throw new IllegalArgumentException("el nombre de usuario es demaciada corta,  debes de tener almenos 5 caracteres");
        }
    }

    public static void veritifyPassWord(String passWord) {
        if(passWord.length() < 5) {
            throw new IllegalArgumentException("la contraseñ es demaciado corta, debes de tener almenos 5 caracteres");
        }
    }

    public static void veritifyYearBook(int year) {
        LocalDate dateCurrent =  LocalDate.now();

        int yearCurrent = dateCurrent.getYear();

        if (year > yearCurrent) {
            throw new IllegalArgumentException("el año del libro no es valido.");
        }

    }
}
