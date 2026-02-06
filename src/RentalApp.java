import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RentalApp {

    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static double totalRentalIncome = 0.0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;

        while (!exit) {
            displayMenu();

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        viewAllVehicles();
                        break;
                    case 3:
                        rentVehicle();
                        break;
                    case 4:
                        returnVehicle();
                        break;
                    case 5:
                        searchVehicleById();
                        break;
                    case 6:
                        viewTotalRentalIncome();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting system. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid menu option. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine();
            }
        }
    }

    //  MENU
    private static void displayMenu() {
        System.out.println("\n--- Vehicle Rental Management System ---");
        System.out.println("1. Add a Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Rent a Vehicle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. Search Vehicle by ID");
        System.out.println("6. View Total Rental Income");
        System.out.println("7. Exit");
    }

    // ADD VEHICLE
    private static void addVehicle() {

        try {
            System.out.print("Enter Vehicle ID: ");
            String id = scanner.nextLine();

            if (findVehicleById(id) != null) {
                System.out.println("Error: Vehicle ID already exists.");
                return;
            }

            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Enter Model: ");
            String model = scanner.nextLine();

            System.out.print("Enter Base Rate Per Day: ");
            double rate = scanner.nextDouble();

            System.out.println("Select Vehicle Type:");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Van");
            int type = scanner.nextInt();

            Vehicle vehicle = null;

            switch (type) {
                case 1:
                    System.out.print("Enter Number of Seats: ");
                    int seats = scanner.nextInt();
                    vehicle = new Car(id, brand, model, rate, seats);
                    break;

                case 2:
                    System.out.print("Enter Engine Capacity (CC): ");
                    int cc = scanner.nextInt();
                    vehicle = new Bike(id, brand, model, rate, cc);
                    break;

                case 3:
                    System.out.print("Enter Cargo Capacity (Kg): ");
                    double cargo = scanner.nextDouble();
                    vehicle = new Van(id, brand, model, rate, cargo);
                    break;

                default:
                    System.out.println("Invalid vehicle type selected.");
                    return;
            }

            vehicles.add(vehicle);
            System.out.println("Vehicle added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Vehicle not added.");
            scanner.nextLine();
        }
    }

    // VIEW ALL VEHICLES
    private static void viewAllVehicles() {

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("---------------------------------");
        }
    }

    //  RENT VEHICLE
    private static void rentVehicle() {

        System.out.print("Enter Vehicle ID to rent: ");
        String id = scanner.nextLine();

        Vehicle vehicle = findVehicleById(id);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is already rented.");
            return;
        }

        try {
            System.out.print("Enter number of rental days: ");
            int days = scanner.nextInt();

            if (days <= 0) {
                System.out.println("Rental days must be greater than zero.");
                return;
            }

            double cost = vehicle.calculateRentalCost(days);
            vehicle.rentVehicle();
            totalRentalIncome += cost;

            System.out.println("Rental Cost: Rs. " + cost);

        } catch (InputMismatchException e) {
            System.out.println("Invalid number of days.");
            scanner.nextLine();
        }
    }

    // RETURN VEHICLE
    private static void returnVehicle() {

        System.out.print("Enter Vehicle ID to return: ");
        String id = scanner.nextLine();

        Vehicle vehicle = findVehicleById(id);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (vehicle.isAvailable()) {
            System.out.println("This vehicle is not currently rented.");
            return;
        }

        vehicle.returnVehicle();
    }

    //  SEARCH
    private static void searchVehicleById() {

        System.out.print("Enter Vehicle ID to search: ");
        String id = scanner.nextLine();

        Vehicle vehicle = findVehicleById(id);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicle.displayDetails();
        }
    }

    //  TOTAL INCOME
    private static void viewTotalRentalIncome() {
        System.out.println("Total Rental Income: Rs. " + totalRentalIncome);
    }

    //  HELPER METHOD
    private static Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }
}
