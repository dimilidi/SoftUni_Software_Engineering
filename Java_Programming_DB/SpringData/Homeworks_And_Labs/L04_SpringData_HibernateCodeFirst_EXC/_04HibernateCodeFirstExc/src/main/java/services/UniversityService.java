package services;

import entities._03UniversitySystem.Course;
import entities._03UniversitySystem.Student;
import entities._03UniversitySystem.Teacher;
import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import java.time.LocalDate;

import static constants.Constants.*;
import static enums.PersistenceUnitName.UNIVERSITY;

public class UniversityService {
    private static UniversityService instance;

    private UniversityService() {}

    public static UniversityService getInstance() {
        if (instance == null) {
            instance = new UniversityService();
        }
        return instance;
    }

    public void executeTaskThree() {
        EntityManager manager = JpaUtil.getDBConnection(UNIVERSITY.getPersistenceUnitName());
        manager.getTransaction().begin();

        //Bonus code to populate the DB
        populateDB(manager);

        manager.getTransaction().commit();
    }

    private void populateDB(EntityManager manager) {
        Teacher teacherOne = createTeacher(FIRST_NAME, LAST_NAME);
        Student studentOne = createStudent(FIRST_NAME, LAST_NAME);
        Student studentTwo = createStudent(FIRST_NAME, LAST_NAME);
        Course courseOne = createCourse(COURSE_NAME, LocalDate.now());

        teacherOne.getCourses().add(courseOne);
        courseOne.setTeacher(teacherOne);

        studentOne.getCourses().add(courseOne);
        courseOne.getStudents().add(studentOne);
        courseOne.getStudents().add(studentTwo);

        manager.persist(teacherOne);
        manager.persist(courseOne);
        manager.persist(studentOne);
        manager.persist(studentTwo);
    }

    private Teacher createTeacher(String firstName, String lastName) {
        return new Teacher(firstName, lastName);
    }

    private Student createStudent(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }

    private Course createCourse(String name, LocalDate date) {
        return new Course(name, date);
    }
}
