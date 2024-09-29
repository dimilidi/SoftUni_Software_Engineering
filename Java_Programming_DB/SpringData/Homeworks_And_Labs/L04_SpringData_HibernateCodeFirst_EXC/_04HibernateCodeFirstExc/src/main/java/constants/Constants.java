package constants;

import java.math.BigDecimal;

public class Constants {
    public static final String INPUT_PROMPT = "Please enter task number from 1 to 6: ";

    public static final String FIND_PATIENT = "FROM Patient WHERE id = ?1";
    public static final String FIND_DIAGNOSE = "FROM Diagnose WHERE name = ?1";
    public static final String FIND_MEDICAMENT = "FROM Medicament WHERE name = ?1";
    public static final String DO_YOU_WANT_TO_POPULATE_DB = "Do you want to populate the database?(Yes/No): ";
    public static final String VISITATION_FROM_PATIENT_WITH_ID = "Today's visitation is from patient ID: ";
    public static final String COMMENT_FOR_VISITATION = "Enter comment for today's visitation: ";
    public static final String PATIENT_IS_DIAGNOSED_WITH = "The patient is diagnosed with(name): ";
    public static final String PATIENT_WITH_PRESCRIPTION_FOR_MEDICAMENT_NAME = "The patient has prescription for the following medicament(name): ";
    public static final String TASK_DOES_NOT_EXIST = "Task with number %d does not exist!\n";
    public static final String TASK_WAS_EXECUTED = "Task %d was executed!\n";


    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String PASSWORD = "PAssWOrd";
    public static final Integer AGE = 30;
    public static final String PRODUCT_ONE = "ProductOne";
    public static final Double QUANTITY = 2.00;
    public static final BigDecimal PRICE = BigDecimal.valueOf(500);
    public static final String CUSTOMER_ONE = "CustomerOne";
    public static final String EMAIL = "test@test.bg";
    public static final String CARD_NUMBER = "1234554234234";
    public static final String LOCATION_NAME = "LocationOne";
    public static final String COURSE_NAME = "CourseOne";
    public static final String MEDICAMENT_ONE_NAME = "MedicamentOne";
    public static final String MEDICAMENT_TWO_NAME = "MedicamentTwo";
    public static final String DIAGNOSE_ONE_NAME = "DiagnoseOne";
    public static final String DIAGNOSE_TWO_NAME = "DiagnoseTwo";
    public static final String DIAGNOSE_COMMENT = "Comment for diagnose";
    public static final String VISITATION_COMMENT = "Comment for visitation";
    public static final String PICTURE_IN_BLOB = "/9j/4AAQSkZJRgABAQEBLAEsAAD/4QBWRXhpZgAATU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAAITAAMAAAABAAEAAAAAAAAAAAEsAAAAAQAAASwAAAAB/+0ALFBob3Rvc2hvcCAzLjAAOEJJTQQEAAAAAAAPHAFaAAMbJUccAQAAAgAEAP/hDIFodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0nYWRvYmU6bnM6bWV0YS8nIHg6eG1wdGs9J0ltYWdlOjpFeGlmVG9vbCAxMC4xMCc+CjxyZGY6UkRGIHhtbG5zOnJkZj0naHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyc+CgogPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9JycKICB4bWxuczp0aWZmPSdodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyc+CiAgPHRpZmY6UmVzb2x1dGlvblVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICA8dGlmZjpYUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpYUmVzb2x1dGlvbj4KICA8dGlmZjpZUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpZUmVzb2x1dGlvbj4KIDwvcmRmOkRlc2NyaXB0aW9uPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6eG1wTU09J2h0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8nPgogIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnN0b2NrOjYxZDkwMzZiLWE2Y2UtNDdiNi04ZTE4LTdmZWZmODkxYTBlNzwveG1wTU06RG9jdW1lbnRJRD4KICA8eG1wTU06SW5zdGFuY2VJRD54bXAuaWlkOmMwZmEyZjAxLWFiZWYtNDdhOS04ZWM2LTE2NDc2YzM2MDRjZTwveG1wTU06SW5zdGFuY2VJRD4KIDwvcmRmOkRlc2NyaXB0aW9uPgo8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSd3Jz8+/9sAQwAFAwQEBAMFBAQEBQUFBgcMCAcHBwcPCwsJDBEPEhIRDxERExYcFxMUGhURERghGBodHR8fHxMXIiQiHiQcHh8e/9sAQwEFBQUHBgcOCAgOHhQRFB4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4e/8AAEQgBaAHgAwERAAIRAQMRAf/EAB0AAQABBQEBAQAAAAAAAAAAAAAFAgMEBgcBCAn/xABQEAABAwIBBggKBwQHCAMBAAABAAIDBBEFBhIUITFRBxMVQVJhkZIiMlNVcYGTobHRFhcj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";
    public static final String BANK_NAME = "Bank name";
    public static final String BANK_NUMBER = "9977711";
    public static final String SWIFT_CODE = "AAAA-BB-CC-123";
}
