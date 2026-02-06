// inheriting the Vehicle class
public class Van extends Vehicle {
    private double cargoCapacityKg;

    // Creating Van constructor
    public Van(String vehicleId, String brand, String model, double baseRatePerDay, double cargoCapacityKg){
        super(vehicleId, brand, model, baseRatePerDay);

        // setting a default value for cargoCapacityKg
        if (cargoCapacityKg < 350){
            System.out.println("Warning: The minimum Cargo Capacity of a Van must be 350 kg.");
            this.cargoCapacityKg = 350;
        }else {
            this.cargoCapacityKg = cargoCapacityKg;
        }
    }

    // Polymorphism: Overrides calculateRentalCost
    @Override
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (cargoCapacityKg * 0.2 *days);
    }

    // Polymorphism: Overrides the Display Details
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Cargo Capacity (kg)  : " + cargoCapacityKg);
        System.out.println("Vehicle Type         : Van");
    }

    public double getCargoCapacityKg(){
        return cargoCapacityKg;
    }
}
