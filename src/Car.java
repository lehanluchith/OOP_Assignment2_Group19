public class Car extends Vehicle{  // Inherit the Parent class
    private int numberOfSeats;

    // Creating Car constructor
    public Car(String vehicleId, String brand, String model, double baseRatePerDay, int numberOfSeats){
        super(vehicleId, brand, model, baseRatePerDay);

        // If enter negative value
        if (numberOfSeats < 1){
            System.out.println("Warning:\nNumber of seats must be at least 1.");
            this.numberOfSeats = 1;  // Set Default Value
        }else {
            this.numberOfSeats = numberOfSeats;
        }
    }

    @Override // Polymorphism (Calculate the rental Cost)
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (numberOfSeats * 200 * days);
    }

    @Override  // Polymorphism (Override the Display Details)
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Number Of Seats : " + numberOfSeats);
        System.out.println("Vehicle Type    : Car");
    }

    public int getNumberOfSeats(){
        return numberOfSeats;
    }
}

