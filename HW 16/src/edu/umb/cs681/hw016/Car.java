package edu.umb.cs681.hw016;
import java.util.*;


public class Car {
	private String maker; 
	public Car(String maker)
	{
		this.maker = maker;
	}
	public void setMaker(String maker)
	{
		this.maker = maker;
	}
	public String getMaker()
	{
		return this.maker;
	}
	public static void main(String args[])
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car a = new Car("BMW");
		Car b = new Car("Ford");
		Car c = new Car("Benz");
		Car d = new Car("KIA");
		
		cars.add(a);
		cars.add(b);
		cars.add(c);
		cars.add(d);
		
		int carMakerCount = cars.stream()
				.parallel()
				.map((Car car)-> car.getMaker())
				.reduce(0,
				(count,carMaker)-> ++count,
				(final_result,int_result)->final_result + int_result);
		System.out.println("Number of Makers : " + carMakerCount);

		
	}
}
