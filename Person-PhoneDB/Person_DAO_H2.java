package PersonDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Person_DAO_H2 implements PersonDAO
{
	private Connection conn = null;
	private Statement  st   = null;

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
		
		String firstName = p.getFName();
		String lastName = p.getLName();
		String age = ""+p.getAge();
		String create = "INSERT INTO Person (FirstName, LastName, Age)"+
				" VALUES ('"+firstName+"','"+lastName+"','"+age+"')";
//		String create = "INSERT INTO Person (FirstName, LastName, Age)"+
//				" VALUES ('"+p.getFName()+"','"+p.getLName()+"','"+p.getAge()+"')";
		try 
		{
			st.execute(create);
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

		String query = "SELECT id, firstname, lastname, age FROM person;";
		ResultSet res = st.executeQuery(query);
		while( res.next() )
		{
			Person p = new Person();
			p.setId   ( Integer.valueOf(res.getInt("ID")) );
			p.setFName( res.getString("FirstName") );
			p.setLName( res.getString("LastName") );
			p.setAge  ( Integer.valueOf(res.getInt("Age")) );
			ar.add(p);
		}
		return ar;
	}
	@Override
	public void Update(Person p)
	{
		String update = "UPDATE PERSON SET FirstName = '"+p.getFName()+" and LastName = '"+p.getLName()+"'  and Age = '"+p.getAge()+"' WHERE `ID`='"+p.getId()+"'";
		try 
		{
			st.executeUpdate(update);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void Delete(Person p)
	{
		String delete = "DELETE FROM person WHERE `ID`='"+p.getId()+"'";
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
