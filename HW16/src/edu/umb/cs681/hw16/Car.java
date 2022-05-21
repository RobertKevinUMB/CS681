package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String make;
    private String model;
    private int mileage;
    private int year;
    private float price;
    private int dominationCount;

    public Car(String make, String model, int year, int mileage, float price, int dominationCount) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.dominationCount = dominationCount;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }

    public int getMileage() {
        return this.mileage;
    }

    public static void main(String[] args) {


        List<Car> carList = new ArrayList();
        carList.add(new Car("BMW", "5 series", 2022, 16, 60000, 1));
        carList.add(new Car("Benz", "AMG", 2018, 15, 40000, 3));
        carList.add(new Car("Ford", "Mustang", 2010, 13, 30000, 2));
        carList.add(new Car("KIA", "Seltos", 2016, 20, 20000, 4));
     

        int Domination = carList.stream().parallel().map((Car car) -> car.getYear())
                .reduce(0, (result, year) ->  result > year ? result : year);

        System.out.println("Newest car on the list: " + Domination);

        int Mileage = carList.stream().parallel().map((Car car) -> car.getMileage())
                .reduce(100, (result, mileage) -> mileage > result ? result: mileage);

        System.out.println("Leats mileage car on the list : " + Mileage + "mpg");
        
        float Price = carList.stream().parallel().map((Car car) -> car.getPrice())
                .reduce((float) 0, (result, price) ->  result > price ? result : price);

        System.out.println("Most expensive car in the list has price = $" + Price);

    }
}
