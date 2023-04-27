import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.sql.*;
public class Main extends JFrame implements ActionListener{
	int count=0;
	JPanel centerPanel;
	JButton submitbtn, exitBtn, addBtn, viewBtn, updateBtn, deleteBtn;
	JTextField nameField;
	JRadioButton malebtn, femalebtn;
	JComboBox dropdown;
	JTable table;
	DefaultTableModel model;
	Database db = new Database();
	public Main() {
		window();
	}
	public void window() {
	setTitle("Database Application");
	this.setLayout(new BorderLayout());
	this.setSize(new Dimension(800, 600));
	this.setLocation(250,30);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	components();
	}
	public void components() {
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("PT Pudding");
		titlePanel.add(title);
		title.setFont(new Font("Poppins", Font.BOLD, 30));
		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(800, 50));
		southPanel.setBackground(Color.black);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2));
		centerComponents();
		
		addBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		
		
		add(titlePanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	public void centerComponents() {
				JPanel panel1 = new JPanel(); 
				panel1.setBackground(Color.yellow);
				
				JLabel formLabel = new JLabel("Form"); 
				formLabel.setPreferredSize(new Dimension(64,64));
				panel1.add(formLabel);
				
				JPanel panelKode = new JPanel();
				JLabel KodeLabel = new JLabel("Kode Menu: ");
				nameField = new JTextField(); // bikin textfield
				nameField.setPreferredSize(new Dimension(100, 30)); // setting ukuran buat textfield
				panelKode.add(KodeLabel);
				panelKode.add(nameField);
				panel1.add(panelKode);
				
				// field Nama
				JPanel panelName = new JPanel();
				JLabel NameLabel = new JLabel("Nama Menu: ");
				nameField = new JTextField();
				nameField.setPreferredSize(new Dimension(150,30));
				panelName.add(NameLabel);
				panelName.add(nameField);
				panel1.add(panelName);
				
				// field course
				JPanel panelHarga = new JPanel();
				JLabel HargaLabel = new JLabel("Harga Menu: ");
				nameField = new JTextField();
				nameField.setPreferredSize(new Dimension(150, 30));
				panelHarga.add(HargaLabel);
				panelHarga.add(nameField);
				panel1.add(panelHarga);
				
				// field message
				JPanel panelStok = new JPanel();
				JLabel StokLabel = new JLabel("Stok Menu: ");
				nameField = new JTextField();
				nameField.setPreferredSize(new Dimension(70, 30));
				panelStok.add(StokLabel);
				panelStok.add(nameField);
				panel1.add(panelStok);
				
				submitbtn = new JButton("Submit");
				submitbtn.addActionListener(this);
				panel1.add(submitbtn);
				
				JPanel panel2 = new JPanel();
				panel2.setBackground(Color.white);
				
				model = new DefaultTableModel();
				table = new JTable(model);
				model.addColumn("KodeMenu");
				model.addColumn("NamaMenu");
				model.addColumn("HargaMenu");
				model.addColumn("StokMenu");
				JScrollPane scroll= new JScrollPane(table);
				scroll.setPreferredSize(new Dimension(300, 200)); // setting ukuran space buat table
				panel2.add(scroll);
				
				panel2.setPreferredSize(new Dimension(100, 200));
				addBtn = new JButton("Add");
				viewBtn = new JButton("View");
				updateBtn = new JButton("Update");
				deleteBtn = new JButton("Delete");
				exitBtn = new JButton("Exit");
				
				panel2.add(addBtn);
				panel2.add(viewBtn);
				panel2.add(updateBtn);
				panel2.add(deleteBtn);
				panel2.add(exitBtn);
			
				centerPanel.add(panel1);
				centerPanel.add(panel2);
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== submitbtn) {
			// bikin apa
			System.out.println("Data telah disubmit");
		}
		else if (e.getSource()==exitBtn) {
			this.dispose(); // exit
		}
		else if(e.getSource()== addBtn) {
			add();
			JOptionPane.showMessageDialog(null, "Data telah ditambahkan");
		}
		else if(e.getSource() == viewBtn) {
			try {
				view();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("There is no data");
			}
		}
		else if(e.getSource()== updateBtn) {
			update();
		}
		else if(e.getSource()==deleteBtn) {
			delete();
		}
		
	}
	public void add() {
		String kode;
		kode = nameField.getText();
		
		String nama;
		nama = nameField.getText();
		
		String harga;
		harga = nameField.getText();
		
		String stok;
		stok = nameField.getText();
		
		Menu newMenu = new Menu(kode,nama,harga,stok);
		db.insert(newMenu);
			
	}
	public void view() throws SQLException {
		while(table.getRowCount()!=0) {
			model.removeRow(table.getRowCount()-1);
		}
		
		ResultSet dataMember = db.view();
		while(dataMember.next()) {
			Object[] row = {dataMember.getInt("id"), dataMember.getString("name"),
					dataMember.getString("gender"), dataMember.getString("course")}; // array baris
			
			model.addRow(row);
			
			System.out.print(dataMember.getInt("id")+ " ");
			System.out.print(dataMember.getString("name") + " ");
			System.out.print(dataMember.getString("gender")+ " ");
			System.out.println(dataMember.getString("course")+ " ");
		}
	}
	
	
	public void update() {
		String harga;
		harga = nameField.getText();
		
		String stok;
		stok = nameField.getText();
		
		String kode;
		kode = nameField.getText();
		db.update(harga, stok, kode);
	}
	
	
	public void delete() {
		String name = nameField.getText();
		db.delete(name);
	}


}
