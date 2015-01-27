package PersonDB;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class PersonTableModel extends AbstractTableModel 
{
	ArrayList<Person> ar = new ArrayList<Person>();
	Commands cmd = new Commands();
	
	@Override
	public String getColumnName(int column) 
	{
		String ret = "";
		switch (column) 
		{
			case 0: ret = "ID";      break;
			case 1: ret = "Name";    break;
			case 2: ret = "SurName"; break;
			case 3: ret = "Age";     break;
		}
		return ret;
	}
	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public int getRowCount() 
	{
		return ar.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		Object ret = null;
		Person p = ar.get(row);
		switch (column) 
		{
			case 0: ret = p.getId();    break;
			case 1: ret = p.getFName(); break;
			case 2: ret = p.getLName(); break;
			case 3: ret = p.getAge();   break;
		}
		return ret;
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        cmd.setValueAt(aValue, rowIndex, columnIndex);
    }
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{
		boolean edit = false;
		if( columnIndex == 0)
		{
			edit = false;
		}
		else
		{
			edit = true;
		}
		return edit;
	}
    public Person getSelectedPerson(int row)
    {
        Person person = ar.get(row);
        return person;
    }
	
}
