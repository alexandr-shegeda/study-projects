package PersonDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DBConnect_PhoneDialog extends JDialog
{
	JButton     ok          = null;
	JButton     back        = null;
	JPanel      panel       = null;
	BtnListener listener    = null;
	JTextField  phoneNumber = null;
	boolean     isOkPressed = false;
	Person      p           = null;

	public DBConnect_PhoneDialog()
	{
		setModal(true);
		setLayout(null);
        setBounds(300, 330, 240, 240);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listener = new BtnListener();
        panel = new JPanel();
		add(panel);
		panel.setBounds(0, 0, 240, 270);
		panel.setLayout(null);
        
        ok          = new JButton("ok");
    	back        = new JButton("back");

    	phoneNumber = new JTextField();

		ok.          setBounds(30, 180, 80, 30);
		back.        setBounds(130, 180, 80, 30);
        phoneNumber. setBounds(30, 20, 180, 30);

		ok.  addActionListener(listener);
		back.addActionListener(listener);
				
		panel.add(ok);
		panel.add(back);
		panel.add(phoneNumber);
	}
	
	public Person getPerson()
	{
        if( p == null)
        {
            p = new Person();
        }

		Phone phone = null;

        List<Phone> phones = p.getPhoneList();
        String[] phoneList = phoneNumber.getText().split(",");
        int i = 0;
        while(i < phoneList.length)
        {
            phone = new Phone();
            phone.setPhoneNumber(phoneList[i]);
            phone.setPerson_id(p);
            phones.add(phone);
            i++;
        }

		p.setPhoneList(phones);

		phoneNumber.setText("");

		return p;
	}

    public void setPerson(Person person)
    {
        p = person;
    }

    private class BtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getActionCommand().equals("ok"))
				isOkPressed = true;
			
			dispose();
		}
	}
}
