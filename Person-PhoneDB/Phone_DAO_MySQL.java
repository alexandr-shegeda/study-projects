package PersonDB;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Александр on 22.12.14.
 */
/**
public class Phone_DAO_MySQL implements Phone_DAO{

    private static Connection conn = null;
    private static Statement st   = null;

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
    public void Create(Phone p)
    {
        String id = ""+p.getPhone_id();
        String phoneNumber = p.getPhoneNumber();
//        int person_id =  p.getPerson_id();
//        String create = "INSERT INTO Person (Id,PhoneNumber, Person_Id)"+
//                " VALUES ('"+id+"','"+phoneNumber+"','"+person_id+"')";
        try
        {
//            st.execute(create);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Phone> Read() throws SQLException
    {
        ArrayList<Phone> ar = new ArrayList<Phone>();

        String query = "SELECT id, phoneNumber, person_id FROM phone;";
        ResultSet res = st.executeQuery(query);
        while( res.next() )
        {
            Phone p = new Phone();
            p.setPhone_id   ( Integer.valueOf(res.getInt("id")) );
            p.setPhoneNumber( res.getString("Number") );
            p.setPerson_id( Integer.valueOf(res.getInt("Person_Id")));

            ar.add(p);
        }
        return ar;
    }
    @Override
    public void Update(Phone p)
    {
        String update = "UPDATE phone SET PhoneNumber ='"+p.getPhoneNumber()+"' WHERE id="+p.getPhone_id();
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
    public void Delete(Phone p)
    {
        String delete = "DELETE FROM phone WHERE id="+p.getPhone_id();
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

*/