// Inheriting the Vehicle class
public class Bike extends Vehicle {
    private int engineCapacityCC;

    // Creating Bike constructor
    public Bike(String vehicleId, String brand, String model, double baseRatePerDay, int engineCapacityCC){
        super(vehicleId, brand, model,baseRatePerDay);

        // setting a default value for engineCapacityCC
        if (engineCapacityCC < 50){
            System.out.println("Warning: The minimum Engine Capacity of a Bike must be 50 cc.");
            this.engineCapacityCC = 50;
        }else {
            this.engineCapacityCC = engineCapacityCC;
        }
    }

    // Polymorphism: Overrides calculateRentalCost
    @Override
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (engineCapacityCC * 0.5 * days);
    }

    // Polymorphism: Overrides displayDetails
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Engine Capacity (cc) : " + engineCapacityCC);
        System.out.println("Vehicle Type         : Bike");
    }

    public int getEngineCapacityCC(){
        return engineCapacityCC;
    }
}

