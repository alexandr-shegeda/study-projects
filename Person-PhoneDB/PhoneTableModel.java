package PersonDB;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 25.12.14.
 */
public class PhoneTableModel extends AbstractTableModel
{
    private List<Phone> phones = new ArrayList<Phone>();
    Commands cmd = new Commands();

    public void setPhones(List<Phone> phones)
    {
        this.phones = phones;
    }
    @Override
    public int getRowCount()
    {
        return phones.size();
    }

    @Override
    public int getColumnCount()
    {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int column)
    {
        Object ret = phones.get(rowIndex).getPhoneNumber();

        return ret;
    }

    @Override
    public String getColumnName(int column)
    {
        String ret = "";
        switch (column)
        {
            case 0: ret = "Numbers";     break;
        }
        return ret;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Phone phone = phones.get(rowIndex);
        phone.setPhoneNumber(aValue+"");
        phones.set(rowIndex,phone);
        cmd.setValueAtPhone(aValue,rowIndex,phones);
    }
}
