package library_java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static String chooseCategoryBook(Scanner input) {
        ArrayList<String> categoryBookList = new ArrayList<>();
        categoryBookList.add("Ficcion");
        categoryBookList.add("No ficcion");
        categoryBookList.add("Ciencia ficcion y fantasia");
        categoryBookList.add("Literatura infantil y juvenil");
        categoryBookList.add("Biografia y autobiografia");

        boolean isSelect = true;
        String optionCategory = "";
        while (isSelect) {
            System.out.println("  Elige la categoria del libro:  ");
            System.out.println(" 1. Ficcion\n 2. No ficcion\n 3. Ciencia ficcion y fantasia\n 4. Literatura infantil y juvenil\n 5. Biografia y autobiografia");
            int category = input.nextInt();
            input.nextLine();
            switch (category) {
                case 1:
                    optionCategory = categoryBookList.get(0);
                    isSelect = false;
                    break;
                case 2:
                    optionCategory = categoryBookList.get(1);
                    isSelect = false;
                    break;
                case 3:
                    optionCategory = categoryBookList.get(2);
                    isSelect = false;
                    break;
                case 4:
                    optionCategory = categoryBookList.get(3);
                    isSelect = false;
                    break;
                case 5:
                    optionCategory = categoryBookList.get(4);
                    isSelect = false;
                    break;
            
                default:
                    System.out.println("debes de elegir una opcion valida");
                    break;
            }
        }
        if (optionCategory.equals("")) {
            System.out.println(" Debes de agregar una categoria validad ");
            chooseCategoryBook(input);
        }
        
        return optionCategory;
    }
}
