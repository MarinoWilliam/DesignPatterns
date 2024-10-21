// Revenue Strategy interface (family of algorithms)
interface RevenueStrategy {
    double calculateRevenue(double quantityOfWork, int numberOfEmployees, double totalSalaries);
}

// Concrete Strategies for different types of Corporates
class SoftwareRevenue implements RevenueStrategy {
    @Override
    public double calculateRevenue(double quantityOfWork, int numberOfEmployees, double totalSalaries) {
        return (quantityOfWork * 2000) - totalSalaries;
    }
}

class ConstructionRevenue implements RevenueStrategy {
    @Override
    public double calculateRevenue(double quantityOfWork, int numberOfEmployees, double totalSalaries) {
        return (quantityOfWork * 1000) - totalSalaries + (numberOfEmployees * 500);
    }
}

class RetailRevenue implements RevenueStrategy {
    @Override
    public double calculateRevenue(double quantityOfWork, int numberOfEmployees, double totalSalaries) {
        return (quantityOfWork * 500) + (numberOfEmployees * 200) - totalSalaries * 15;
    }
}

class DoingNothing implements RevenueStrategy {
    @Override
    public double calculateRevenue(double quantityOfWork, int numberOfEmployees, double totalSalaries) {
        return 0;
    }
}

// Superclass Corporate
class Corporate {
    private String name;
    private int numberOfEmployees;
    private String location;
    private double totalSalaries;
    private String slogan;
    private RevenueStrategy revenueStrategy;

    public Corporate(String name, int numberOfEmployees, String location, double totalSalaries, String slogan, RevenueStrategy revenueStrategy) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.location = location;
        this.totalSalaries = totalSalaries;
        this.slogan = slogan;
        this.revenueStrategy = revenueStrategy;
    }

    // Method to set/change revenue strategy at runtime
    public void setRevenueStrategy(RevenueStrategy strategy) {
        this.revenueStrategy = strategy;
    }

    // Main method to calculate the revenue
    public double calculateRevenue(double quantityOfWork) {
        if (revenueStrategy == null) {
            throw new IllegalStateException("Revenue strategy is not defined");
        }
        return revenueStrategy.calculateRevenue(quantityOfWork, numberOfEmployees, totalSalaries);
    }

    public void displaySlogan() {
        System.out.println(slogan);
    }
}

// Concrete Corporate Classes
class SoftwareCompany extends Corporate {
    public SoftwareCompany(String name, int numberOfEmployees, String location, double totalSalaries, String slogan) {
        super(name, numberOfEmployees, location, totalSalaries, slogan, new SoftwareRevenue());
    }
}

class ConstructionCompany extends Corporate {
    public ConstructionCompany(String name, int numberOfEmployees, String location, double totalSalaries, String slogan) {
        super(name, numberOfEmployees, location, totalSalaries, slogan, new ConstructionRevenue());
    }
}

class RetailCompany extends Corporate {
    public RetailCompany(String name, int numberOfEmployees, String location, double totalSalaries) {
        super(name, numberOfEmployees, location, totalSalaries, "Shopping all day!", new RetailRevenue());
    }
}

// Main class to demonstrate the pattern
public class StrategyPattern {
    public static void main(String[] args) {
        SoftwareCompany macrosoft = new SoftwareCompany("Tech Corp", 50, "New York", 500000, "coding all the way");
        System.out.println("Software Company Revenue: " + macrosoft.calculateRevenue(2000));

        ConstructionCompany aliens = new ConstructionCompany("Build Corp", 200, "San Francisco", 1000000, "building future");
        System.out.println("Construction Company Revenue: " + aliens.calculateRevenue(1500));

        RetailCompany alimama = new RetailCompany("Retail Corp", 100, "Los Angeles", 300000);
        System.out.println("Retail Company Revenue: " + alimama.calculateRevenue(4000));

        // Change strategy at runtime
        macrosoft.setRevenueStrategy(new ConstructionRevenue());
        System.out.println("Software Company with Construction Revenue Strategy: " + macrosoft.calculateRevenue(1500));
    }
}
