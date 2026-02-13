public class Main {
    public static void main(String[] args) {
        // loading existing data from the text file when the program starts
        RentalAppNew.loadData();

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
            System.out.println("    7. Remove a Vehicle");
            System.out.println("    8. Exit");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("Enter your choice (1-8)       : ");

            try {
                int choice = Integer.parseInt(RentalAppNew.getScanner().nextLine());    // reads the entire input and converts it to an integer

                switch (choice) {
                    case 1:
                        RentalAppNew.addVehicle();
                        break;

                    case 2:
                        RentalAppNew.viewAllVehicles();
                        break;

                    case 3:
                        RentalAppNew.rentVehicle();
                        break;

                    case 4:
                        RentalAppNew.returnVehicle();
                        break;

                    case 5:
                        RentalAppNew.searchVehicle();
                        break;

                    case 6:
                        RentalAppNew.viewTotalRentalIncome();
                        break;

                    case 7:
                        RentalAppNew.removeVehicle();
                        break;

                    case 8:
                        RentalAppNew.saveData();
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
