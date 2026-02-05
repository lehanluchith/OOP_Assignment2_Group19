public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    // creating Constructor
    public Vehicle(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = true;    // default to available

    }

    // creating Getters
    public String getVehicleId() {
        return vehicleId;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public double getBaseRatePerDay() {
        return baseRatePerDay;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    // creating Setters
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // creating method to display vehicle details
    public void displayDetails() {
        System.out.println("ID: " + vehicleId +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nRate (Rs.): " + baseRatePerDay +
                "\nAvailable: " + (isAvailable ? "Yes" : "No"));

    }

    // creating method to rent a vehicle
    public void rentVehicle() {
        this.isAvailable = false;
    }

    // creating method to return a vehicle
    public void returnVehicle() {
        this.isAvailable = true;
    }

    // creating abstract method to be implemented by subclasses
    public abstract double calculateRentalCost(int days);

}
