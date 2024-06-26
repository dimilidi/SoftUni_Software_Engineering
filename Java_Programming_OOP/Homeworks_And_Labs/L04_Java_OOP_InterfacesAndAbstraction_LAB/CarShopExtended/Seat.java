package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.CarShopExtended;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }


    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s sells for %.6f",super.toString(),getModel(), getPrice());
    }
}