package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
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

        Room room = session.load(Room.class, s);

        session.delete(room);

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
        String hql = "FROM Room WHERE roomTypeID = :student_id";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        query.setParameter("student_id", s);
        int status = query.executeUpdate();
        System.out.println(status);
        return  (status > 0 ? true : false);
    }

    @Override
    public List<Room> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ----- In HQL -----

        // SELECT *
        String hql = "FROM Room ";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List roomList = query.list();
        return  roomList;
    }

    @Override
    public Room search(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room roomList= session.get(Room.class, s);

        transaction.commit();
        session.close();
        return roomList;
    }

    @Override
    public String generateNewID() {
        return null;
    }
}
