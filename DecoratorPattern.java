abstract class Pizza {
    String description= "unkown pizza";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}

//decorator will extends pizza will not have a constructor so they need getDescription implementation,
// along side the main cost implimintation 

abstract class PizzaDecorator extends Pizza{
    public abstract String getDescription();
}

//base concrete class will have a constructor filling the discription
class ThinPizza extends Pizza{
    public ThinPizza (){
        description = "Thin Crust";
    }

    @Override
    public double cost() {
        return 10;
    }
}

class ThickPizza extends Pizza{
    public ThickPizza (){
        description = "Thick Crust";
    }

    @Override
    public double cost() {
        return 12;
    }
}

//concrete decorators will extend the decorator to add the base attribute
class Mozzarella extends PizzaDecorator{
    Pizza pizza;

    public Mozzarella(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", mozzarella";
    }

    @Override
    public double cost() {
        return 4 + pizza.cost();
    }
}

class Pepperoni extends PizzaDecorator {
    Pizza pizza;

    public Pepperoni(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", pepperoni";
    }

    @Override
    public double cost() {
        return 3 + pizza.cost();
    }
}

class Chicken extends PizzaDecorator {
    Pizza pizza;

    public Chicken(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Chicken";
    }

    @Override
    public double cost() {
        return 3.5 + pizza.cost();
    }
}

class Mushrooms extends PizzaDecorator {
    Pizza pizza;

    public Mushrooms(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", mushrooms";
    }

    @Override
    public double cost() {
        return 2 + pizza.cost();
    }
}

class Olives extends PizzaDecorator {
    Pizza pizza;

    public Olives(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", olives";
    }

    @Override
    public double cost() {
        return 1.5 + pizza.cost();
    }
}

class BellPeppers extends PizzaDecorator {
    Pizza pizza;

    public BellPeppers(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", bell peppers";
    }

    @Override
    public double cost() {
        return 2.5 + pizza.cost();
    }
}



public class DecoratorPattern {
    public static void main(String[] args) {
        Pizza plain = new ThinPizza(); 
        
        Pizza speacialPizza = new ThinPizza();
        speacialPizza = new Mozzarella(speacialPizza);
        speacialPizza = new Mushrooms(speacialPizza);
        speacialPizza = new BellPeppers(speacialPizza);

        Pizza fattyPizza = new ThickPizza();
        fattyPizza = new Mozzarella(fattyPizza);
        fattyPizza = new Mushrooms(fattyPizza);
        fattyPizza = new BellPeppers(fattyPizza);

        System.out.println(plain.getDescription() + "   " + plain.cost() + " $" );
        System.out.println(speacialPizza.getDescription() + "   " + speacialPizza.cost() + " $" );
        System.out.println(fattyPizza.getDescription() + "   " + fattyPizza.cost() + " $" );
    }
}
