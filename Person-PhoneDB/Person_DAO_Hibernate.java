package PersonDB;

import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Александр on 25.12.14.
 */
public class Person_DAO_Hibernate implements PersonDAO
{

    private Session session = null;

    @Override
    public void Connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void DisConnect() throws SQLException
    {
        session = null;
    }

    @Override
    public void Create(Person p)
    {
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }

    @Override
    public ArrayList<Person> Read() throws SQLException
    {
        session.beginTransaction();
        Query query = session.createQuery("from PersonDB.Person");
        ArrayList<Person> persons = (ArrayList<Person>)query.list();

        return persons;
    }

    @Override
    public void Update(Person p)
    {

    }

    @Override
    public void Delete(Person p)
    {

    }
}
