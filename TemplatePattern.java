import java.util.Scanner;

abstract class Pastry {
    final void prepareRecipe() {
        mixingTheDough();
        chilling();
        RollingOut();
        addFilling();
        bake();
        if (wantToppings()) {
            addTopping();
        }
        System.out.println("Thanks for passing by, have a nice day :)");
    }

    // abstract classes to be filled for each subclass implementing the algorithm

    abstract void mixingTheDough();

    abstract void RollingOut();

    // implemented parts of the algorithm
    void chilling() {
        System.out.println("let the dough chill");
    }

    void bake() {
        System.out.println("bake in the oven");
    }

    // hooks that are optional

    // A function that does nothing
    void addFilling() {
    }

    // a function that is getting invoked only if you override the condition method
    boolean wantToppings() {
        return false;
    }

    void addTopping() {
        System.out.println("adding Toppings");
    }
}

class Croissant extends Pastry {
    private Scanner scanner = new Scanner(System.in);

    @Override
    void mixingTheDough() {
        System.out.println("Mixing the croissant dough");
    }

    @Override
    void RollingOut() {
        System.out.println("Rolling out the croissant dough");
    }

    @Override
    void addFilling() {
        System.out.println("Adding filling to the croissant");
    }

    @Override
    boolean wantToppings() {
        System.out.print("Do you want to add toppings? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

}

class Brioche extends Pastry {
    @Override
    void mixingTheDough() {
        System.out.println("Mixing the brioche dough");
    }

    @Override
    void RollingOut() {
        System.out.println("Rolling out the brioche dough");
    }
}

public class TemplatePattern {
    public static void main(String[] args) {
        Pastry croissant = new Croissant();
        System.out.println("Preparing Croissant:");
        croissant.prepareRecipe();

        System.out.println("\nPreparing Brioche:");
        Pastry brioche = new Brioche();
        brioche.prepareRecipe();
    }
}
