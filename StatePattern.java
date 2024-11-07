import java.util.ArrayList;
import java.util.Scanner;

abstract class State {
    Cart cart;

    public State(Cart cart) {
        this.cart = cart;
    }

    void addToCart(String itemName, Double itemCost) {
        System.out.println("You can't add Items now");
    };

    void confirmOrder(Double userBank) {
        System.out.println("You can't confirm your order yet");
    };

    void cancelOrder() {
        System.out.println("You can't cancel your Order");
    };

    void deliver() {
        System.out.println("You must confirm your order first");
    };

    void receive() {
        System.out.println("You must wait for the delivery ");
    };
}

class PlacingOrder extends State {
    public PlacingOrder(Cart cart) {
        super(cart);
    }

    @Override
    public void addToCart(String itemName, Double itemCost) {
        cart.addItem(itemName);
        cart.setTotal(cart.getTotal() + itemCost);
    }

    @Override
    public void confirmOrder(Double userBalance) {
        Double total = cart.getTotal();
        if (userBalance < total) {
            System.out.println("Payment Failed please check your balance");
            cart.setState(cart.getPlacingOrderState());
        } else {
            System.out.println("your order is confirmed");
            cart.setState(cart.getDileveringOrderState());
        }
    }

    @Override
    public void cancelOrder() {
        cart.clearItems();
        cart.setTotal(0.0);
        System.out.println("Your order is canceled");
    }

}

class DileveringOrder extends State {
    public DileveringOrder(Cart cart) {
        super(cart);
    }

    @Override
    public void deliver() {
        System.out.println("your order is being dileverd");
        cart.setState(cart.getDeliveredState());
    }

}

class Delivered extends State {
    private Scanner scanner = new Scanner(System.in);

    public Delivered(Cart cart) {
        super(cart);
    }

    @Override
    public void receive() {
        System.out.println("Total cost is: " + cart.getTotal());
        System.out.print("Do you want to receive iy? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            System.out.println("thank you for shoping with us ");
            cart.setBalance(cart.getTotal() + 20.0);
        } else {
            System.out.println("You will only be charged for the delivery ");
            cart.setBalance(20.0);
        }
        cart.clearItems();
        cart.setTotal(0.0);
        cart.setState(cart.getPlacingOrderState());
    }

}

class Cart {
    State placingOrder;
    State dileveringOrder;
    State delivered;


    State state;;

    Double myBalance;
    Double total = 0.0;
    ArrayList<String> items = new ArrayList<>();

    public Cart(Double myBalance){
        this.myBalance = myBalance;
        placingOrder= new PlacingOrder(this);
        dileveringOrder= new DileveringOrder(this);
        delivered= new Delivered(this);
        state = placingOrder;
    }

    void setState(State state) {
        this.state = state;
    }

    void setTotal(Double total) {
        this.total = total;
    }

    Double getTotal() {
        return this.total;
    }

    void setBalance(Double balance) {
        this.myBalance = balance;
    }

    Double getBalance() {
        return this.myBalance;
    }

    void addItem(String item) {
        this.items.add(item);
    }

    void clearItems() {
        this.items.clear();
    }

    State getPlacingOrderState() {
        return placingOrder;
    }

    State getDileveringOrderState() {
        return dileveringOrder;
    }

    State getDeliveredState() {
        return delivered;
    }

    void addToCart(String item, Double cost){
        state.addToCart(item, cost);
    }

    void confirmOrder(){
        state.confirmOrder(myBalance);
    }

    void deliver(){
        state.deliver();
    }

    void receive(){
        state.receive();
    }
    
    void cancelOrder(){
        state.cancelOrder();
    }

}

public class StatePattern {
    public static void main(String[] args) {
        Cart cart = new Cart(100.0);
        
        System.out.println("Adding items to the cart...");
        cart.addToCart("Laptop", 50.0);
        cart.addToCart("Headphones", 30.0);
        cart.addToCart("Mouse", 20.0);
                
        cart.confirmOrder();

        cart.deliver();

        cart.receive(); 

        cart.addToCart("keyboard", 25.0);

        cart.cancelOrder();

        cart.addToCart("screen", 35.0);
        cart.confirmOrder();

    }
}
