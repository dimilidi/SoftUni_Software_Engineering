package service;

import entities._01Gringotts.WizardDeposit;
import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import static constants.Constants.AGE;
import static constants.Constants.LAST_NAME;
import static enums.PersistenceUnitName.GRINGOTTS;

public class GringottsService {
    private static GringottsService instance;

    private GringottsService() {}

    public static GringottsService getInstance() {
        if (instance == null) {
            instance = new GringottsService();
        }
        return instance;
    }

    public void executeTaskOne() {
        EntityManager manager = JpaUtil.getDBConnection(GRINGOTTS.getPersistenceUnitName());
        manager.getTransaction().begin();

        final WizardDeposit wizardDeposit = new WizardDeposit(LAST_NAME, AGE);
        manager.persist(wizardDeposit);

        manager.getTransaction().commit();
    }
}
