package dao.custom.impl;

import dao.custom.ReservationDAO;
import entity.Reservartion;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservartion entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservartion entity) throws Exception {
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

        Reservartion reservartion = session.load(Reservartion.class, s);

        session.delete(reservartion );

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exists(String s) throws Exception {
        return false;
    }

    @Override
    public List<Reservartion> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ----- In HQL -----

        // SELECT *
        String hql = "FROM Reservartion ";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List reservationList = query.list();
        return  reservationList;
    }

    @Override
    public Reservartion search(String s) {
        return null;
    }

    @Override
    public String generateNewID() throws IOException {
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ----- In HQL -----

        // SELECT *
        String hql = "SELECT Owner SET name = :owner_name WHERE oId = :owner_id";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List reservationList = query.list();
        return  reservationList;


        ResultSet rst = SQLUtil.executeQuery("SELECT CustID FROM Customer ORDER BY CustID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("CustID");
            int newCustomerId = Integer.parseInt(id.replace("RE", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "RE001";
        }*/
        //return "RE001";

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select res_ID from Reservartion order by res_ID desc limit 1");
        String id = (String) sqlQuery.uniqueResult();

        if(id != null){
            //String id = rst.getString("CustID");
            int newCustomerId = Integer.parseInt(id.replace("RE", "")) + 1;
            return String.format("RE%03d", newCustomerId);
        }
        transaction.commit();
        session.close();
        return "RE001";
    }
}
