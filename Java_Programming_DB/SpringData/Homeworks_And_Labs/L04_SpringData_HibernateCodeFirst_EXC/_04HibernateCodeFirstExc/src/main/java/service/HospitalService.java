package service;

import entities._04Hospital.*;
import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import java.time.LocalDate;
import java.util.Scanner;

import static constants.Constants.*;
import static enums.PersistenceUnitName.HOSPITAL;

public class HospitalService {
    private static HospitalService instance;

    private HospitalService() {
    }

    public static HospitalService getInstance() {
        if (instance == null) {
            instance = new HospitalService();
        }
        return instance;
    }

    /**
     * Part one: Populating the DB<br>
     * Populates the DB with patients, diagnoses and medicines.<br><br>
     * Part two: Create a visitation<br>
     */
    public void executeTaskFour() {
        EntityManager manager = JpaUtil.getDBConnection(HOSPITAL.getPersistenceUnitName());
        Scanner scanner = new Scanner(System.in);

        // Part one: Gather patient information and populate the database
        gatherPatientInfo(manager, scanner);

        // Part two: Record a visitation for an existing patient
        recordVisitation(manager, scanner);
    }

    private void gatherPatientInfo(EntityManager manager, Scanner scanner) {
        System.out.print(DO_YOU_WANT_TO_POPULATE_DB);
        String input = scanner.nextLine().toLowerCase();

        while (input.equalsIgnoreCase("Yes")) {
            manager.getTransaction().begin();

            final String firstName = PatientInfoGatherer.gatherPatientFirstName();
            final String lastName = PatientInfoGatherer.gatherPatientLastName();
            final String address = PatientInfoGatherer.gatherPatientAddress();
            final String email = PatientInfoGatherer.gatherPatientEmail();
            final String date = PatientInfoGatherer.gatherPatientDateOfBirth();
            final Boolean isInsured = PatientInfoGatherer.isPatientInsured();

            final Patient patient = new Patient(firstName, lastName, address, email, date, PICTURE_IN_BLOB, isInsured);
            manager.persist(patient);

            final String diagnoseName = PatientInfoGatherer.gatherDiagnosisName();
            final String diagnoseComment = PatientInfoGatherer.gatherDiagnosisComment();
            final Diagnose diagnose = new Diagnose(diagnoseName, diagnoseComment);
            manager.persist(diagnose);

            final String medicamentName = PatientInfoGatherer.gatherMedicamentName();
            final Medicament medicament = new Medicament(medicamentName);
            manager.persist(medicament);

            manager.getTransaction().commit();

            System.out.print(DO_YOU_WANT_TO_POPULATE_DB);
            input = scanner.nextLine().toLowerCase();
        }
    }

    private void recordVisitation(EntityManager manager, Scanner scanner) {
        System.out.print(VISITATION_FROM_PATIENT_WITH_ID);
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print(COMMENT_FOR_VISITATION);
        final String comment = scanner.nextLine();

        System.out.print(PATIENT_IS_DIAGNOSED_WITH);
        final String diagnoseName = scanner.nextLine();

        System.out.print(PATIENT_WITH_PRESCRIPTION_FOR_MEDICAMENT_NAME);
        final String medicamentName = scanner.nextLine();

        manager.getTransaction().begin();

        final Patient patient = manager.createQuery(FIND_PATIENT, Patient.class)
                .setParameter(1, id)
                .getSingleResult();

        final Diagnose diagnose = manager.createQuery(FIND_DIAGNOSE, Diagnose.class)
                .setParameter(1, diagnoseName)
                .getSingleResult();

        final Medicament medicament = manager.createQuery(FIND_MEDICAMENT, Medicament.class)
                .setParameter(1, medicamentName)
                .getSingleResult();

        final Visitation visitation = new Visitation(LocalDate.now(), comment, patient, diagnose, medicament);
        manager.persist(visitation);

        manager.getTransaction().commit();
    }
}