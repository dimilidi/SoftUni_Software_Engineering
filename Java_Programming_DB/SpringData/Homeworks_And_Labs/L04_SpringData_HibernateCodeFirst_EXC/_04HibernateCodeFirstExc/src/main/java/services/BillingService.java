package services;

import entities._05BillsPaymentSystem.BankAccount;
import entities._05BillsPaymentSystem.CreditCard;
import entities._05BillsPaymentSystem.CreditCardType;
import entities._05BillsPaymentSystem.User;
import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import static constants.Constants.*;
import static enums.PersistenceUnitName.BILLING_SYSTEM;

public class BillingService {
    private static BillingService instance;

    private BillingService() {}

    public static BillingService getInstance() {
        if (instance == null) {
            instance = new BillingService();
        }
        return instance;
    }

    public void executeTaskFive() {
        EntityManager manager = JpaUtil.getDBConnection(BILLING_SYSTEM.getPersistenceUnitName());
        manager.getTransaction().begin();

        User user = createUser(manager);
        createBankAccount(user, manager);
        createCreditCard(user, manager);


        manager.getTransaction().commit();
    }

    private static void createCreditCard(User user, EntityManager manager) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(CARD_NUMBER);
        creditCard.setOwner(user);
        creditCard.setCreditCardType(CreditCardType.MASTERCARD);
        creditCard.setExpirationMonth(5);
        creditCard.setExpirationYear(2026);
        manager.persist(creditCard);
    }

    private static void createBankAccount(User user, EntityManager manager) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName(BANK_NAME);
        bankAccount.setNumber(BANK_NUMBER);
        bankAccount.setSWIFTCode(SWIFT_CODE);
        bankAccount.setOwner(user);
        manager.persist(bankAccount);
    }

    private static User createUser(EntityManager manager) {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        manager.persist(user);
        return user;
    }
}
