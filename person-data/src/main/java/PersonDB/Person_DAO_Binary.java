package PersonDB;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Александр on 21.12.14.
 */
public class Person_DAO_Binary implements PersonDAO {
    String file = "";

    @Override
    public void Connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        file = "c:\\person.sbf";
    }

    @Override
    public void DisConnect() throws SQLException {
        file = "";

    }

    @Override
    public void Create(Person p) {

        ArrayList<Person> personList = null;
        try {
            personList = Read();
            personList.add(p);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));

            Iterator<Person> iter = personList.iterator();
            while (iter.hasNext()) {
                Person person = iter.next();

                dos.write(Byte.valueOf(Byte.valueOf(""+person.getId())));
                dos.write(Byte.valueOf(person.getFName()));
                dos.write(Byte.valueOf(person.getLName()));
                dos.write(Byte.valueOf(Byte.valueOf("" + person.getAge())));

//                dos.write(new byte[]{Byte.valueOf(""+person.getId())},0,1);
//                dos.write(new byte[]{Byte.valueOf(person.getFName())},1,2);
//                dos.write(new byte[]{Byte.valueOf(person.getLName())},2,3);
//                dos.write(new byte[]{Byte.valueOf(""+person.getAge())},3,4);
                dos.writeChars("\n");
            }
            dos.flush();
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Person> Read() throws SQLException {
        String line = "";
        Person p = null;
        ArrayList<Person> personList = new ArrayList<Person>();
        try {
            DataInputStream di = new DataInputStream(new FileInputStream(file));
            while (di.available() != 0) {
                p = new Person();
                line = di.readLine();
                String[] person = line.replaceAll("'|\\{|\\}", "").split(",|=");
                p.setId(Integer.parseInt(person[1]));
                p.setFName(person[3]);
                p.setLName(person[5]);
                p.setAge(Integer.parseInt(person[7]));
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
    public void Update(Person p) {

    }

    @Override
    public void Delete(Person p) {

    }
}
