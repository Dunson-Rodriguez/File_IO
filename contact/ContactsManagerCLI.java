package contact;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
public class ContactsManagerCLI {





    private static final String CONTACTS_FILE = "contacts.txt";
    public static void main(String[] args) {



        List<Contact> contacts = loadContacts();
        System.out.println(
                "          ___   .--.\n" +
                "    .--.-\"   \"-' .- |\n" +
                "   / .-,`          .'\n" +
                "   \\   `           \\\n" +
                "    '.            ! \\\n" +
                "      |     !  .--.  |\n" +
                "      \\        '--'  /.____\n" +
                "     /`-.     \\__,'.'      `\\\n" +
                "  __/   \\`-.____.-' `\\      /\n" +
                "  | `---`'-'._/-`     \\----'    _ \n" +
                "  |,-'`  /             |    _.-' `\\\n" +
                " .'     /              |--'`     / |\n" +
                "/      /               `         | |\n" +
                "|   ./  \\      .--. __          \\ |\n" +
                " '-'      '._       /  `\\         /\n" +
                "            `\\    '     |------'`\n" +
                "               \\  |      |\n" +
                "                \\        /\n" +
                "                 '._  _.'\n" +
                "                    ``");

        System.out.println("WELCOME");
        while (true) {
            System.out.println("""
            \nMain Menu:
           1. View contacts.
           2. Add a new contact.
           3. Search a contact by name.
           4. Delete an existing contact.
           5. Exit.

           Enter an option (1, 2, 3, 4, or 5): """);



            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            doChoice(choice, contacts);
        }
    }

    private static void doChoice(String choice, List<Contact> contacts) {
        switch (choice) {
            case "1" -> showContacts(contacts);
            case "2" -> {
                addContact(contacts);
                showContacts(contacts);
            }
            case "3" -> searchContact(contacts);
            case "4" -> {
                showContacts(contacts);
                deleteContact(contacts);
                showContacts(contacts);
            }
            case "5" -> {
                saveContacts(contacts);
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTACTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0];
                String phoneNumber = parts[1];
                Contact contactt = new Contact(name, phoneNumber);
                contacts.add(contactt);
            }
        } catch (IOException e) {
            // Ignore if file does not exist or error occurs during reading
        }

        return contacts;
    }

    private static void saveContacts(List<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTACTS_FILE))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "  |  " + contact.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving contacts: " + e.getMessage());
        }
    }

    private static void showContacts(List<Contact> contacts) {
        System.out.println("Name\t\tPhone number");
        System.out.println("-".repeat(30));
        for (Contact contact : contacts) {
            String formattedPhoneNumber = formatPhoneNumber(contact.getPhoneNumber());
            System.out.println(contact.getName() + "\t" + formattedPhoneNumber);
        }
    }
    private static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
        } else if (phoneNumber.length() == 7) {
            return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
        } else {
            return phoneNumber;
        }
    }

    private static void addContact(List<Contact> contacts) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the contact's name: ");
        String name = scanner.nextLine();
        boolean overwriteContact = false;
        while (!overwriteContact) {
            for (Contact contact : contacts) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    System.out.println("There's already a contact named " + contact.getName() + ".");
                    System.out.print("Do you want to overwrite it? (Yes/No): ");
                    String overwriteChoice = scanner.nextLine();
                    if (overwriteChoice.equalsIgnoreCase("No")) {
                        System.out.println("Contact not added.");
                        return;
                    } else if (overwriteChoice.equalsIgnoreCase("Yes")) {
                        contacts.remove(contact);
                        overwriteContact = true;
                        break;
                    }
                }
            }
            if (!overwriteContact) {
                System.out.print("Enter the contact's phone number: ");
                String phoneNumber = scanner.nextLine();
                Contact contact = new Contact(name, phoneNumber);
                contacts.add(contact);
                System.out.println("Contact added successfully!");
                overwriteContact = true;
            }
        }
    }

    private static void searchContact(List<Contact> contacts) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the contact's name to search: ");
        String name = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact found:");
                System.out.println("Name: " + contact.getName());
                System.out.println("Phone number: " + contact.getPhoneNumber());
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    private static void deleteContact(List<Contact> contacts) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the contact's name to delete: ");
        String name = scanner.nextLine();

        Contact foundContact = null;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                foundContact = contact;
                break;
            }
        }

        if (foundContact != null) {
            contacts.remove(foundContact);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }
}