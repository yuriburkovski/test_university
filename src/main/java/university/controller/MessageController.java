package university.controller;

public class MessageController {

    public static void askForInput(){
        System.out.println("Please select one of this commands (by enter the number):");
        System.out.println("1. Show who is the Head of department.");
        System.out.println("2. Show department statistics.");
        System.out.println("3. Show the average salary for department.");
        System.out.println("4. Show count of department's employee.");
        System.out.println("5. The global search.");
    }

    public static void noInputMessage(){
        System.out.println("Wrong input data! Please try again.");
    }
    public static void chooseDepartmentMessage(){
        System.out.println("Please enter department name: Economics, Mathematics, Philology");
    }
}
