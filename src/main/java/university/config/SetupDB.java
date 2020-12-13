package university.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import university.model.Department;
import university.model.Lector;
import university.model.LectorDegree;

import java.util.HashSet;
import java.util.Set;

public class SetupDB {
    public static void saveAll() throws HibernateException {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Department departmentEcon = new Department("Economics", "Oleg Skrypka");
        Department departmentMath = new Department("Mathematics", "Ivan Petrov");
        Department departmentPhil = new Department("Philology", "Yulia Golovan");
        Set<Lector> lectorsEcon = new HashSet<>();
        Set<Lector> lectorsMath = new HashSet<>();
        Set<Lector> lectorsPhil = new HashSet<>();

        Lector lectorRepeat = new Lector("Vasyl Stus", LectorDegree.ASSISTANT, 5000);
        lectorsEcon.add(new Lector("Oleg Skrypka", LectorDegree.PROFESSOR, 12000));
        lectorsEcon.add(new Lector("Petro Ivanov", LectorDegree.ASSOCIATE_PROFESSOR, 8000));
        lectorsEcon.add(lectorRepeat);
        lectorsMath.add(new Lector("Ivan Petrov", LectorDegree.PROFESSOR, 12000));
        lectorsMath.add(new Lector("Alex Rodas", LectorDegree.ASSOCIATE_PROFESSOR, 8000));
        lectorsMath.add(lectorRepeat);
        lectorsPhil.add(new Lector("Yulia Golovan", LectorDegree.PROFESSOR, 12000));
        lectorsPhil.add(new Lector("Olena Bystra", LectorDegree.ASSOCIATE_PROFESSOR, 8000));
        lectorsPhil.add(new Lector("Ivan Bystry", LectorDegree.ASSISTANT, 5000));

        departmentEcon.setLectors(lectorsEcon);
        departmentMath.setLectors(lectorsMath);
        departmentPhil.setLectors(lectorsPhil);

        session.persist(departmentEcon);
        session.persist(departmentMath);
        session.persist(departmentPhil);

        session.getTransaction().commit();
    }
}
