package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Engineer;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Repair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    List<Repair> repairsList;

    public EngineerImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.repairsList = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        repairsList.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return Collections.unmodifiableList(repairsList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s %s Id: %d Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary())).append(System.lineSeparator());
        stringBuilder.append("Corps: ").append(corp).append(System.lineSeparator());
        stringBuilder.append("Repairs:").append(System.lineSeparator());
        for (Repair repair : repairsList) {
            stringBuilder.append(" ").append(repair.toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}


