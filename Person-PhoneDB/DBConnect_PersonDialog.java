package PersonDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DBConnect_PersonDialog extends JDialog
{
	JButton     ok          = null;
	JButton     back        = null;
	JPanel      panel       = null;
	BtnListener listener    = null;
	JTextField  id          = null;
	JTextField  name        = null;
	JTextField  surname     = null;
	JTextField  age         = null;
	JTextField  phoneNumber = null;
	boolean     isOkPressed = false;
	

	public DBConnect_PersonDialog()
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
    	id          = new JTextField();
    	name        = new JTextField();
    	surname     = new JTextField();
    	age         = new JTextField();
    	phoneNumber = new JTextField();

		ok.          setBounds(30, 180, 80, 30);
		back.        setBounds(130, 180, 80, 30);
		id.          setBounds(30, 20, 180, 30);
		name.        setBounds(30, 50, 180, 30);
		surname.     setBounds(30, 80, 180, 30);
		age.         setBounds(30, 110, 180, 30);
		phoneNumber. setBounds(30, 140, 180, 30);

		ok.  addActionListener(listener);
		back.addActionListener(listener);
				
		panel.add(ok);
		panel.add(back);
		panel.add(id);
		panel.add(name);
		panel.add(surname);
		panel.add(age);	
		panel.add(phoneNumber);
	}
	
	public Person getPerson()
	{
		Person p = new Person();
		Phone phone = null;

        ArrayList<Phone> phones = new ArrayList<Phone>();
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

		p.setId(Integer.parseInt(id.getText()));
		p.setFName(name.getText());
		p.setLName(surname.getText());
		p.setAge(Integer.parseInt(age.getText()));
		p.setPhoneList(phones);

		id.         setText("");
		name.       setText("");
		surname.    setText("");
		age.        setText("");
		phoneNumber.setText("");

		return p;
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
