package entities;

public class Car {

	private int carId;
	private String year;
	private String make;
	private String model;
	private String trim;
	private double zeroToSixty;
	
	public Car (int carId, String year, String make, String model, String trim, double zeroToSixty) {
		this.setCarId(carId);
		this.setYear(year);
		this.setMake(make);
		this.setModel(model);
		this.setTrim(trim);
		this.setZeroToSixty(zeroToSixty);
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public double getZeroToSixty() {
		return zeroToSixty;
	}

	public void setZeroToSixty(double zeroToSixty) {
		this.zeroToSixty = zeroToSixty;
	}
}
