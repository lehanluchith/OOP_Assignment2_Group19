# Vehicle Rental Management System

## About the Project

This is a console-based Vehicle Rental Management System developed using Java and Object-Oriented Programming (OOP) concepts.

The system allows users to add different types of vehicles, rent and return them, search by ID, remove vehicles, and track total rental income. All data is saved in a text file, so the system remembers information even after it is closed.


---

## What This System Can Do

- Add new vehicles (Bike, Car, Van)
- View all vehicles in the system
- Rent a vehicle
- Return a rented vehicle
- Search for a vehicle using its ID
- Remove a vehicle
- View total rental income
- Automatically save and load data

---

## OOP Concepts Applied
 

**Abstraction**  
The `Vehicle` class is created as an abstract class with an abstract method `calculateRentalCost()`.

**Inheritance**  
`Bike`, `Car`, and `Van` extend the `Vehicle` class.

**Polymorphism**  
Each vehicle type calculates rental cost differently by overriding the `calculateRentalCost()` method.

**Encapsulation**  
All attributes are private and accessed using getters and setters. Validation is handled inside constructors and setter methods.

---

## Rental Cost Logic

Each vehicle type has its own pricing formula:

- **Bike** - Based on engine capacity  
- **Car** - Based on number of seats  
- **Van** - Based on cargo capacity  

This makes the system flexible and realistic.

---

## Data Storage

The system uses a file called:

```
rental_data.txt
```

The file stores:
- Vehicle details
- Availability status
- Total rental income

Data is:
- Loaded when the program starts
- Saved automatically after updates
- Saved when the system exits

---

## Error Handling

The system handles:
- Invalid numeric inputs
- Duplicate vehicle IDs
- Incorrect menu selections
- File reading/writing errors

---

## Technologies Used

- Java
- Object-Oriented Programming
- ArrayList
- File Handling
- Exception Handling
- IntelliJ IDEA
- Git & GitHub

---

## How to Run

1. Clone the repository:
   ```
   git clone <your-repository-link>
   ```

2. Open in IntelliJ IDEA or any Java IDE.

3. Run:
   ```
   Main.java
   ```

---


