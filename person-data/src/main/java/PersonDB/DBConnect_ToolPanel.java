package PersonDB;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class DBConnect_ToolPanel extends JPanel 
{
	public DBConnect_ToolPanel(Commands cmd) 
	{
		setLayout(null);
        setBounds(0, 0, 600, 400);
        
        ButtonGroup bg = new ButtonGroup();
        JToggleButton btnConnect    = new JToggleButton("Connect");
        JToggleButton btnDisConnect = new JToggleButton("Disconnect");
        
        JButton btnCreate = new JButton("Create");
        JButton btnRead   = new JButton("Read");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        	
        bg.add(btnConnect);
        bg.add(btnDisConnect);
        add(btnConnect);
        add(btnDisConnect);
        add(btnCreate);
        add(btnRead);
        add(btnUpdate);
        add(btnDelete);
        
        btnConnect.setBounds(25, 0 + 310, 125, 20);
        btnDisConnect.setBounds(25, 30 + 310, 125, 20);
        btnCreate.setBounds(100 * 0 + 200, 310, 75, 50);
        btnRead.setBounds(100 * 1 + 200, 310, 75, 50);
        btnUpdate.setBounds(100 * 2 + 200, 310, 75, 50);
        btnDelete.setBounds(100 * 3 + 200, 310, 75, 50);
        
        btnConnect.   addActionListener( cmd.actionConnect );
        btnDisConnect.addActionListener( cmd.actionDisConnect );
        btnCreate.    addActionListener( cmd.actionCreate );
        btnRead.      addActionListener( cmd.actionRead );
        btnUpdate.    addActionListener( cmd.actionUpdate );
        btnDelete.    addActionListener( cmd.actionDelete );
	}
}
