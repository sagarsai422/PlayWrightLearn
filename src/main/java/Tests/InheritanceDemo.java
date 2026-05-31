package Tests;

public class InheritanceDemo {
    public static void main(String[] args) {
        Animal a = new Animal("Generic");
        Animal d = new Dog("Rex");
        Animal c = new Cat("Mittens");

        a.speak();
        d.speak();
        c.speak();

        if (d instanceof Dog) {
            ((Dog) d).fetch();
        }
        if (c instanceof Cat) {
            ((Cat) c).scratch();
        }

        Animal[] animals = { new Dog("Buddy"), new Cat("Whiskers"), new Animal("Anon") };
        for (Animal animal : animals) {
            animal.speak();
        }
    }
}
