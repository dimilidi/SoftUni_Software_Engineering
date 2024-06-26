package ExamPreparation.Classes.parrots_JavaAdvancedRegularExam19February2022;

public class Parrot {
    private String name;
    private String species;
    private boolean available;

    public Parrot(String name, String species) {
        this.name = name;
        this.species = species;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setIsAvailable(boolean isAvailable) {
        available = isAvailable;
    }

    @Override
    public String toString() {
        return  String.format("Parrot (%s): %s", species, name);
    }
}
