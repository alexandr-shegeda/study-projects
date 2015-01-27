package PersonDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Person_DAO_Mock  implements PersonDAO
{
	static ArrayList<Person> pList = null;

	@Override
	public void Connect() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException 
	{
        pList = new ArrayList<Person>();
        Person p = null;
        for( int i = 1; i < 10; i++){
            p = new Person();
            p.setId(i);
            p.setFName("Alex");
            p.setLName("Shegeda");
            p.setAge(16+i);
            pList.add(p);
        }
	}

	@Override
	public void DisConnect() throws SQLException 
	{
        pList = new ArrayList<Person>();
	}

	@Override
	public void Create(Person p) 
	{
		pList.add(p);
	}

	@Override
	public ArrayList<Person> Read() throws SQLException 
	{
		return pList;
	}

	@Override
	public void Update(Person p) 
	{
		Iterator<Person> iter = pList.iterator();
        Person person = null;
		while( iter.hasNext() )
		{
            person = iter.next();
			if( person.getId() == p.getId())
			{
                person.setFName(p.getFName());
                person.setLName(p.getLName());
                person.setAge(p.getAge());
			}
		}
	}

	@Override
	public void Delete(Person p) 
	{
		pList.remove(p);
	}

}
