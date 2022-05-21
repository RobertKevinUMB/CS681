package edu.umb.cs681.hw02;
import java.util.*;

public class Car {

    private String make; 
    private String model;
    private int mileage;
    private int year;
    private int price;
    private int dominationCount;

    public Car(String make, String model, int year, int mileage, int price,int dominationCount) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.dominationCount=dominationCount;
    }
    public int getDominationCount() {
        return dominationCount;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    public int getMileage() {
        return mileage;
    }

    public int setDominationCount(int dominationCount ) {
        return this.dominationCount;
    }
    public String setMake(String make) {
        return this.make;
    }
    public String setModel(String model) {
        return this.model;
    }

    public int setYear(int year) {
        return this.year;
    }
    public int setPrice(int price) {
        return this.price;
    }
    public int setMileage(int mileage) {
        return this.mileage;
    }

   
    @Override
    public boolean equals(Object obj) {
        Car car = (Car) obj;
        if(make.equals(car.getMake()) && model.equals(car.getModel()) && year==car.getYear() && mileage==car.getMileage() && price==car.getPrice() && dominationCount==car.getDominationCount())
            return true;
        return false;
    }

    

    public static void main(String[] args) {
        List<Car> car_List = new ArrayList();
        car_List.add(new Car("Ford", "Mustang", 2021,30000,20000,1));
        car_List.add(new Car("Srt", "Dodge", 2019,40000,18000,3));
        car_List.add(new Car("BMW", "320d",2019, 45000, 22000,2));
        car_List.add(new Car("Audi", "A4", 2022,46000,22000,4));



        int min_Price = car_List.stream().map((Car car) -> car.getPrice()).reduce(1000000000, (ans, price)->price>ans ? ans : price);
        System.out.println("Cheap priced car: "+min_Price);

        int max_Price = car_List.stream().map((Car car) -> car.getPrice()).reduce(0, (ans,price)->ans > price ? ans : price);
        System.out.println("Highest priced car: "+max_Price);


        Integer avgPrice = car_List.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0, (result,price) -> result+price, (finalResult, intermediateResult) -> finalResult)/car_List.size();

        System.out.println("Avg price of  Cars: " + avgPrice);
    }

}
