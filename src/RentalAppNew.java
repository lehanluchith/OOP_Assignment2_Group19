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

    // creating addVehicle method
    private static void addVehicle() {
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

                System.out.print("Enter Vehicle Model           : ");
                String model = scanner.nextLine().trim();

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
    private static void viewAllVehicles() {
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
    private static void rentVehicle() {
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
    private static void returnVehicle() {
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

                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("Vehicle returned successfully!");
                }
                return;
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle not found. Returning Vehicle failed.");
    }

    // creating searchVehicle method
    private static void searchVehicle() {
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
    private static void viewTotalRentalIncome() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("Total Rental Income (Rs.)     : %.2f%n" , totalRentalIncome);    // formats to 2 decimel places
    }

    // File Handling
    // creating saveData method
    private static void saveData() {
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
    private static void loadData() {
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

    public static void main(String[] args) {
        // loading existing data from the text file when the program starts
        loadData();

        boolean running = true;
        while (running) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                VEHICLE RENTAL SYSTEM                                ");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("    1. Add a Vehicle");
            System.out.println("    2. View All Vehicles");
            System.out.println("    3. Rent a Vehicle");
            System.out.println("    4. Return a Vehicle");
            System.out.println("    5. Search Vehicle by ID");
            System.out.println("    6. View Total Rental Income");
            System.out.println("    7. Exit");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("Enter your choice (1-7)       : ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());    // reads the entire input and converts it to an integer

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
                        searchVehicle();
                        break;

                    case 6:
                        viewTotalRentalIncome();
                        break;

                    case 7:
                        saveData();    // saves data before exiting
                        running = false;
                        break;

                    default:
                        System.out.println("-------------------------------------------------------------------------------------");
                        System.out.println("ERROR: Invalid selection. Try again.");

                }
            } catch (NumberFormatException e) {
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("ERROR: Please enter a valid number for menu selection.");

            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("                        System closed. All Data saved.                               ");
        System.out.println("-------------------------------------------------------------------------------------");

    }

}
