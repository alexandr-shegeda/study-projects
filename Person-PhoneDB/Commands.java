package PersonDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Commands 
{

	ConnectListener    actionConnect    = new ConnectListener();
	DisconnectListener actionDisConnect = new DisconnectListener();
	CreateListener     actionCreate     = new CreateListener();
	ReadListener       actionRead       = new ReadListener();
	UpdateListener     actionUpdate     = new UpdateListener();
	DeleteListener     actionDelete     = new DeleteListener();
	AddNumListener     actionAddNum     = new AddNumListener();
	BoxChangeListener  actionBoxChange  = new BoxChangeListener();

	private static PersonDAO dao = null;
    static String daoChoose ;
	static DBConnect_TablePanel panelWork = null;
	
	DBConnect_PersonDialog dlg  = null;
	DBConnect_PhoneDialog  pDlg = null;

    public Commands()
	{
		dlg  = new DBConnect_PersonDialog();
		pDlg = new DBConnect_PhoneDialog();
	}


    public static void updatePhones()
    {
        panelWork.mdlPhone.setPhones(panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow()).getPhoneList());
        panelWork.tblPhone.updateUI();
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
                case 4:
                    List<Phone> phones = person.getPhoneList();
                    Phone phone = phones.get(0);
                    phone.setPhoneNumber(aValue+"");
                    phones.set(0,phone);
                    person.setPhoneList(phones);
                    break;
            }
            dao.Update(person);
            panelWork.mdlPerson.ar = dao.Read();
            panelWork.tblPerson.updateUI();
        } catch (Exception e) { e.printStackTrace();}

    }

    public void setValueAtPhone(Object aValue, int rowIndex,List<Phone> phones)
    {
        try
        {
            Person person = panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow());
            person.setPhoneList(phones);

            dao.Update(person);
            panelWork.mdlPerson.ar = dao.Read();
            panelWork.tblPerson.updateUI();
        } catch (Exception e) { e.printStackTrace();}

    }

    class ConnectListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
            if(dao == null) dao = new Person_DAO_MySQL();
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

                panelWork.mdlPhone.setPhones(new ArrayList<Phone>());
                panelWork.tblPhone.updateUI();
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
                panelWork.mdlPhone.setPhones(panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow()).getPhoneList());
                panelWork.tblPhone.updateUI();

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

    class AddNumListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                pDlg.setVisible(true);
                pDlg.setPerson(panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow()));
                dao.Update(pDlg.getPerson());

                panelWork.mdlPhone.setPhones(panelWork.mdlPerson.getSelectedPerson(panelWork.tblPerson.getSelectedRow()).getPhoneList());
                panelWork.tblPhone.updateUI();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    class BoxChangeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JComboBox box = (JComboBox) e.getSource();
            daoChoose = box.getSelectedItem().toString();
            getDao(daoChoose);
        }
    }

    public void getDao(String daoChoose) {
        if (daoChoose.equals("MySQL")) {
            dao = new Person_DAO_MySQL();
        } else if (daoChoose.equals("MongoDB")) {
            dao = new Person_DAO_MongoDB();
        } else if (daoChoose.equals("Hibernate")) {
            dao = new Person_DAO_Hibernate();
        } else if (daoChoose.equals("Mock")) {
            dao = new Person_DAO_Mock();
        } else if (daoChoose.equals("CSV")) {
            dao = new Person_DAO_CSV();
        } else if (daoChoose.equals("JSON")) {
            dao = new Person_DAO_JSON();
        } else if (daoChoose.equals("XML")) {
            dao = new Person_DAO_XML();
        }
    }
}
