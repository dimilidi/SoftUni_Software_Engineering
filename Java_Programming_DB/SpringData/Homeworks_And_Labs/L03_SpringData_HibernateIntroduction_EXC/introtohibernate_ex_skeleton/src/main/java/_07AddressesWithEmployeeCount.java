/*_07AddressesWithEmployeeCount
Find all addresses, ordered by the number of employees who live there (descending).
Take only the first 10 addresses and print their address text, town name and employee count.*/


import entities.Address;

import javax.persistence.EntityManager;

public class _07AddressesWithEmployeeCount {

    public static final String FIND_ALL_ADDRESSES = "FROM Address a ORDER BY a.employees.size DESC";

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();

        manager.createQuery(FIND_ALL_ADDRESSES, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);


        manager.getTransaction().commit();
        manager.close();
        JpaUtil.closeFactory();
    }
}
