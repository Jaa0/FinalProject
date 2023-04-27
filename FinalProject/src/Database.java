import java.sql.*;
public class Database {
	Connection con;
	Statement s;
	PreparedStatement ps;
	ResultSet rs;
	public Database() {
		// TODO Auto-generated constructor stub
	}
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/finalproject";
		String username= "root";
		String password = "";
		try {
			con = DriverManager.getConnection(url,username,password);
			s = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(Menu menu) {
		try {
			con.prepareStatement("INSERT INTO menu (kode,nama,harga,stok) VALUES (?, ?, ?, ?)");
			ps.setString(1, menu.getKodeMenu());
			ps.setString(2, menu.getNamaMenu());
			ps.setString(3, menu.getHargaMenu());
			ps.setString(4, menu.getStokMenu());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to insert");
		}
	}
	public void insert(String kode, String nama, String harga, String stok) {
		try {
			ps = con.prepareStatement("INSERT INTO member (name, gender, course) VALUES (?, ?, ?)");
			ps.setString(1, kode);
			ps.setString(2, nama);
			ps.setString(3, harga);
			ps.setString(4, stok);
			ps.execute();
//			int id;
//			ps.setInt(0, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet view() {
		try {
			rs = s.executeQuery("select * from menu");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void update(String harga, String stok,String kode ) {
		try {
			ps = con.prepareStatement("update menu set harga = ?, stok = ? where kode = ?");
			ps.setString(1, harga);
			ps.setString(2, stok);
			ps.setString(3, kode);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String kode) {
		try {
			ps = con.prepareStatement("delete from menu where kode = ?");
			ps.setString(1, kode);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
