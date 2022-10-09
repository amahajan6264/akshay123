package com.data.insert;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiInsertDataInDatabase extends JFrame implements ActionListener, WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9195226820383562827L;
	private JLabel sname, scollege, sid, sclass, sfees;
	private JTextField tname, tcollege, tid, tclass, tfees;
	private Connection con;
	private JButton b;
	private PreparedStatement ps;
	private static final String insert_Query = "insert into hkbuddy values(?,?,?,?,?)";

	// constructor
	public GuiInsertDataInDatabase() {
		System.out.println("GIDID is created");
		setTitle("GuiApp");
		setLayout(null);

		sname = new JLabel("Name");
		add(sname);
		sname.setBounds(50, 50, 100, 30);
		tname = new JTextField(10);
		add(tname);
		tname.setBounds(150, 50, 150, 30);

		scollege = new JLabel("college");
		add(scollege);
		scollege.setBounds(50, 100, 100, 30);
		tcollege = new JTextField(10);
		add(tcollege);
		tcollege.setBounds(150, 100, 150, 30);

		sid = new JLabel("Id");
		add(sid);
		sid.setBounds(50, 150, 100, 30);
		tid = new JTextField(10);
		add(tid);
		tid.setBounds(150, 150, 150, 30);

		sclass = new JLabel("class");
		add(sclass);
		sclass.setBounds(50, 200, 100, 50);
		tclass = new JTextField(10);
		add(tclass);
		tclass.setBounds(150, 200, 150, 30);

		sfees = new JLabel("Fees");
		add(sfees);
		sfees.setBounds(50, 250, 100, 30);
		tfees = new JTextField(10);
		add(tfees);
		tfees.setBounds(150, 250, 150, 30);

		b = new JButton("submit");
		add(b);
		b.setBounds(120, 300, 100, 30);
		b.addActionListener(this);
		this.addWindowListener(this);
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initalizeJdbc(); // call jdbc method
		System.out.println("constructor end");
	}

	// initialize jdbc
	private void initalizeJdbc() {
		System.out.println("initialize jdbc");
		try {
			// establish connection object
			con = DriverManager.getConnection("jdbc:mysql://localhost/ab", "root", "rootpassword");
			// create prepare statement object
			ps = con.prepareStatement(insert_Query);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// main
	public static void main(String[] args) {
		// GuIapplication is created
		new GuiInsertDataInDatabase();
	}// main

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Insert Action perform");
		try {
			ps.setString(1, tname.getText());
			ps.setString(2, tcollege.getText());
			ps.setInt(3, Integer.parseInt(tid.getText()));
			ps.setString(4, tclass.getText());
			ps.setDouble(5, Double.parseDouble(tfees.getText()));
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("Record is not inserted");
				showMessageDialog(this, "Record is not inserted");
			} else {
				System.out.println("Record is  inserted");
				showMessageDialog(this, "Record is inserted");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void showMessageDialog(GuiInsertDataInDatabase guiInsertDataInDatabase, String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if (ps != null) {
				System.out.println("close : " + ps.getClass());
				ps.close();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			if (con != null) {
				System.out.println("close : " + con.getClass());
				con.close();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
} // class
