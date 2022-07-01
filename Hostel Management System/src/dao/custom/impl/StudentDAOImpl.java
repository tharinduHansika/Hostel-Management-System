package dao.custom.impl;

import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, s);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exists(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ----- In HQL -----

        // SELECT *
        String hql = "FROM Student WHERE studentID = :student_id";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        query.setParameter("student_id", s);
        int status = query.executeUpdate();
        System.out.println(status);
        return  (status > 0 ? true : false);

    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ----- In HQL -----

        // SELECT *
        String hql = "FROM Student ";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List studentList = query.list();
        return  studentList;

    }

    @Override
    public Student search(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student studentList= session.get(Student.class, s);

        transaction.commit();
        session.close();
        return studentList;
    }

    @Override
    public String generateNewID() {
        return null;
    }
}
