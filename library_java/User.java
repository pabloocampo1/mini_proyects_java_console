package library_java;

public class User {
    private String name;
    private int identification;
    private int age;
    private String userName;
    private String password;
    private static int idGlobal = 10000;
    private int id;

    public User(String name, int identification, int age, String userName, String password){
        this.name = name;
        this.identification = identification;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.id = ++idGlobal;
    }

    //methods getter()

    public String getName () {
        return this.name;
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
    public void setPasword(String password){
        this.password = password;
    }

    //method for show information about user

    public void getInformation(){
        System.out.println(" - Nombre : "+ getName() + "- edad: " + getAge() + " - identificacion " + getIdentification());
        
    };
}