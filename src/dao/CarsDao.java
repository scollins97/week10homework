package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Car;

public class CarsDao {

	private Connection connection;
	private final String GET_CARS_QUERY = "SELECT * FROM cars";
	private final String GET_CAR_BY_ID_QUERY = "SELECT * FROM cars WHERE id = ?";
	private final String CREATE_NEW_CAR_QUERY = "INSERT INTO cars("
			+ " year_made,"
			+ " make,"
			+ " model,"
			+ " trim)"
			+ "VALUES(?,?,?,?)";
	private final String DELETE_CAR_BY_ID_QUERY = "DELETE FROM cars WHERE id = ?";
	private final String UPDATE_0_TO_60_TIME_BY_CAR_ID_QUERY = "UPDATE cars SET zero_to_sixty = ? WHERE id = ?";
	
	public CarsDao() {
		connection = DB_Connection.getConnection();
	}
	
	public List<Car> getCars() throws SQLException{
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(GET_CARS_QUERY); 
		List<Car> cars = new ArrayList<Car>();
		while(rs.next()) {
			cars.add(new Car(
					rs.getInt(1), 
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getDouble(6)));
		}
		return cars;
	}
	//retrieves a row from the database using the id passed in as the primary key
	public Car getCarById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Car car = new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
			return car;
		}else {
			return null;
		}
	}
	//receives input from the menu and creates a new row in the cars table
	public void createNewCar(String year, String make, String model, String trim) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CAR_QUERY);
		ps.setString(1, year);
		ps.setString(2, make);
		ps.setString(3, model);
		ps.setString(4, trim);
		ps.executeUpdate();
	}
	//deletes the a row from the database that matches the id number passed in
	public void deleteCarById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CAR_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	//updates the zero_to_sixty column on a car with the given id number
	public void updateTimeByCarId(int id, double time) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_0_TO_60_TIME_BY_CAR_ID_QUERY);
		ps.setDouble(1, time);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
}
