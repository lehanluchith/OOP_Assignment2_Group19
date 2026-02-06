// Inheriting the Vehicle class
public class Car extends Vehicle{
    private int numberOfSeats;

    // Creating Car constructor
    public Car(String vehicleId, String brand, String model, double baseRatePerDay, int numberOfSeats){
        super(vehicleId, brand, model, baseRatePerDay);

        // setting a default value for numberOfSeats
        if (numberOfSeats < 2){
            System.out.println("Warning: The minimum Number of Seats of a Car must be 2.");
            this.numberOfSeats = 2;
        }else {
            this.numberOfSeats = numberOfSeats;
        }
    }

    // Polymorphism: Overrides calculateRentalCost
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (numberOfSeats * 200 * days);
    }

    // Polymorphism: Overrides displayDetails
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Number Of Seats      : " + numberOfSeats);
        System.out.println("Vehicle Type         : Car");
    }

    public int getNumberOfSeats(){
        return numberOfSeats;
    }
}
