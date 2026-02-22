public class Dog extends Pet {
    public Dog(String name, int age, String color, double weight, String breed) {
        // Call the parent constructor
        super(name, age, color, weight, breed);
    }

    @Override
    public String speak() {
        // Return a string representing the dog's speech
        return "Woof! \"I am " + name + ", " + age + " year old " + breed + ".";
    }

    @Override
    public String toString() {
        // Return a string representation of the dog
        return "Dog: " + super.toString();
    }
}
