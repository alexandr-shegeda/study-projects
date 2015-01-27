package PersonDB;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Person_DAO_JSON implements PersonDAO
{
    static String file = "";
    @Override
    public void Connect() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException
    {
        file = "c:\\person.json";
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
                writer.append("{ 'Person':[ {");
                writer.append(" 'id':'"+person.getId()+"',");
                writer.append(" 'name':'"+person.getFName()+"',");
                writer.append(" 'surname':'"+person.getLName()+"',");
                writer.append(" 'age':'"+person.getAge()+"' } ]");
                writer.append("}");
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
                String[] person = line.replaceAll("\\{|\\}|\\[|\\]|'|\\s","").split(":|,");

                p.setId(Integer.parseInt(person[2]));
                p.setFName(person[4]);
                p.setLName(person[6]);
                p.setAge(Integer.parseInt(person[8]));

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

                writer.append("{ 'Person':[ {");
                writer.append(" 'id':'"+person.getId()+"',");
                writer.append(" 'name':'"+person.getFName()+"',");
                writer.append(" 'surname':'"+person.getLName()+"',");
                writer.append(" 'age':'"+person.getAge()+"' } ]");
                writer.append("}");
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
                writer.append("{ 'Person':[ {");
                writer.append(" 'id':'"+person.getId()+"',");
                writer.append(" 'name':'"+person.getFName()+"',");
                writer.append(" 'surname':'"+person.getLName()+"',");
                writer.append(" 'age':'"+person.getAge()+"' } ]");
                writer.append("}");
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
