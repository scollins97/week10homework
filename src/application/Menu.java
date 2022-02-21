package application;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.CarsDao;
import entities.Car;
public class Menu {
	//an instance of a connection that handles the cars table
	private CarsDao carsDao = new CarsDao();
	//scanner to be used throughout entire menu Class
	private Scanner scanner = new Scanner(System.in);
	//menu list of options
	private List<String> options = Arrays.asList(
			"Display all Cars",
			"Display a Car",
			"Add a Car",
			"Delete a Car",
			"Update Zero to Sixty on a Car",
			"Quit");
	//this is the menu, once 6 is entered in, it will stop, ending the program
	public void start() {
		int selection;
		do {
			printOptions();
			selection = scanner.nextInt();
			scanner.nextLine();
			try {
				if(selection == 1) {
					displayCars();
				}else if(selection == 2) {
					displayACar();
				}else if(selection == 3) {
					addCar();
				}else if(selection == 4) {
					deleteCar();
				}else if(selection == 5) {
					updateTime();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Press Enter to Continue");
			scanner.nextLine();
		}while(selection != 6);
	}
	//displays all cars in the data base in the format year, make, and model
	private void displayCars() throws SQLException {
		List<Car> cars = carsDao.getCars();
		for(Car car : cars) {
			System.out.println(car.getCarId() + ") "
		+ car.getYear()
		+ " " + car.getMake()
		+ " " + car.getModel()); 
		}
	}
	//displays a car selected by its id in the database. Prints out all information of the car
	private void displayACar() throws SQLException {
		System.out.print("Enter the ID of the car: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Car car = carsDao.getCarById(id);
		if(car != null) {
		System.out.println("Year: " + car.getYear()
		+ "\nMake: "+ car.getMake()
		+ "\nModel: " + car.getModel()
		+ "\nTrim: " + car.getTrim()
		+ "\nZero to Sixty(in seconds): " + car.getZeroToSixty()
		+ "\n_______________________________________");
		}else {
			//if the getCarById does returns as null, this will print out. 
			System.out.println("This car does not exist!");
		}
	}
	//method for the start method that prints out the menu options
	private void printOptions() {
		System.out.println("Make a selection: \n_______________________________________");
		for(int i = 0; i < options.size(); i ++) {
			System.out.println("(" + (i + 1) + ") " + options.get(i));
		}
	}
	//adds a car to the database 
	private void addCar() throws SQLException {
		System.out.print("Enter the Year of the car: ");
		String year = scanner.next();
		System.out.print("Enter the Make of the car: ");
		String make = scanner.next();
		scanner.nextLine();
		System.out.print("Enter the Model of the car: ");
		String model = scanner.nextLine();
		System.out.print("Enter the Trim of the car: ");
		String trim = scanner.next();
		scanner.nextLine();
		carsDao.createNewCar(year,make,model,trim);
	}
	//deletes a car from the database
	private void deleteCar() throws SQLException {
		System.out.print("Enter the ID of the car you wish to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		carsDao.deleteCarById(id);
	}
	//if the user wants to add a cars zero to sixty time, they can do so here
	private void updateTime() throws SQLException {
		System.out.println("Enter the car ID that you want to update the time on: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the new time (in seconds): ");
		double time = scanner.nextDouble();
		scanner.nextLine();
		carsDao.updateTimeByCarId(id, time);
	}	
}
