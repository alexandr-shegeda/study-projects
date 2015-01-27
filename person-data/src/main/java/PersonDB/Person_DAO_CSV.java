package PersonDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Person_DAO_CSV implements PersonDAO{

	static String file = "";
	@Override
	public void Connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException 
	{
		file = "c:\\person.csv";
	}

	@Override
	public void DisConnect() throws SQLException 
	{
		file = "";
	}

	@Override
	public void Create(Person p) 
	{
		try
		{
			ArrayList<Person> personList = Read();
			personList.add(p);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator<Person> iter = personList.iterator();
			while(iter.hasNext())
			{	
				Person person = iter.next();
				writer.append(""+person.getId());
			    writer.append(';');
			    writer.append(""+person.getFName());
			    writer.append(';');
			    writer.append(""+person.getLName());
			    writer.append(';');
			    writer.append(""+person.getAge());
			    writer.append('\n');
			}

		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public ArrayList<Person> Read() throws SQLException 
	{
		String line = "";
		Person p = null;
		ArrayList<Person> personList = new ArrayList<Person>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null)
			{
				p = new Person();
				String[] person = line.split(";");
				
				p.setId(Integer.parseInt(person[0]));
				p.setFName(person[1]);
				p.setLName(person[2]);
				p.setAge(Integer.parseInt(person[3]));
				
				personList.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return personList;
	}

	@Override
	public void Update(Person p) 
	{
		try
		{
			ArrayList<Person> personList = Read();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator<Person> iter = personList.iterator();
            while(iter.hasNext())
            {
                Person person = iter.next();
                if(person.getId() == p.getId())
                {
                    person.setAge(p.getAge());
                    person.setFName(p.getFName());
                    person.setLName(p.getLName());
                }
                writer.append(""+person.getId());
                writer.append(';');
                writer.append(person.getFName());
                writer.append(';');
                writer.append(person.getLName());
                writer.append(';');
                writer.append(""+person.getAge());
                writer.append('\n');
            }

		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void Delete(Person p) 
	{
		try
		{
			ArrayList<Person> personList = Read();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator<Person> iter = personList.listIterator();
			while(iter.hasNext())
			{	
				Person person = iter.next();
                if(person.getId() == p.getId())
                {
                   continue;
                }
				writer.append(""+person.getId());
			    writer.append(';');
			    writer.append(person.getFName());
			    writer.append(';');
			    writer.append(person.getLName());
			    writer.append(';');
			    writer.append(""+person.getAge());
			    writer.append('\n');
			}

		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
