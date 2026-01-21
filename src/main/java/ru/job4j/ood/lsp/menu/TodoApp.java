package ru.job4j.ood.lsp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {

    private Menu menu;
    private MenuPrinter menuPrinter;

    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public TodoApp() {
        this.menu = new SimpleMenu();
        this.menuPrinter = new MenuPrinterClass();
    }

    public void addRootItem(String itemName) {
        menu.add(Menu.ROOT, itemName, DEFAULT_ACTION);
    }

    public void addItem(String parentName, String itemName) {
        menu.add(parentName, itemName, DEFAULT_ACTION);
    }

    public void executeAction(String itemName) {
        Optional<Menu.MenuItemInfo> item = menu.select(itemName);
        item.ifPresent(menuItemInfo -> menuItemInfo.getActionDelegate().delegate());
    }

    public void printMenu() {
        menuPrinter.print(menu);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Add root item");
                System.out.println("2. Add child item");
                System.out.println("3. Execute action");
                System.out.println("4. Print menu");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter root item name: ");
                        String rootItemName = scanner.nextLine();
                        addRootItem(rootItemName);
                        break;
                    case 2:
                        System.out.print("Enter parent item name: ");
                        String parentName = scanner.nextLine();
                        System.out.print("Enter child item name: ");
                        String childName = scanner.nextLine();
                        addItem(parentName, childName);
                        break;
                    case 3:
                        System.out.print("Enter item name to execute action: ");
                        String itemName = scanner.nextLine();
                        executeAction(itemName);
                        break;
                    case 4:
                        printMenu();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp();
        app.start();
    }
}