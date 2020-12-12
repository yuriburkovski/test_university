package university.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import university.config.SessionProvider;
import university.model.Department;
import university.model.Lector;
import university.model.LectorDegree;

import java.util.List;
import java.util.Optional;

public class CommandController {
    private Optional<Department> findDepartmentByName(String inputName) {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Query query = (Query) session.createQuery("from Department d where d.name=:name")
                .setParameter("name", inputName);
        Optional<Department> department = Optional.ofNullable((Department) query.uniqueResult());
        session.getTransaction().commit();
        return department;
    }

    public void headOfDepartment(String inputName) {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Department head = (Department) session
                .createQuery("from Department d where d.name=:name")
                .setParameter("name", inputName)
                .uniqueResult();
        if (!head.equals(null)) {
            System.out.println("Head of " + inputName + " department is: " + head.getHeadOfDepartment());
        } else {
            System.out.println(inputName + " department is not exist in DB!");
        }
        session.getTransaction().commit();
    }

    public void departmentStats(String inputName) {
        Optional<Department> department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        if (department.isPresent()) {
            Query query = session.createQuery("select count(l.name) " +
                    "Lector l where l.degree=:degree and :department in elements(l.departments)");
            Long assistantsCount = (Long) query.setParameter("degree", LectorDegree.ASSISTANT)
                    .setParameter("department", department.get()).uniqueResult();
            Long assocProfCount = (Long) query.setParameter("degree", LectorDegree.ASSOCIATE_PROFESSOR)
                    .setParameter("department", department.get()).uniqueResult();
            Long professorsCount = (Long) query.setParameter("degree", LectorDegree.PROFESSOR)
                    .setParameter("department", department.get()).uniqueResult();

            session.getTransaction().commit();
            System.out.println(inputName + " department statistics:");
            System.out.println("ASSISTANT: " + assistantsCount);
            System.out.println("ASSOCIATE_PROFESSOR: " + assocProfCount);
            System.out.println("PROFESSOR: " + professorsCount);
        } else {
            System.out.println(inputName + " department is not exist in DB!");
        }
        session.getTransaction().commit();
    }

    public void averageSalary(String inputName) {
        Optional<Department> department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        if (department.isPresent()) {
            Query query = session.createQuery("select avg(l.salary) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
            Double averageSalary = (Double) query.uniqueResult();

            session.getTransaction().commit();
            System.out.println("The average salary for " + inputName + "department:" + averageSalary);
        } else {
            System.out.println(inputName + " department is not exist in DB!");
        }
    }

    public void employeeCount(String inputName) {
        Optional<Department> department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        if (department.isPresent()) {
            Query query = session.createQuery("select count(l) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
            Integer count = (Integer) query.uniqueResult();

            session.getTransaction().commit();
            System.out.println("Count of employee in " + inputName + " department is:" + count);
        } else {
            System.out.println(inputName + " department is not exist in DB!");
        }
    }

    public void globalSearch(String input) {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from Lector l where l.name like :word")
                .setParameter("word", "%" + input.toLowerCase() + "%");
        List<Lector> lectors = query.list();

        if (lectors.isEmpty()) {
            System.out.println("No such element");
        } else
            System.out.println("Answer:");
        for (Lector l : lectors) {
            System.out.println(l.getName());
        }
        session.getTransaction().commit();
    }
}

