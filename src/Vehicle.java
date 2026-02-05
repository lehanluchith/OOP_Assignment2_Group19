import java.sql.SQLOutput;

public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    // creating Vehicle Constructor
    public Vehicle(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = true;    // default to available

    }

    // creating Getter and Setter for vehicleID
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    // creating Getter and Setter for brand
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // creating Getter and Setter for model
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    // creating Getter and Setter for baseRatePerDay
    public double getBaseRatePerDay() {
        return baseRatePerDay;
    }
    public void setBaseRatePerDay(double baseRatePerDay) {
        if (baseRatePerDay < 0) {
            System.out.println("Warning: Base Rate cannot be negative.");
            this.baseRatePerDay = 0;
        } else {
            this.baseRatePerDay = baseRatePerDay;
        }
    }

    // creating Getter and Setter for isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // creating method to display vehicle details
    public void displayDetails() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Vehicle ID         : " + vehicleId);
        System.out.println("Vehicle Brand      : " + brand);
        System.out.println("Vehicle Model      : " + model);
        System.out.println("Rate per Day (Rs.) : " + baseRatePerDay);
        System.out.println("Available          : " + (isAvailable ? "Yes" : "No"));    // Turnery Operator: alternative to the if-else statement.
        System.out.println("--------------------------------------------------------------------");

    }

    // creating method to rent a vehicle
    public void rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Vehicle " + vehicleId + " has been successfully rented. SAFE RIDE!");
        } else {
            System.out.println("Vehicle " + vehicleId + " is currently unavailable. WE ARE SORRY!");
        }
    }

    // creating method to return a vehicle
    public void returnVehicle() {
        isAvailable = true;
        System.out.println("Vehicle " + vehicleId + " has been returned. THANK YOU!");
    }

    // creating abstract method to be implemented by subclasses
    public abstract double calculateRentalCost(int days);

}
