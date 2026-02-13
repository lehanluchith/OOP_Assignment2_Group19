import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalAppNew {
    // creating ArrayList to store all vehicles
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    // creating variable to track total rental income
    private static double totalRentalIncome = 0.0;

    // creating constant text file
    private static final String FILE = "rental_data.txt";

    // creating Scanner object to input data
    private static Scanner scanner = new Scanner(System.in);

    // Adding getter for the scanner
    public static Scanner getScanner() {
        return scanner;
    }

    // creating addVehicle method
    public static void addVehicle() {
        try {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("    1. Bike");
            System.out.println("    2. Car");
            System.out.println("    3. Van");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("Select the Vehicle Type (1-3) : ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.println("-------------------------------------------------------------------------------------");
            if (type < 1 || type > 3 ) {
                System.out.println("ERROR: Invalid vehicle type selection.");

            } else {
                System.out.print("Enter Vehicle ID              : ");
                String id = scanner.nextLine().trim();    // trim(): removes leading and trailing whitespace

                // checks if vehicleID is empty
                if (id.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("ERROR: Vehicle ID cannot be empty. Adding Vehicle Failed.");
                    return;
                }

                // For-each Loop: v in vehicles ArrayList
                for (Vehicle v : vehicles) {

                    // equalsIgnoreCase is a case-insensitive method. checks for unique Vehicle ID
                    if (v.getVehicleId().equalsIgnoreCase(id)) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("ERROR: This Vehicle already exists in the System. Adding vehicle failed.");
                        return;
                    }
                }

                System.out.print("Enter Vehicle Brand           : ");
                String brand = scanner.nextLine().trim();

                // checks if vehicle brand is empty
                if (brand.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("ERROR: Brand cannot be empty. Adding vehicle failed");
                    return;
                }


                System.out.print("Enter Vehicle Model           : ");
                String model = scanner.nextLine().trim();

                // checks if vehicle modle is empty
                if (model.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("ERROR: Vehicle Model cannot be empty. Adding vehicle failed");
                    return;
                }

                System.out.print("Enter Base Rate per Day (Rs.) : ");
                double rate = Double.parseDouble(scanner.nextLine());    // reads the entire input and converts it to a double

                switch (type) {
                    case 1:
                        System.out.print("Enter Engine Capacity (cc)    : ");
                        int cc = Integer.parseInt(scanner.nextLine());

                        vehicles.add(new Bike(id, brand, model, rate, cc));    // creates Bike object and stores it in the vehicles
                        saveData();    // saves data

                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("New Bike added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Number of Seats         : ");
                        int seats = Integer.parseInt(scanner.nextLine());

                        vehicles.add(new Car(id, brand, model, rate, seats));    // creates Car object and stores it in the vehicles
                        saveData();    // saves data

                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("New Car added successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Cargo Capacity (kg)     : ");
                        double cargo = Double.parseDouble(scanner.nextLine());

                        vehicles.add(new Van(id, brand, model, rate, cargo));    // creates Van object and stores it in the vehicles
                        saveData();    // saves data

                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("New Van added successfully!");
                        break;

                    default:
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("ERROR: Invalid Vehicle Type.");

                }
            }
        } catch (NumberFormatException e) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("ERROR: Invalid numeric input. Adding Vehicle failed.");
        }
    }

    // creating viewAllVehicles method
    public static void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("No vehicles in the system. WE ARE SORRY!");

        } else {
            for (Vehicle v : vehicles) {
                System.out.println("-------------------------------------------------------------------------------------");
                v.displayDetails();

            }
        }
    }

    // creating rentVehicle method
    public static void rentVehicle() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.print("Enter Vehicle ID to Rent      : ");
        String id = scanner.nextLine().trim();    // trim(): removes leading and trailing whitespace

        for (Vehicle v : vehicles) {

            // checks for ID and checks for availability
            if (v.getVehicleId().equalsIgnoreCase(id)) {

                if (!v.isAvailable()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("The Vehicle already rented. WE ARE SORRY!");
                    return;
                }

                try {
                    System.out.print("Enter Number of Rental Days   : ");
                    int days = Integer.parseInt(scanner.nextLine());

                    if (days <= 0) {
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("ERROR: Rental Days must be greater than zero.");
                        return;
                    }
                    double cost = v.calculateRentalCost(days);
                    v.rentVehicle();    // sets isAvailable to false

                    totalRentalIncome += cost;
                    saveData();    // saves data

                    System.out.printf("Rental Cost (Rs.)             : %.2f%n" , cost);    // formats to 2 decimel places

                    return;

                } catch (NumberFormatException e) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("ERROR: Invalid input for Number of Rental Days");
                    return;
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle not found. Renting Vehicle failed.");
    }

    // creating returnVehicle method
    public static void returnVehicle() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.print("Enter Vehicle ID to return    : ");
        String id = scanner.nextLine().trim();    // trim(): removes leading and trailing whitespace

        for (Vehicle v : vehicles) {

            // checks for ID and calls returnVehicle method in Vehicle
            if (v.getVehicleId().equalsIgnoreCase(id)) {

                // checking if it was actually rented out
                if (v.isAvailable()) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("This Vehicle is already in the System.");

                } else {
                    v.returnVehicle();
                    saveData();    // saves data

                }
                return;
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle not found. Returning Vehicle failed.");
    }

    // creating searchVehicle method
    public static void searchVehicle() {
        System.out.print("Enter vehicle ID to search    : ");
        String id = scanner.nextLine().trim();    // trim(): removes leading and trailing whitespace

        for (Vehicle v : vehicles) {

            // checks for ID and calls displayDetails method in Vehicle
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                System.out.println("-------------------------------------------------------------------------------------");
                v.displayDetails();
                return;

            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle not found in the System.");
    }

    // creating viewTotalRentalIncome method
    public static void viewTotalRentalIncome() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("Total Rental Income (Rs.)     : %.2f%n" , totalRentalIncome);    // formats to 2 decimel places
    }

    // creating removeVehicle method
    public static void removeVehicle() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.print("Enter Vehicle ID to remove    : ");
        String id = scanner.nextLine().trim();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {

                vehicles.remove(v);   // remove from ArrayList
                saveData();           // update file

                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Vehicle removed successfully!");
                return;
            }
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle not found. Removal failed.");
    }

    // File Handling
    // creating saveData method
    public static void saveData() {
        // creating PrintWriter object. try-with-resouces ensures writer is closed automatically
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            // saving total income on first line
            writer.println(totalRentalIncome);

            for (Vehicle v : vehicles) {

                // looks for Class of the object and takes it
                String type = v.getClass().getSimpleName();

                String data = type + ","
                        + v.getVehicleId() + ","
                        + v.getBrand() + ","
                        + v.getModel() + ","
                        + v.getBaseRatePerDay() + ","
                        + v.isAvailable();

                // checking for Bike class
                if (v instanceof Bike) {
                    // downcasts and accesses the engineCapacityCC
                    data += "," + ((Bike) v).getEngineCapacityCC();

                // checking for Car class
                } else if (v instanceof Car) {
                    // downcasts and accesses the numberOfSeats
                    data += "," + ((Car) v).getNumberOfSeats();

                // checking for Van class
                } else if (v instanceof Van) {
                    // downcasts and accesses the cargoCapacityKg
                    data += "," + ((Van) v).getCargoCapacityKg();

                }

                writer.println(data);
            }
        } catch (IOException e) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("ERROR SAVING DATA: " + e.getMessage());
        }
    }

    // creating loadData method
    public static void loadData() {
        vehicles.clear();  // Clears existing vehicle list to prevent duplication when reloading data

        // creating File object
        File file = new File(FILE);

        if (!file.exists()) return;    // stops loading and starts fresh, if file doesn't exist

        // creating BufferedReader object. try-with-resouces ensures reader is closed automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // reading total rental income
            String incomeLine =  reader.readLine();

            if (incomeLine != null) {
                // assigning existing income to totalRentalIncome
                totalRentalIncome = Double.parseDouble(incomeLine);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                // creating String Array
                String[] parts = line.split(",");    // split(): uses "," as a divider

                // rebuilding the object from the text file
                String type = parts[0].trim();    // trim(): removes leading and trailing whitespace
                String id  = parts[1].trim();
                String brand = parts[2].trim();
                String model = parts[3].trim();
                double rate = Double.parseDouble(parts[4].trim());
                boolean available = Boolean.parseBoolean(parts[5].trim());

                Vehicle v = null;

                if (type.equals("Bike")) {
                    v = new Bike(id, brand, model, rate, Integer.parseInt(parts[6].trim()));    // builds a Bike using parts[6] for cc

                } else if (type.equals("Car")) {
                    v = new Car(id, brand, model, rate, Integer.parseInt(parts[6].trim()));     // builds a Car using parts[6] for seats

                } else if (type.equals("Van")) {
                    v = new Van(id, brand, model, rate, Double.parseDouble(parts[6].trim()));     // builds a Van using parts[6] for cargo

                }

                if (v != null) {
                    // restoring availability status
                    v.setAvailable(available);

                    // adding v to the vehicles ArrayList
                    vehicles.add(v);

                }
            }

        } catch (IOException e) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("FILE ERROR: The file cannot be read." + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("DATA ERROR: The file contains invalid details.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("FORMAT ERROR: Some details are missing.");

        }
    }
}
