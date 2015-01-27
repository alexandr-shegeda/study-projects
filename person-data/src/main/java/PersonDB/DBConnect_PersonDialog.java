package PersonDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	boolean     isOkPressed = false;
	

	public DBConnect_PersonDialog()
	{
		setModal(true);
		setLayout(null);
        setBounds(300, 300, 240, 240);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listener = new BtnListener();
        panel = new JPanel();
		add(panel);
		panel.setBounds(0, 0, 240, 240);
		panel.setLayout(null);
        
        ok      = new JButton("ok");
    	back    = new JButton("back");
    	id      = new JTextField();
    	name    = new JTextField();
    	surname = new JTextField();
    	age     = new JTextField();
		
		ok.     setBounds(30, 160, 80, 30);
		back.   setBounds(130, 160, 80, 30);
		id.     setBounds(30, 30, 180, 30);
		name.   setBounds(30, 60, 180, 30);
		surname.setBounds(30, 90, 180, 30);
		age.    setBounds(30, 120, 180, 30);
		
		ok.  addActionListener(listener);
		back.addActionListener(listener);
				
		panel.add(ok);
		panel.add(back);
		panel.add(id);
		panel.add(name);
		panel.add(surname);
		panel.add(age);	
	}
	
	public Person getPerson()
	{
		Person p = new Person();
		
		p.setId(Integer.parseInt(id.getText()));
		p.setFName(name.getText());
		p.setLName(surname.getText());
		p.setAge(Integer.parseInt(age.getText()));
		
		id.     setText("");
		name.   setText("");
		surname.setText("");
		age.    setText("");
		
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
