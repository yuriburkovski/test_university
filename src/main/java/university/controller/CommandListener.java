package university.controller;

import java.util.Scanner;

import static university.controller.MessageController.*;
import static university.controller.MessageController.noInputMessage;

public class CommandListener {
    static Scanner scanner = new Scanner(System.in);
    static CommandExecutor command = new CommandExecutor();
    public static void run() throws Exception {
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
                command.globalSearch(scanner.nextLine());
                exit();
                break;
            default:
                noInputMessage();
                run();
                break;
        }
    }

    static void exit() throws Exception {
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
