package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.CollectionHierarchy;

public class AddCollection extends Collection implements Addable {

    @Override
    public int add(String item) {
        super.addLast(item);
        return super.getItems().size() - 1;
    }
}
