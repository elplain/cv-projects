import java.io.*;
import java.util.*;

public class PetManager {
    private final String PETS_FILE = "PetDetails.txt";
    private final String CLINIC_FILE = "ClinicDetails.txt";

    public void addPet(Pet pet) throws IOException {
        // Add a new pet to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PETS_FILE, true))) {
            String type = pet instanceof Dog ? "Dog" : "Cat";
            String line = type + "," + pet.name + "," + pet.age + "," + pet.color + "," + pet.weight + "," + pet.breed;
            writer.write(line);
            writer.newLine();
        }
    }

    public List<Pet> loadPets(String filename) throws IOException {
        // Load pets from the specified file
        List<Pet> pets = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return pets;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into pet details
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String color = parts[3];
                double weight = Double.parseDouble(parts[4]);
                String breed = parts[5];

                // Create a Dog or Cat object
                if (type.equals("Dog")) {
                    pets.add(new Dog(name, age, color, weight, breed));
                } else {
                    pets.add(new Cat(name, age, color, weight, breed));
                }
            }
        }
        return pets;
    }

    public void listClinicPets() throws IOException {
        // Display all pets in the clinic
        List<Pet> pets = loadPets(CLINIC_FILE);
        if (pets.isEmpty()) {
            System.out.println("No pets in the clinic.");
        } else {
            for (int i = 0; i < pets.size(); i++) {
                System.out.println((i + 1) + ". " + pets.get(i));
            }
        }
    }

    public void speakPet(int id) throws IOException {
        // Make a pet speak by ID
        List<Pet> pets = loadPets(PETS_FILE);
        if (id <= 0 || id > pets.size()) {
            System.out.println("Invalid ID.");
            return;
        }
        Pet pet = pets.get(id - 1);
        System.out.println(pet.speak());
    }

    public void movePetToClinic(int id) throws IOException {
        // Move a pet to the clinic file
        List<Pet> allPets = loadPets(PETS_FILE);
        if (id <= 0 || id > allPets.size()) {
            System.out.println("Invalid ID.");
            return;
        }
        Pet pet = allPets.get(id - 1);
        List<Pet> clinicPets = loadPets(CLINIC_FILE);
        clinicPets.add(pet);
        savePetsToFile(clinicPets, CLINIC_FILE);
        System.out.println("Pet moved to clinic list with new ID: " + clinicPets.size());
    }

    public void deletePetFromClinic(int id) throws IOException {
        // Remove a pet from the clinic file
        List<Pet> pets = loadPets(CLINIC_FILE);
        if (id <= 0 || id > pets.size()) {
            System.out.println("Invalid ID.");
            return;
        }
        pets.remove(id - 1);
        savePetsToFile(pets, CLINIC_FILE);
        System.out.println("Pet deleted from clinic list.");
    }

    public void reportClinic() throws IOException {
        final String clinicName = "Happy Paws Veterinary Clinic";
        List<Pet> pets = loadPets(CLINIC_FILE);
    
        int dogCount = 0;
        int catCount = 0;
    
        for (Pet pet : pets) {
            if (pet instanceof Dog) dogCount++;
            else if (pet instanceof Cat) catCount++;
        }
    
        System.out.println("\n--- Clinic Report ---");
        System.out.println("Clinic Name: " + clinicName);
        System.out.println("Total Pets: " + pets.size());
        System.out.println("Dogs: " + dogCount);
        System.out.println("Cats: " + catCount);
    
        System.out.println("Colors of pets in clinic:");
        for (Pet pet : pets) {
            System.out.println("- " + pet.color);
        }
        System.out.println("----------------------\n");
    }

    public void modifyPet(int id, String file, Scanner sc) throws IOException {
        List<Pet> pets = loadPets(PETS_FILE); 
        if (id <= 0 || id > pets.size()) {
            System.out.println("Invalid ID.");
            return;
        }

        Pet pet = pets.get(id - 1);

        System.out.print("New Name: "); pet.name = sc.nextLine();
        System.out.print("New Age: "); pet.age = sc.nextInt(); sc.nextLine();
        System.out.print("New Color: "); pet.color = sc.nextLine();
        System.out.print("New Weight: "); pet.weight = sc.nextDouble(); sc.nextLine();
        System.out.print("New Breed: "); pet.breed = sc.nextLine();

        if (pet instanceof Dog) {
            pets.set(id - 1, new Dog(pet.name, pet.age, pet.color, pet.weight, pet.breed));
        } else {
            pets.set(id - 1, new Cat(pet.name, pet.age, pet.color, pet.weight, pet.breed));
        }

        savePetsToFile(pets, PETS_FILE);
        System.out.println("Pet info updated.");
    }

    public void searchPets(String keyword) throws IOException {
        List<Pet> pets = loadPets(PETS_FILE);
        boolean found = false;
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            boolean match = p.name.equalsIgnoreCase(keyword) ||
                            p.color.equalsIgnoreCase(keyword) ||
                            String.valueOf(p.age).equals(keyword) ||
                            p.breed.equalsIgnoreCase(keyword) ||
                            (p instanceof Dog && ((Dog) p).speak().toLowerCase().contains(keyword.toLowerCase())) ||
                            (p instanceof Cat && ((Cat) p).speak().toLowerCase().contains(keyword.toLowerCase()));

            if (match) {
                System.out.println((i + 1) + ". " + p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pets matched your search.");
        }
    }

    private void savePetsToFile(List<Pet> pets, String filename) throws IOException {
        // Save the list of pets to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Pet pet : pets) {
                String type = pet instanceof Dog ? "Dog" : "Cat";
                String line = type + "," + pet.name + "," + pet.age + "," + pet.color + "," + pet.weight + "," + pet.breed;
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
