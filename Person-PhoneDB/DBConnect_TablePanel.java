package PersonDB;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class DBConnect_TablePanel extends JPanel 
{
	static PersonTableModel mdlPerson = null;
    static JTable           tblPerson = null;

    static PhoneTableModel  mdlPhone = null;
	static JTable           tblPhone = null;

	public DBConnect_TablePanel() 
	{
        setLayout(null);
		mdlPerson = new PersonTableModel();
		tblPerson = new JTable(mdlPerson);
		JScrollPane scrollPane = new JScrollPane(tblPerson);
        scrollPane.setBounds(100,0,400,300);
		add(scrollPane);

        mdlPhone = new PhoneTableModel();
        tblPhone = new JTable(mdlPhone);
        JScrollPane scroll = new JScrollPane(tblPhone);
        scroll.setBounds(500,0,200,300);

        add(scroll);
		setBounds(0, 0, 800, 300);

        tblPerson.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){}
            @Override
            public void mousePressed(MouseEvent e){}
            @Override
            public void mouseReleased(MouseEvent e){  Commands.updatePhones();  }
            @Override
            public void mouseEntered(MouseEvent e){}
            @Override
            public void mouseExited(MouseEvent e){}
        });
        tblPerson.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    Commands.updatePhones();
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    Commands.updatePhones();
                }
            }
        });
	}
}
