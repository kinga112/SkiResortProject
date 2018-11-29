import java.sql.*;


public class DBconnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;
		
	public void DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skiresort","root","Comp2650!");
			st = con.createStatement();
			
		}catch(Exception ex){
				System.out.println("Error 1: " + ex);
		}
		
	}
	
	public void getData(){
		
		String query = ("SELECT * FROM skiresort.costumer;");
		
		try{
			
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt("CustumerID");
				String name = rs.getString("name");
				System.out.println("Name: " + name + "	" + "ID: " + id);
			}
			
		}catch(Exception ex){
			System.out.println("Error 2: " + ex);
		}
		finally{
			try{
				con.close();
				st.close();
				rs.close();
			}catch(Exception ex){
				System.out.println("Error 3: " + ex);
			}
		}
	}
	
}