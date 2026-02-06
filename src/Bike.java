public class Bike extends Vehicle {  // Inherit the Parent class
    private int engineCapacityCC;

    // Creating Bike constructor
    public Bike(String vehicleId, String brand, String model, double baseRatePerDay, int engineCapacityCC){
        super(vehicleId, brand, model,baseRatePerDay);

        // If enter negative value
        if (engineCapacityCC <= 0){
            System.out.println("Warning:\nEngine capacity must be positive.");
            this.engineCapacityCC = 100;  // Set Default Value
        }else {
            this.engineCapacityCC = engineCapacityCC;
        }
    }

    @Override  // Polymorphism (Calculate the rental Cost)
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (engineCapacityCC * 0.5 *days);
    }

    @Override  // Polymorphism (Override the Display Details)
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Engine Capacity (CC) : " + engineCapacityCC);
        System.out.println("Vehicle Type         : Bike");
    }

    public int getEngineCapacityCC(){
        return engineCapacityCC;
    }
}

