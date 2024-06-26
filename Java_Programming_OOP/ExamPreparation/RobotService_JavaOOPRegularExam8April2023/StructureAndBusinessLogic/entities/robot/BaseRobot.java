package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot;

import static ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.common.ExceptionMessages.*;

public abstract class BaseRobot implements Robot{


    private String name;
    private String kind;
    private int kilograms;
    private double price;

    protected BaseRobot(String name, String kind, int kilograms, double price) {
        setName(name);
        setKind(kind);
        this.kilograms = kilograms;
        setPrice(price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ROBOT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    protected void increaseKilograms(int value) {
        kilograms += value;
    }

    private void setKind(String kind) {
        if(kind == null || kind.trim().isEmpty()) {
            throw new NullPointerException(ROBOT_KIND_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }


    private void setPrice(double price) {
        if(price <= 0) {
            throw new IllegalArgumentException(ROBOT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.price = price;
    }
}
