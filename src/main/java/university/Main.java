package university;

import university.config.SessionProvider;
import university.config.SetupDB;
import university.controller.CommandController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static university.controller.MessageController.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CommandController command = new CommandController();

    public static void main(String[] args) {
        SessionProvider.getSessionFactory().openSession();
        SetupDB.saveAll();
        run();
        SessionProvider.shutdown();
    }

    private static void run() {
        askForInput();
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                chooseDepartmentMessage();
                command.headOfDepartment(scanner.nextLine());
                exit();
                break;
            case "2":
                chooseDepartmentMessage();
                command.departmentStats(scanner.nextLine());
                exit();
                break;
            case "3":
                chooseDepartmentMessage();
                command.averageSalary(scanner.nextLine());
                exit();
                break;
            case "4":
                chooseDepartmentMessage();
                command.employeeCount(scanner.nextLine());
                exit();
                break;
            case "5":
                System.out.println("Search: ");
                ;
                command.globalSearch(scanner.nextLine());
                exit();
                break;
            default:
                noInputMessage();
                run();
                break;
        }
    }

    public static void exit() {
        System.out.println("Do you want exit: Y/N ?");
        String choice = scanner.nextLine().toLowerCase();
        switch (choice) {
            case "y":
                System.out.println("Good bye");
                break;
            case "n":
                run();
                break;
            default:
                noInputMessage();
                exit();
                break;
        }
    }
}
