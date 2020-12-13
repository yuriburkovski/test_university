package university.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import university.config.SessionProvider;
import university.model.Department;
import university.model.Lector;
import university.model.LectorDegree;

import java.io.IOException;
import java.util.List;

public class CommandExecutor {

    private Department findDepartmentByName(String inputName) throws Exception {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Department depToReturn;
        Query<Department> query = session
                .createQuery("FROM Department WHERE departmentName=:name", Department.class);
        query.setParameter("name", inputName);
        List<Department> department = query.list();
        session.getTransaction().commit();
        if (department.isEmpty()) {
            throw new Exception("Wrong data!");
        } else {
            depToReturn = department.get(0);
        }
        return depToReturn;
    }

    public void headOfDepartment(String inputName){
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String head = (String) session
                .createQuery("SELECT headOfDepartment FROM Department WHERE departmentName=:name")
                .setParameter("name", inputName)
                .uniqueResult();
        if (!head.equals(null)) {
            System.out.println("Head of " + inputName + " department is: " + head);
        } else {
            System.out.println("Wrong data!");
        }
        session.getTransaction().commit();
    }

    public void departmentStats(String inputName) throws Exception {
        Department department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        if (department != null) {
            Query query = session.createQuery(
                    "SELECT count(name) FROM Lector WHERE degree=:degree AND :department IN elements(departments)");
            List assistantsCount = query
                    .setParameter("degree", LectorDegree.ASSISTANT)
                    .setParameter("department", department)
                    .list();
            List assocProfCount = query
                    .setParameter("degree", LectorDegree.ASSOCIATE_PROFESSOR)
                    .setParameter("department", department)
                    .list();
            List professorsCount = query
                    .setParameter("degree", LectorDegree.PROFESSOR)
                    .setParameter("department", department)
                    .list();

            session.getTransaction().commit();
            System.out.println(inputName + " department statistics:");
            System.out.println("ASSISTANT: " + assistantsCount.get(0));
            System.out.println("ASSOCIATE_PROFESSOR: " + assocProfCount.get(0));
            System.out.println("PROFESSOR: " + professorsCount.get(0));
        } else {
            System.out.println("Wrong data!");
        }
    }

    public void averageSalary(String inputName) throws Exception {
        Department department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        if (department != null) {
            Query query = session
                    .createQuery("select avg(l.salary) from Lector l where :name in elements(l.departments) ")
                    .setParameter("name", department);
            List averageSalary = query.list();
            session.getTransaction().commit();
            System.out.println("The average salary for " + inputName + " department is: " + averageSalary.get(0));
        } else {
            System.out.println("Wrong data!");
        }
    }

    public void employeeCount(String inputName) throws Exception {
        Department department = findDepartmentByName(inputName);
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        if (department != null) {
            Query query = session
                    .createQuery("select count(l) from Lector l where :name in elements(l.departments) ")
                    .setParameter("name", department);
            List count = query.list();
            session.getTransaction().commit();
            System.out.println("Count of employee in " + inputName + " department is:" + count.get(0));
        } else {
            System.out.println("Wrong data!");
        }
    }

    public void globalSearch(String input) throws IOException {
        Session session = SessionProvider.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Query<Lector> query = session
                .createQuery("FROM Lector l WHERE LOWER(l.name) LIKE :word", Lector.class)
                .setParameter("word", "%" + input.toLowerCase() + "%");
        List<Lector> lectors = query.list();

        if (lectors.isEmpty()) {
            System.out.println("No such element");
        } else
            System.out.println("Answer:");
        for (Lector lector : lectors) {
            System.out.println(lector.getName());
        }
        session.getTransaction().commit();
    }
}

