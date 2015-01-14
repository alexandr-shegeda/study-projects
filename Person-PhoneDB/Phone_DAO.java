package PersonDB;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Александр on 22.12.14.
 */
public interface Phone_DAO
{
    public void Connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
    public void DisConnect() throws SQLException;
    public void Create(Phone p);
    public ArrayList<Phone> Read() throws SQLException;
    public void Update(Phone p);
    public void Delete(Phone p);
}
