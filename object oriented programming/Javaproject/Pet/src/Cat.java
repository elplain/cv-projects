public class Cat extends Pet {
    public Cat(String name, int age, String color, double weight, String breed) {
        // Call the parent constructor
        super(name, age, color, weight, breed);
    }

    @Override
    public String speak() {
        // Return a string representing the cat's speech
        return "Meow! \"I am " + name + ", " + age + " year old " + breed + ".";
    }

    @Override
    public String toString() {
        // Return a string representation of the cat
        return "Cat: " + super.toString();
    }
}