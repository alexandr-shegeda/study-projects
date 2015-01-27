package PersonDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JDialog;

public class Commands 
{

	ConnectListener    actionConnect    = new ConnectListener();
	DisconnectListener actionDisConnect = new DisconnectListener();
	CreateListener     actionCreate     = new CreateListener();
	ReadListener       actionRead       = new ReadListener();
	UpdateListener     actionUpdate     = new UpdateListener();
	DeleteListener     actionDelete     = new DeleteListener();

	private PersonDAO dao = null;
	
	static DBConnect_TablePanel panelWork = null;
	
	DBConnect_PersonDialog dlg = null;

	public Commands()
	{
//		dao = new Person_DAO_MySQL();
//		dao = new Person_DAO_MongoDB();
//		dao = new Person_DAO_Mock();
//		dao = new Person_DAO_CSV();
//		dao = new Person_DAO_JSON();
//		dao = new Person_DAO_XML();
		dao = new Person_DAO_Binary();

		dlg = new DBConnect_PersonDialog();
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        try
        {
            Person person = dao.Read().get(rowIndex);

            switch (columnIndex)
            {
                case 1:
                	person.setFName(aValue+"");
                    break;
                case 2:
                	person.setLName(aValue + "");
                    break;
                case 3:
                	person.setAge(Integer.parseInt(aValue + ""));
                    break;
            }
            dao.Update(person);
            panelWork.mdlPerson.ar = dao.Read();
            panelWork.tblPerson.updateUI();
        } catch (Exception e) { e.printStackTrace();}

    }
	
	class ConnectListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				dao.Connect();
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}

	class DisconnectListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				dao.DisConnect();
                panelWork.mdlPerson.ar = new ArrayList<Person>();
				panelWork.tblPerson.updateUI();
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}

	class CreateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			dlg.setVisible(true);
			dao.Create(dlg.getPerson());
			try 
			{
				panelWork.mdlPerson.ar = dao.Read();
				panelWork.tblPerson.updateUI();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}

	}
	class ReadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			try 
			{
				panelWork.mdlPerson.ar = dao.Read();
				panelWork.tblPerson.updateUI();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

	class UpdateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				panelWork.mdlPerson.ar = dao.Read();
				panelWork.tblPerson.updateUI();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}

	}
	class DeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	

			dao.Delete(panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow()));
			try 
			{
				panelWork.mdlPerson.ar = dao.Read();
				panelWork.tblPerson.updateUI();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}
