package PersonDB;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

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
                writer.append("{\n'Person': \n");
                writer.append("\t{\n");
                writer.append("\t'id':'"+person.getId()+"',\n");
                writer.append("\t'name':'"+person.getFName()+"',\n");
                writer.append("\t'surname':'"+person.getLName()+"',\n");
                writer.append("\t'age':'"+person.getAge()+"',\n");
                writer.append("\t'phoneNumbers':[ \n");
                int phones = 0;
                while( phones < person.getPhoneList().size() )
                {
                    writer.append("\t\t'"+person.getPhoneList().get(phones).getPhoneNumber()+"',\n");
                    phones++;
                }
                writer.append("\t]\n");
                writer.append("}]}\n");
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
        List<Phone> phoneList = null;
        Phone phone = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null)
            {
                if (line.contains("'Person':"))
                {
                    p = new Person();
                }
                else if (line.contains("'id'"))
                {
                    p.setId(Integer.parseInt(line.replaceAll("\'|,|:|id|\\s|\\t|/:", "")));
                }
                else if (line.contains("'name'"))
                {
                    p.setFName(line.replaceAll("'|:|,|name|\\s|\\t", ""));
                }
                else if (line.contains("'surname'"))
                {
                    p.setLName(line.replaceAll("'|:|,|surname|\\s|\\t", ""));
                }
                else if (line.contains("'age'"))
                {
                    p.setAge(Integer.parseInt(line.replaceAll("\'|:|,|age|\\s|\\t", "")));
                }
                else if (line.contains("'phoneNumbers'"))
                {
                    phoneList = new ArrayList<Phone>();
                }
                else if (line.replaceAll("\'|,|\\t","").matches("\\d{1,10}"))
                {
                    phone = new Phone();
                    phone.setPhoneNumber(line.replaceAll("\'|:|,|\\s|\\t", ""));
                    phoneList.add(phone);
                }
                else if (line.replaceAll("\\t","").equals("]"))
                {
                    p.setPhoneList(phoneList);
                }
                else if (line.equals("}]}"))
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
                    person.setPhoneList(p.getPhoneList());
                }
                writer.append("{\n'Person': [\n");
                writer.append("\t{\n");
                writer.append("\t'id':'"+person.getId()+"',\n");
                writer.append("\t'name':'"+person.getFName()+"',\n");
                writer.append("\t'surname':'"+person.getLName()+"',\n");
                writer.append("\t'age':'"+person.getAge()+"',\n");
                writer.append("\t'phoneNumbers':[ \n");
                int phones = 0;
                while( phones < person.getPhoneList().size() )
                {
                    writer.append("\t\t'"+person.getPhoneList().get(phones).getPhoneNumber()+"',\n");
                    phones++;
                }
                writer.append("\t]\n");
                writer.append("}]}\n");
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
                writer.append("{\n'Person': \n");
                writer.append("\t{\n");
                writer.append("\t'id':'"+person.getId()+"',\n");
                writer.append("\t'name':'"+person.getFName()+"',\n");
                writer.append("\t'surname':'"+person.getLName()+"',\n");
                writer.append("\t'age':'"+person.getAge()+"',\n");
                writer.append("\t'phoneNumbers':[ \n");
                int phones = 0;
                while( phones < person.getPhoneList().size() )
                {
                    writer.append("\t\t'"+person.getPhoneList().get(phones).getPhoneNumber()+"',\n");
                    phones++;
                }
                writer.append("\t]\n");
                writer.append("}]}\n");
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
