import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SkiResortApp extends Application{
	
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	
	public static void main(String[] args) throws Exception {
		getConnection();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//start screen
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(10);
		grid.setVgap(10);
		Scene start = new Scene(grid,500,500);
		
		stage.setScene(start);
		stage.setResizable(false);
		stage.show();
		
		//Employee
		BorderPane border = new BorderPane();
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(20));
		grid1.setHgap(10);
		grid1.setVgap(10);
		Scene employee = new Scene(border,500,500);
		border.setCenter(grid1);
		
		
		TextArea name = new TextArea();
		TextArea birth = new TextArea();
		TextArea email = new TextArea();
		TextArea num = new TextArea();
		TextArea user = new TextArea();
		PasswordField pass = new PasswordField();
		
		name.setPromptText("Name");
		name.setMaxSize(150, 10);
		birth.setMaxSize(150, 10);
		email.setMaxSize(150, 10);
		num.setMaxSize(150, 10);
		user.setMaxSize(150, 10);
		pass.setMaxSize(150, 10);
		
		Label name1 = new Label("Name:");
		Label birth1 = new Label("Date of Birth:");
		Label email1 = new Label("Email:");
		Label num1 = new Label("Phone Number:");
		Label pass1 = new Label("Password:");
		Button b = new Button("DONE");
		Button emplo = new Button("Employee");
		
		grid.add(name, 1, 0);
		grid.add(birth, 1, 1);
		grid.add(email, 1, 2);
		grid.add(num, 1, 3);
		grid.add(user, 1, 4);
		grid.add(pass, 1, 5);
		grid.add(b, 1, 6);
		grid.add(name1, 0, 0);
		grid.add(birth1, 0, 1);
		grid.add(email1, 0, 2);
		grid.add(num1, 0, 3);
		grid.add(pass1, 0, 5);
		grid.add(emplo, 1, 7);
		
		emplo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	stage.setScene(employee);
            	TextField emid = new TextField();
            	PasswordField empass = new PasswordField();
            	emid.setPromptText("Employee ID");
            	empass.setPromptText("Password");
            	grid1.add(emid, 0, 0);
            	grid1.add(empass, 0, 1);
            	Button enter = new Button("Enter");
            	grid1.add(enter, 0, 2);
           
      	  enter.setOnAction(new EventHandler<ActionEvent>() {
              @Override public void handle(ActionEvent e) {
            	  
            	  int id = Integer.parseInt(emid.getText());
            	  String pass = empass.getText();
            	  
            	  try{	
                  	st = con.createStatement();
                  	rs = st.executeQuery("SELECT * FROM skiresort.employees WHERE EmployeeID = " + id + ";");
                  		
                  		
                  	//rs.next();
                  	String datapass = "";
                  	while(rs.next()){
           		   		datapass = rs.getString(2);
                  	}
                  		
           		   	if((pass.equals(datapass))){
           		   		System.out.println("You are logged in");
           		   	}else{
           		   		System.out.println("Wrong Id or Password");
           		   	}
           		   		
            	  }catch(Exception e1){
           			System.out.println(e1);
            	  }
              	
              }
          });
      	    
     		   
     
            
            }
        });
		
		b.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
        
            	try{
            		
            		rs = st.executeQuery("SELECT COUNT(*) FROM skiresort.customer");
            		rs.next();
            	    int rowCount = rs.getInt(1) + 1;
            	    System.out.println(rowCount);
            		String n = name.getText(); 
            		String b = birth.getText();
            		String em = email.getText();
            		String number = num.getText();
            		String password = pass.getText();
            		
            		PreparedStatement pst = con.prepareStatement("INSERT INTO skiresort.customer (CustomerID, "
					+ "Password, Name, DateOfBirth, Email, PhoneNumber, PurchaseHistory)" 
            		+ "VALUES('"+ rowCount +"','" + password + "',"
					+ "'"+ n +"', '"+ b +"', '"+ em +"', '"+ number +"', 'none')");
				    
            		pst.execute();
					
				}catch (Exception e1){
					e1.printStackTrace();
				}
            }
        });
	}
	
	public static Connection getConnection() throws Exception{
		try{
		   String driver = "com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://localhost:3306/skiresort";
		   String username = "root";
		   String password = "Comp2650!";
		   Class.forName(driver);
		   
		   con = DriverManager.getConnection(url,username,password);
		   
		   st = con.createStatement();
		   rs = st.executeQuery("SELECT * FROM skiresort.customer;");
		   
		   while(rs.next()){
			   String name = rs.getString("Name");
			   int id = rs.getInt("CustomerID");
			   System.out.println("Name: " + name + "	" + "Id: " + id);
		   }
		   
		   return con;
		}catch(Exception e){
			System.out.println(e);
		}
		  
		return null;
	}
	
	
	
	
}
