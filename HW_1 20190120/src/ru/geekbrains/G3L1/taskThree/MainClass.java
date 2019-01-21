package ru.geekbrains.G3L1.taskThree;

public class MainClass {
    public static void main(String[] args) {
        Apple a0 = new Apple();
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();
        Apple a4 = new Apple();

        Orange or0 = new Orange();
        Orange or1 = new Orange();
        Orange or2 = new Orange();
        Orange or3 = new Orange();
        Orange or4 = new Orange();
        Orange or5 = new Orange();
        Orange or6 = new Orange();
        Orange or7 = new Orange();

        Box<Apple> appleBox = new Box<Apple>();
        appleBox.add(a0,a1,a2,a3,a4);
        Box<Orange> orangeBox = new Box<Orange>();
        orangeBox.add(or0,or1,or2,or3,or4,or5,or6,or7);

        System.out.println("Коробка апельсинов весит: " + orangeBox.getWeight() + "кг.; коробка яблок весит: " + appleBox.getWeight() + "кг.");
        System.out.println();

        if (appleBox.compare(orangeBox)) {
            System.out.println("вес коробок одинаков");
        }
        else System.out.println("коробки разные по весу");

        System.out.println();

        Box<Apple> newAppleBox = new Box<Apple>();
        Box<Orange> newOOrangeBox = new Box<Orange>();

        GoTo goTo = new GoTo();
        goTo.goToFruits(appleBox, newAppleBox);
        goTo.goToFruits(orangeBox, newOOrangeBox);

        System.out.println("Старая коробка яблок теперь весит: " + appleBox.getWeight() + ", а новая весит: " + newAppleBox.getWeight());
        System.out.println("Старая коробка апельсинов теперь весит: " + orangeBox.getWeight() + ", а новая весит: " + newOOrangeBox.getWeight());
    }
}
