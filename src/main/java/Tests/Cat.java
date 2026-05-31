package Tests;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(getName() + " says: Meow!");
    }

    public void scratch() {
        System.out.println(getName() + " is scratching.");
    }
}
