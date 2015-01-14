package PersonDB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDAO 
{
	public void Connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException ;
	public void DisConnect() throws SQLException; 
	public void Create(Person p);
	public ArrayList<Person> Read() throws SQLException;
	public void Update(Person p);
	public void Delete(Person p);
}
