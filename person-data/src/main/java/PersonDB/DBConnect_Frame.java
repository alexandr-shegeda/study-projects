package PersonDB;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DBConnect_Frame extends JFrame
{
	public DBConnect_Frame()
    {
        setLayout(null);
        setTitle("Person DB");
        setBounds(200, 200, 600, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Commands cmd = new Commands();
        DBConnect_TablePanel tbl = new DBConnect_TablePanel();
        DBConnect_ToolPanel  tol = new DBConnect_ToolPanel( cmd );
        
        cmd.panelWork = tbl;
        
        JPanel pp = (JPanel) this.getContentPane();
        pp.setLayout(null);
        pp.add(tbl);
        pp.add(tol);

        setVisible(true);
    }
}
