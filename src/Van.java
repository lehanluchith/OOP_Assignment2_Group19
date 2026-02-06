public class Van extends Vehicle {  // Inherit the Parent class
    private double cargoCapacityKg;

    //Creating Van constructor
    public Van(String vehicleId, String brand, String model, double baseRatePerDay, double cargoCapacityKg){
        super(vehicleId, brand, model, baseRatePerDay);

        // If enter negative value
        if (cargoCapacityKg <= 0){
            System.out.println("Warning:\nCargo capacity must be positive.");
            this.cargoCapacityKg = 50; // Set Default Value
        }else {
            this.cargoCapacityKg = cargoCapacityKg;
        }
    }

    @Override  // Polymorphism (Calculate the rental Cost)
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (cargoCapacityKg * 0.2 *days);
    }

    @Override  // Polymorphism (Override the Display Details)
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Cargo Capacity (Kg) : " + cargoCapacityKg);
        System.out.println("Vehicle Type        : Van");
    }

    public double getCargoCapacityKg(){
        return cargoCapacityKg;
    }
}
