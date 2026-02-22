public abstract class Pet {
    public String name; // Pet's name
    public int age; // Pet's age
    public String color; // Pet's color
    public double weight; // Pet's weight
    public String breed; // Pet's breed

    public Pet(String name, int age, String color, double weight, String breed) {
        // Initialize pet attributes
        this.name = name;
        this.age = age;
        this.color = color;
        this.weight = weight;
        this.breed = breed;
    }

    public abstract String speak(); // Abstract method for pet's speech

    public String toString() {
        // Return a string representation of the pet
        return name + ", Age: " + age + ", Color: " + color + ", Weight: " + weight + "kg, Breed: " + breed;
    }
}