package PersonDB;
import com.mongodb.QueryBuilder;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Person_DAO_MySQL implements PersonDAO
{
	private static Connection conn = null;
	private static Statement  st   = null;

	@Override
	public void Connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		st = conn.createStatement();
	}
	@Override
	public void DisConnect() throws SQLException
	{
		st.close();
		conn.close();
	}
	@Override
	public void Create(Person p)
	{
        String id        =""+p.getId();
		String firstName =   p.getFName();
		String lastName  =   p.getLName();
		String age       =""+p.getAge();
		String create    = "INSERT INTO Person (Id,FirstName, LastName, Age)"+
				" VALUES ('"+id+"','"+firstName+"','"+lastName+"','"+age+"')";
        String query;
        Iterator<Phone> iter = p.getPhoneList().iterator();
        while(iter.hasNext())
        {
            Phone phone = iter.next();
            query = "INSERT INTO telephone (person_id,phoneNumber)"+
                    " VALUES ('"+id+"','"+phone.getPhoneNumber()+"')";
            try
            {
                st.addBatch(query);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
		try 
		{
            st.execute(create);
            st.executeBatch();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<Person> Read() throws SQLException
	{
		ArrayList<Person> ar = new ArrayList<Person>();

		String query = "SELECT * FROM person;";
		ResultSet res = st.executeQuery(query);
		while( res.next() )
		{
			Person p = new Person();
			p.setId   ( Integer.valueOf(res.getInt("Id")) );
			p.setFName( res.getString("FirstName") );
			p.setLName( res.getString("LastName") );
            p.setAge(Integer.valueOf(res.getInt("Age")));
			ar.add(p);
		}

        for( int i = 0; i < ar.size(); i++)
        {
            Person p = ar.get(i);
            List<Phone> phoneList = new ArrayList<Phone>();
            String phoneQuery = "SELECT * FROM telephone where person_id="+p.getId();
            ResultSet resp = st.executeQuery(phoneQuery);
            while( resp.next() )
            {
                Phone phone = new Phone();
                phone.setPhone_id(Integer.valueOf(resp.getString("id")));
                phone.setPhoneNumber(resp.getString("phoneNumber"));
                phone.setPerson_id(p);
                phoneList.add(phone);
            }
            p.setPhoneList(phoneList);
        }

		return ar;
	}
	@Override
	public void Update(Person p)
	{
		String update = "UPDATE person SET FirstName ='"+p.getFName()+"' ,LastName ='"+p.getLName()+"', Age = "+p.getAge()+" WHERE id="+p.getId();
		StringBuilder updatePhones = new StringBuilder();
        Iterator<Phone> iter = p.getPhoneList().iterator();
        while(iter.hasNext())
        {
            Phone phone = iter.next();
            String query;
            if( phone.getPhone_id() == 0)
            query = " INSERT INTO telephone (phoneNumber,person_id)" +
                    " VALUES ('"+phone.getPhoneNumber()+"',"+phone.getPerson_id().getId()+");\r\n";
            else
            query = " UPDATE telephone" +
                    " SET phoneNumber='"+phone.getPhoneNumber()+
                    "' WHERE id="+phone.getPhone_id()+";\r\n";
            try {
                st.addBatch(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try
		{
            st.addBatch(update);
			st.executeBatch();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void Delete(Person p)
	{
		String delete = "DELETE FROM person WHERE id="+p.getId();

		try 
		{
			st.execute(delete);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
