package PersonDB;

import javax.swing.*;

public class DBConnect_ToolPanel extends JPanel 
{
	public DBConnect_ToolPanel(Commands cmd) 
	{
		setLayout(null);
        setBounds(0, 0, 800, 400);
        
        ButtonGroup bg = new ButtonGroup();
        JToggleButton btnConnect    = new JToggleButton("Connect");
        JToggleButton btnDisConnect = new JToggleButton("Disconnect");

        String[] daoList = {"MySQL","Mock","Hibernate","MongoDB","JSON","XML","CSV"};

        JComboBox daoBox = new JComboBox(daoList);

        
        JButton btnCreate = new JButton("Create");
        JButton btnRead   = new JButton("Read");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnAddNum = new JButton("Add Phone");

        bg.add(btnConnect);
        bg.add(btnDisConnect);
        add(btnConnect);
        add(btnDisConnect);
        add(btnCreate);
        add(btnRead);
        add(btnUpdate);
        add(btnDelete);
        add(btnAddNum);
        add(daoBox);

        daoBox.setBounds       (10            , 310     , 90  ,20);
        btnConnect.setBounds   (125          , 0 + 310  , 100 , 20);
        btnDisConnect.setBounds(125          , 30 + 310 , 100 , 20);
        btnCreate.setBounds    (100 * 0 + 240, 310      , 75  , 50);
        btnRead.setBounds      (100 * 1 + 230, 310      , 75  , 50);
        btnUpdate.setBounds    (100 * 2 + 220, 310      , 75  , 50);
        btnDelete.setBounds    (100 * 3 + 210, 310      , 75  , 50);
        btnAddNum.setBounds    (100 * 4 + 200, 310      , 100 , 50);

        btnConnect.   addActionListener( cmd.actionConnect    );
        btnDisConnect.addActionListener( cmd.actionDisConnect );
        btnCreate.    addActionListener( cmd.actionCreate );
        btnRead.      addActionListener( cmd.actionRead   );
        btnUpdate.    addActionListener( cmd.actionUpdate );
        btnDelete.    addActionListener( cmd.actionDelete );
        btnAddNum.    addActionListener( cmd.actionAddNum );
        daoBox.       addActionListener( cmd.actionBoxChange);
	}
}
