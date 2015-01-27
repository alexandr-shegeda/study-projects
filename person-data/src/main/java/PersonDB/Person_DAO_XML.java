package PersonDB;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Person_DAO_XML implements PersonDAO
{
    static String file = "";
    @Override
    public void Connect() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException
    {
        file = "c:\\person.xml";
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
                writer.append("<Person>\n");
                writer.append("    <id>"+person.getId()+"</id>\n");
                writer.append("    <name>"+person.getFName()+"</name>\n");
                writer.append("    <surname>"+person.getLName()+"</surname>\n");
                writer.append("    <age>"+person.getAge()+"</age>\n");
                writer.append("</Person>\n");
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
                if (line.equals("<Person>"))
                {
                    p = new Person();
                }
                else if (line.contains("<id>"))
                {
                    p.setId(Integer.parseInt(line.replaceAll("\\<|id|\\/|\\>|\\s", "")));
                }
                else if (line.contains("<name>"))
                {
                    p.setFName(line.replaceAll("\\<|name|\\/|\\>|\\s", ""));
                }
                else if (line.contains("<surname>"))
                {
                    p.setLName(line.replaceAll("\\<|surname|\\/|\\>|\\s", ""));
                }
                else if (line.contains("<age>"))
                {
                    p.setAge(Integer.parseInt(line.replaceAll("\\<|age|\\/|\\>|\\s", "")));
                }
                else if (line.equals("</Person>"))
                {
                    personList.add(p);
                }
                continue;
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

                writer.append("<Person>\n");
                writer.append("    <id>"+person.getId()+"</id>\n");
                writer.append("    <name>"+person.getFName()+"</name>\n");
                writer.append("    <surname>"+person.getLName()+"</surname>\n");
                writer.append("    <age>"+person.getAge()+"</age>\n");
                writer.append("</Person>\n");
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
                writer.append("<Person>\n");
                writer.append("    <id>"+person.getId()+"</id>\n");
                writer.append("    <name>"+person.getFName()+"</name>\n");
                writer.append("    <surname>"+person.getLName()+"</surname>\n");
                writer.append("    <age>"+person.getAge()+"</age>\n");
                writer.append("</Person>\n");
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
