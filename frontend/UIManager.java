package frontend;

import java.util.Scanner;

import bank.backend.exceptions.ManagerException;
import bank.backend.manager.BankManager;

public class UIManager {
    private BankManager bankManager;
    private Scanner input;

    public UIManager() {
        bankManager = new BankManager();
        input = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1- register user");
        System.out.println("2- add account for user");
        System.out.println("3- show the user");
        System.out.println("4- transfer money");
        System.out.println("5- show transactions");
        System.out.println("6- exit");
        System.out.println("enter your value");
    }

    public void run() {
        showMenu();

        int select = input.nextInt();
        switch (select) {
            case 1 -> {
                String name = input.next();
                try {
                    bankManager.registerUser(name);
                } catch (ManagerException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                int userId = input.nextInt();
                double balance = input.nextDouble();
                try {
                    bankManager.addAccount(userId, balance);
                } catch (ManagerException e) {
                    System.out.println(e.getMessage());
                }
            }

            case 3 -> {
                System.out.println("Please select an option: ");
                System.out.println("1) show the user by id");
                System.out.println("2) show the user by lastName");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Enter the user id");
                        int id = input.nextInt();
                        try {
                            bankManager.printUsernamesById(id);
                        } catch (ManagerException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter the user lastname");
                        String lastname = input.next();
                        try {
                            bankManager.printUserIdBylastname(lastname);
                        } catch (ManagerException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            }

            case 4 -> {
                int destinationNumber = input.nextInt();
                int sourceNumber = input.nextInt();
                double amount = input.nextDouble();
                try {
                    bankManager.transferMoney(sourceNumber, destinationNumber, amount);
                } catch (ManagerException e) {
                    System.out.println(e.getMessage());
                }
            }

            case 5 -> {
                System.out.println("Please select an option: ");
                System.out.println("1) show transactions by user id");
                System.out.println("2) show transactions by user account number");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Enter the user id");
                        int id = input.nextInt();
                        try {
                            bankManager.printTransactionInformationById(id);
                        } catch (ManagerException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter the account number");
                        int number = input.nextInt();
                        try {
                            bankManager.printTransactionInformationByAccountNumber(number);
                        } catch (ManagerException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            }


        }

    }
}
