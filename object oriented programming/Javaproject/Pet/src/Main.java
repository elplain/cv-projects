import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetManager manager = new PetManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Pet Clinic Menu =====");
            // Display the main menu options
            System.out.println("1. Add Pet"); // Add a new pet
            System.out.println("2. List All Pets"); // Show all pets
            System.out.println("3. Make a Pet Speak"); // Make a pet speak
            System.out.println("4. Move Pet to Clinic"); // Move pet to clinic
            System.out.println("5. List Clinic Pets"); // Show pets in clinic
            System.out.println("6. Modify Pet Info"); // Edit pet details
            System.out.println("7. Search Pets"); // Search pets by keyword
            System.out.println("8. Delete Pet from Clinic"); // Remove pet from clinic
            System.out.println("9. Report on Clinic"); // Generate clinic report
            System.out.println("0. Exit"); // Exit the program
            System.out.print("Choose an option: "); // Prompt user for input

            int option = sc.nextInt(); // Read user choice
            sc.nextLine(); // Consume newline character

            try {
                switch (option) {
                    case 1 -> {
                        // Add a new pet
                        System.out.print("Pet type (Dog/Cat): ");
                        String type = sc.nextLine();
                        System.out.print("Name: "); // Enter pet name
                        String name = sc.nextLine();
                        System.out.print("Age: "); // Enter pet age
                        int age = sc.nextInt(); sc.nextLine();
                        System.out.print("Color: "); // Enter pet color
                        String color = sc.nextLine();
                        System.out.print("Weight: "); // Enter pet weight
                        double weight = sc.nextDouble(); sc.nextLine();
                        System.out.print("Breed: "); // Enter pet breed
                        String breed = sc.nextLine();

                        // Create Dog or Cat object
                        Pet pet = type.equalsIgnoreCase("Dog")
                                ? new Dog(name, age, color, weight, breed)
                                : new Cat(name, age, color, weight, breed);
                        manager.addPet(pet); // Add pet to file
                        System.out.println("Pet added."); // Confirm addition
                    }

                    case 2 -> {
                        // List all pets
                        var pets = manager.loadPets("PetDetails.txt");
                        if (pets.isEmpty()) System.out.println("No pets available."); // No pets found
                        else {
                            for (int i = 0; i < pets.size(); i++) {
                                // Display each pet
                                System.out.println((i + 1) + ". " + pets.get(i));
                            }
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter Pet ID to speak: ");
                        int id = sc.nextInt();
                        manager.speakPet(id);
                    }

                    case 4 -> {
                        System.out.print("Enter Pet ID to move to clinic: ");
                        int id = sc.nextInt();
                        manager.movePetToClinic(id);
                    }

                    case 5 -> manager.listClinicPets();

                    case 6 -> {
                        System.out.print("Enter Pet ID to modify: ");
                        int id = sc.nextInt(); sc.nextLine();
                        manager.modifyPet(id, "PetDetails.txt", sc);
                    }

                    case 7 -> {
                        System.out.print("Enter keyword to search (name, color, age, breed): ");
                        String keyword = sc.nextLine();
                        manager.searchPets(keyword);
                    }

                    case 8 -> {
                        manager.listClinicPets();
                        System.out.print("Enter ID of pet to delete from clinic: ");
                        int id = sc.nextInt(); sc.nextLine();
                        manager.deletePetFromClinic(id);
                    }

                    case 9 -> manager.reportClinic();

                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
