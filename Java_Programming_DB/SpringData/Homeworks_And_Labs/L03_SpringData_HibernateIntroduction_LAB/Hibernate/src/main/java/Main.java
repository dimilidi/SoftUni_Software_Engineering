
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        // read hibernate.cfg.xml file
        cfg.configure();

        // use configuration
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        // getConnection to db with the driver we chose
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Student student = new Student();
        student.setName("Peter");
        session.save(student);

        Student student2 = new Student();
        student.setName("Gosho");
        session.save(student);

        Student studentGet = session.get(Student.class, 1);
        System.out.printf("%d - %s", studentGet.getId(), studentGet.getName());

        studentGet.setName("Updated");

        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        for (Student s : students) {
            System.out.println(s);
        }

        session.getTransaction().commit();
        session.close();
    }
}
