package PersonDB;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class DBConnect_TablePanel extends JPanel 
{
	static PersonTableModel mdlPerson = null;
	static JTable           tblPerson = null;

	public DBConnect_TablePanel() 
	{
		mdlPerson = new PersonTableModel();
		tblPerson = new JTable(mdlPerson);
		JScrollPane scrollPane = new JScrollPane(tblPerson);
		add(scrollPane);
		setBounds(0, 0, 595, 300);
	}
}
