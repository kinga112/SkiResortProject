/*
* Alden King, Matt White
* Group #10
* Comp 2650, Databases
* Ski Resort Application
*/


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Final extends Application {
	
	
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	
	
	public static void main(String[] args) throws Exception {
		getConnection();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane mainBorderPane = new BorderPane();
		BorderPane loginOrCreateAccountBorderPane = new BorderPane();
		BorderPane createAccountBorderPane = new BorderPane();
		BorderPane loginBorderPane = new BorderPane();
		BorderPane emplLoginBorderPane = new BorderPane();
		BorderPane custTicketBorderPane = new BorderPane();
		custTicketBorderPane.setStyle("-fx-background-color: #d9d9d9");
		
		Scene mainScene = new Scene(mainBorderPane, 600,600);
		Scene loginOrCreateAccountScene = new Scene(loginOrCreateAccountBorderPane, 600,600);
		Scene createAccountScene = new Scene(createAccountBorderPane, 600,600);
		Scene loginScene = new Scene(loginBorderPane, 600,600);
		Scene emplLoginScene = new Scene(emplLoginBorderPane, 600,600);
		Scene custTicketScene = new Scene(custTicketBorderPane, 600, 600);
		
		StackPane mainCenterStackPane = new StackPane();
		StackPane loginOrCreateAccountTopPane = new StackPane();
		StackPane loginOrCreateAccountBottomPane = new StackPane();
		StackPane loginOrCreateCenterPane = new StackPane();
		StackPane loginTopPane = new StackPane();
		StackPane emplLoginTopPane = new StackPane();
		StackPane loginBottomPane = new StackPane();
		StackPane emplLoginBottomPane = new StackPane();
		StackPane createAccountTopPane = new StackPane();
		StackPane createAccountBottomPane = new StackPane();	
		StackPane custTicketTopPane = new StackPane();
		StackPane custTicketBottomPane = new StackPane();
		
		GridPane custTicketGridPane = new GridPane();
		GridPane createAccountGridPane = new GridPane();
		GridPane loginGridPane = new GridPane();
		GridPane emplLoginGridPane = new GridPane();
		Text welcomeTxt = new Text("Ski Resort Application");
		Text loginText = new Text("Please enter your email and password");
		
		Button custButton = new Button("Customer");
		Button emplButton = new Button("Employee");
		Button loginOrCreateAccountCancelButton = new Button("Cancel");
		Button loginOkButton = new Button("Enter");
		Button loginCancelButton = new Button("Cancel");
		Button emplLoginOkButton = new Button("Enter");
		Button emplLoginCancelButton = new Button("Cancel");
		Button createAccountOkButton = new Button("Enter");
		Button createAccountCancelButton = new Button("Cancel");
		
		Text createAccountText = new Text("Welcome! Please enter the following information:");
		
		Label nameLabel = new Label("Name:");
		Label dateOfBirthLabel = new Label("Date of Birth (MM/DD/YYYY):");
		Label emailLabel = new Label("Email Address:");
		Label phoneLabel = new Label("Phone:");
		Label passwordLabel = new Label("Password:");
		Label missingInfo = new Label("You left some information empty");
		
		missingInfo.setTextFill(Color.RED);
		missingInfo.setVisible(false);
		
		TextField nameBox = new TextField();
		TextField dateOfBirthBox = new TextField();
		TextField emailBox = new TextField();
		TextField phoneBox = new TextField();
		PasswordField password = new PasswordField();
		
		Text loginOrCreateText = new Text("If you are a returning customer, please sign in below. If this is your first time here, please create an account.");
		loginOrCreateText.setWrappingWidth(primaryStage.getWidth());
		loginOrCreateAccountTopPane.getChildren().add(loginOrCreateText);
		loginOrCreateAccountBottomPane.getChildren().add(loginOrCreateAccountCancelButton);
		
		Button loginButton = new Button("Login");
		Button createAccountButton = new Button("Create Account");
		
		loginOrCreateCenterPane.getChildren().addAll(loginButton, createAccountButton);
		loginButton.setTranslateX(-100);
		loginButton.setMinHeight(100);
		loginButton.setMinWidth(150);
		
		createAccountButton.setTranslateX(100);
		createAccountButton.setMinHeight(100);
		createAccountButton.setMinWidth(150);

		loginOrCreateAccountBorderPane.setTop(loginOrCreateAccountTopPane);
		loginOrCreateAccountBorderPane.setCenter(loginOrCreateCenterPane);
		loginOrCreateAccountBorderPane.setBottom(loginOrCreateAccountBottomPane);
		
		loginOkButton.setTranslateX(250);
		loginCancelButton.setTranslateX(180);
		loginOkButton.setTranslateY(-25);
		loginCancelButton.setTranslateY(-25);
		emplLoginOkButton.setTranslateX(250);
		emplLoginCancelButton.setTranslateX(180);
		emplLoginOkButton.setTranslateY(-25);
		emplLoginCancelButton.setTranslateY(-25);
		createAccountOkButton.setTranslateX(250);
		createAccountCancelButton.setTranslateX(180);
		createAccountOkButton.setTranslateY(-25);
		createAccountCancelButton.setTranslateY(-25);
		loginOrCreateAccountCancelButton.setTranslateX(180);
		loginOrCreateAccountCancelButton.setTranslateY(-25);
		createAccountOkButton.setTranslateX(250);
		createAccountCancelButton.setTranslateX(180);
		createAccountOkButton.setTranslateY(-25);
		createAccountCancelButton.setTranslateY(-25);
		
		
		loginOrCreateAccountTopPane.setStyle("-fx-background-color: #99c0ff");
		loginOrCreateAccountTopPane.setMinHeight(100);
		
		createAccountTopPane.setStyle("-fx-background-color: #99c0ff");
		createAccountTopPane.setMinHeight(100);
		
		custTicketTopPane.setStyle("-fx-background-color: #99c0ff");
		custTicketTopPane.setMinHeight(100);
		
		loginTopPane.setStyle("-fx-background-color: #99c0ff");
		loginTopPane.setMinHeight(100);
		
		emplLoginTopPane.setStyle("-fx-background-color: #99c0ff");
		emplLoginTopPane.setMinHeight(100);
		
		Label custEmailLabel = new Label("email:");
		Label custPasswordLabel = new Label("password:");
		Label emplIdLabel = new Label("ID:");
		Label emplPasswordLabel = new Label("password:");
		
		TextField custEmailBox = new TextField();
		PasswordField custPasswordBox = new PasswordField();
		
		TextField emplIdBox = new TextField();
		PasswordField emplPasswordBox = new PasswordField();
		
		createAccountTopPane.getChildren().add(createAccountText);
		createAccountBorderPane.setTop(createAccountTopPane);
			
		createAccountBorderPane.setCenter(createAccountGridPane);
		
		custTicketBorderPane.setCenter(custTicketGridPane);
		
		Button buyLiftTicket = new Button("Buy Ticket");
		Button buyLesson = new Button("Buy Lesson");
		Label custTicketBought = new Label("You have successfully bought a ticket");
		Label custLessonBought = new Label("You have successfully bought a lesson");
		
		buyLiftTicket.setTranslateX(150);
		buyLiftTicket.setTranslateY(150);
		buyLiftTicket.setPrefHeight(60);
		buyLiftTicket.setPrefWidth(100);
		
		buyLesson.setTranslateX(150);
		buyLesson.setTranslateY(150);
		buyLesson.setPrefHeight(60);
		buyLesson.setPrefWidth(100);
		
		custTicketBought.setTranslateX(150);
		custTicketBought.setTranslateY(150);
		custTicketBought.setPrefHeight(60);


		custLessonBought.setTranslateX(150);
		custLessonBought.setTranslateY(150);
		custLessonBought.setPrefHeight(60);
		
		custTicketGridPane.add(buyLiftTicket, 0, 0);
		custTicketGridPane.add(custTicketBought, 0, 1);
		custTicketGridPane.add(buyLesson, 0, 2);
		custTicketGridPane.add(custLessonBought, 0, 3);
		
		custTicketBought.setVisible(false);
		custLessonBought.setVisible(false);
		
		nameBox.setMinWidth(200);
		dateOfBirthBox.setMinWidth(200);
		emailBox.setMinWidth(200);
		phoneBox.setMinWidth(200);

		createAccountBottomPane.getChildren().addAll(createAccountOkButton, createAccountCancelButton);

		createAccountBorderPane.setBottom(createAccountBottomPane);
		
		custTicketBorderPane.setBottom(custTicketBottomPane);
		custTicketBorderPane.setTop(custTicketTopPane);
		
		createAccountGridPane.add(nameLabel, 0, 0);
		createAccountGridPane.add(nameBox, 1, 0);
		createAccountGridPane.add(dateOfBirthLabel, 0, 1);
		createAccountGridPane.add(dateOfBirthBox, 1, 1);
		createAccountGridPane.add(emailLabel, 0, 2);
		createAccountGridPane.add(emailBox, 1, 2);
		createAccountGridPane.add(phoneLabel, 0, 4);
		createAccountGridPane.add(phoneBox, 1, 4);
		createAccountGridPane.setVgap(10);
		createAccountGridPane.add(password, 1, 3);
		createAccountGridPane.add(passwordLabel, 0, 3);
		createAccountGridPane.add(missingInfo, 0, 5);
		createAccountGridPane.setTranslateX(60);
		createAccountGridPane.setTranslateY(50);
		
		Label custTopPaneText = new Label("You have successfully logged in");
		Button custTicketLogoutButton = new Button("Logout");
		custTicketLogoutButton.setTranslateX(250);
		custTicketLogoutButton.setTranslateY(-25);
		
		
		loginTopPane.getChildren().add(loginText);
		custTicketTopPane.getChildren().add(custTopPaneText);
		custTicketBottomPane.getChildren().add(custTicketLogoutButton);
		loginBorderPane.setTop(loginTopPane);
		emplLoginBorderPane.setTop(emplLoginTopPane);
		loginGridPane.add(custEmailLabel, 0, 0);
		loginGridPane.add(custEmailBox, 1, 0);
		loginGridPane.add(custPasswordLabel, 0, 1);
		loginGridPane.add(custPasswordBox, 1, 1);
		emplLoginGridPane.add(emplIdLabel, 0, 0);
		emplLoginGridPane.add(emplIdBox, 1, 0);
		emplLoginGridPane.add(emplPasswordLabel, 0, 1);
		emplLoginGridPane.add(emplPasswordBox, 1, 1);
		loginBorderPane.setCenter(loginGridPane);
		emplLoginBorderPane.setCenter(emplLoginGridPane);
		loginBottomPane.getChildren().addAll(loginOkButton, loginCancelButton);
		emplLoginBottomPane.getChildren().addAll(emplLoginOkButton, emplLoginCancelButton);
		loginBorderPane.setBottom(loginBottomPane);
		emplLoginBorderPane.setBottom(emplLoginBottomPane);
		loginGridPane.setVgap(10);
		loginGridPane.setTranslateX(60);
		loginGridPane.setTranslateY(50);
		emplLoginGridPane.setVgap(10);
		emplLoginGridPane.setTranslateX(60);
		emplLoginGridPane.setTranslateY(50);
		
		Label custWrongEmail = new Label("Wrong Email or Password");
		Label emplWrongId = new Label("Wrong ID or Password");
		custWrongEmail.setTextFill(Color.RED);
		emplWrongId.setTextFill(Color.RED);
		emplLoginGridPane.add(emplWrongId, 0, 5);
		loginGridPane.add(custWrongEmail, 0, 5);
		custWrongEmail.setVisible(false);
		emplWrongId.setVisible(false);
		
		primaryStage.setTitle("Ski Resort Application");
		primaryStage.setScene(mainScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		loginBorderPane.setStyle("-fx-background-color: #d9d9d9");
		emplLoginBorderPane.setStyle("-fx-background-color: #d9d9d9");
		
		mainBorderPane.setStyle("-fx-background-color: #99c0FF");
		createAccountBorderPane.setStyle("-fx-background-color: #d9d9d9");
		
		loginOrCreateAccountBorderPane.setStyle("-fx-background-color: #d9d9d9");
		
		welcomeTxt.setTranslateY(-80);
		welcomeTxt.setFont(new Font("Times New Roman", 40));
		
		emplButton.setTranslateX(70);
		emplButton.setTranslateY(50);
		emplButton.setPrefHeight(60);
		emplButton.setPrefWidth(100);
		mainCenterStackPane.getChildren().addAll(welcomeTxt,custButton, emplButton);
		mainBorderPane.setCenter(mainCenterStackPane);
	
		custButton.setTranslateX(-70);
		custButton.setTranslateY(50);
		custButton.setPrefHeight(60);
		custButton.setPrefWidth(100);
		
		Label emplLoginText = new Label("Please enter your ID and Password");
		emplLoginTopPane.getChildren().add(emplLoginText);
		
		//Instrustor Scene
		BorderPane instructorBorderPane = new BorderPane();
        Scene instructorScene = new Scene(instructorBorderPane, 600,600);
        StackPane instructorCenterPane = new StackPane();
        GridPane instructorCenterGridPane = new GridPane();
        StackPane instructorTopPane = new StackPane();
        StackPane instructorBottomPane = new StackPane();
        Text instructorWelcomeText = new Text("Instructor View");
        Button instructorLogoutButton = new Button("Logout");
        Button scheduleLessonButton = new Button("Schedule a Lesson");
        Button viewLessonButton = new Button("View Lessons");
        Button cancelLessonButton = new Button("Cancel a Lesson");
        
		
		instructorBottomPane.getChildren().add(instructorLogoutButton);
        
        scheduleLessonButton.setMinHeight(60);
        scheduleLessonButton.setMinWidth(100);
         
        viewLessonButton.setMinHeight(60);
        viewLessonButton.setMinWidth(100);
         
        cancelLessonButton.setMinHeight(60);
        cancelLessonButton.setMinWidth(100);
         
        instructorLogoutButton.setTranslateX(250);
        instructorLogoutButton.setTranslateY(-25);

        instructorCenterGridPane.setTranslateY(100);
        instructorCenterGridPane.setHgap(50);
         
        instructorCenterGridPane.add(scheduleLessonButton, 0, 0);
        instructorCenterGridPane.add(viewLessonButton, 1, 0);
        instructorCenterGridPane.add(cancelLessonButton, 2, 0);
        instructorCenterPane.getChildren().add(instructorCenterGridPane);
        instructorCenterGridPane.setAlignment(Pos.TOP_CENTER);
         
        
        instructorTopPane.setMinHeight(100);
        instructorTopPane.setStyle("-fx-background-color: #99c0ff");
        instructorTopPane.setMinWidth(600);
        instructorBorderPane.setStyle("-fx-background-color: #d9d9d9");
         
        instructorBorderPane.setCenter(instructorCenterPane);
        instructorBorderPane.setTop(instructorTopPane);
        instructorBorderPane.setBottom(instructorBottomPane);
        
        
      //employee scene declarations
        BorderPane employeeBorderPane = new BorderPane();
        Scene employeeScene = new Scene(employeeBorderPane, 600,600);
        StackPane employeeTopPane = new StackPane();
        StackPane employeeBottomPane = new StackPane();
        StackPane employeeCenterPane = new StackPane();
        GridPane employeeClockCenterGridPane = new GridPane();
        GridPane employeeCenterGridPane = new GridPane();
        Text employeeWelcomeText = new Text("Employee View");
        Text clockText = new Text("Please clock in");
        Button employeeCancelButton = new Button("Cancel");
        Button clockButton = new Button("Clock In");
        Button lessonButton = new Button("Book a Lesson");
        Button scheduleButton = new Button("Show Schedule");
        
        
        //lesson scene declarations
        BorderPane lessonBorderPane = new BorderPane();
        Scene lessonScene = new Scene(lessonBorderPane, 600, 600);
        StackPane lessonTopPane = new StackPane();
        StackPane lessonCenterPane = new StackPane();
        StackPane lessonBottomPane = new StackPane();
        GridPane lessonGridPane = new GridPane();
        TextField lessonIDField = new TextField();
        TextField employeeIDField = new TextField();
        TextField customerIDField = new TextField();
        TextField lessonDateField = new TextField();
        Label lessonIDLabel = new Label("Lesson ID:");
        Label employeeIDLabel = new Label("Employee ID:");
        Label customerIDLabel = new Label("Customer ID:");
        Label lessonDateLabel = new Label("Lesson Date:");
        Text lessonWelcomeText = new Text("Employee View - Book a Lesson");
        Button lessonCancelButton = new Button("Cancel");
        Button lessonOkButton = new Button("Enter");
        
        
        //beginning of lesson scene code
        lessonTopPane.setMinHeight(100);
        lessonTopPane.getChildren().add(lessonWelcomeText);
        
        lessonGridPane.add(lessonIDLabel, 0, 0);
        lessonGridPane.add(employeeIDLabel, 0, 1);
        lessonGridPane.add(customerIDLabel, 0, 2);
        lessonGridPane.add(lessonDateLabel, 0, 3);
        lessonGridPane.add(lessonIDField, 1, 0);
        lessonGridPane.add(employeeIDField, 1, 1);
        lessonGridPane.add(customerIDField, 1, 2);
        lessonGridPane.add(lessonDateField, 1, 3);
        
        lessonGridPane.setHgap(10);
        lessonGridPane.setVgap(10);
        lessonGridPane.setTranslateX(60);
        lessonGridPane.setTranslateY(50);
        
        lessonCenterPane.getChildren().add(lessonGridPane);
        lessonBottomPane.getChildren().add(lessonCancelButton);
        lessonBottomPane.getChildren().add(lessonOkButton);
       	lessonCancelButton.setTranslateX(250);
        lessonCancelButton.setTranslateY(-25);
        
        lessonBorderPane.setStyle("-fx-background-color: #d9d9d9");
        lessonTopPane.setStyle("-fx-background-color: #99c0ff");
        
        lessonOkButton.setTranslateX(250);
        lessonCancelButton.setTranslateX(180);
        lessonOkButton.setTranslateY(-25);
        lessonCancelButton.setTranslateY(-25);
        
        lessonBorderPane.setTop(lessonTopPane);
       	lessonBorderPane.setCenter(lessonCenterPane);
    	lessonBorderPane.setBottom(lessonBottomPane);
        //end of lesson scene code


        //beginning of employee scene code
    	employeeBottomPane.getChildren().add(employeeCancelButton);
       	employeeCancelButton.setTranslateX(250);
       	employeeCancelButton.setTranslateY(-25);
       	employeeCenterGridPane.setTranslateY(100);
        employeeCenterGridPane.setHgap(50);
        employeeCenterGridPane.add(lessonButton, 0, 0);
        
        lessonButton.setMinHeight(60);
        lessonButton.setMinWidth(100);
        scheduleButton.setMinHeight(60);
        scheduleButton.setMinWidth(100);

        employeeClockCenterGridPane.add(clockButton, 0, 0);
        employeeClockCenterGridPane.setAlignment(Pos.TOP_CENTER);
        employeeCenterPane.getChildren().add(employeeClockCenterGridPane);
        employeeCenterPane.getChildren().add(clockText);
        clockText.setTranslateY(-200);
        
        employeeCenterGridPane.setAlignment(Pos.TOP_CENTER);
        
 		employeeTopPane.getChildren().add(employeeWelcomeText);
        employeeTopPane.setMinHeight(100);
        employeeTopPane.setStyle("-fx-background-color: #99c0ff");
        employeeTopPane.setMinWidth(600);
        employeeBorderPane.setStyle("-fx-background-color: #d9d9d9");
       
 
        createAccountOkButton.setTranslateX(250);
        createAccountCancelButton.setTranslateX(180);
        createAccountOkButton.setTranslateY(-25);
        createAccountCancelButton.setTranslateY(-25);
        
        employeeBorderPane.setTop(employeeTopPane);
        employeeBorderPane.setCenter(employeeCenterPane);
        employeeBorderPane.setBottom(employeeBottomPane);
        

        //end of employee scene code     

		
        
		
		//when customer button is pressed
		custButton.setOnAction(custButtonEvent -> 
		{
			
			primaryStage.setScene(loginOrCreateAccountScene);
		
			
			createAccountButton.setOnAction(createAccountButtonEvent ->
			{
				
				primaryStage.setScene(createAccountScene);
				
				createAccountCancelButton.setOnAction(cancelButtonEvent ->
				{
					missingInfo.setVisible(false);
					primaryStage.setScene(loginOrCreateAccountScene);	
					emailBox.clear();
					custPasswordBox.clear();
					dateOfBirthBox.clear();
					phoneBox.clear();
					password.clear();
					nameBox.clear();
				});
				
				createAccountOkButton.setOnAction(okButtonEvent -> 
				{

					
						try{
							
							if(nameBox.getText().equals("")){
		            			missingInfo.setVisible(true);
		            		}else if(dateOfBirthBox.getText().equals("")){
		            			missingInfo.setVisible(true);
		            		}else if(emailBox.getText().equals("")){
		            			missingInfo.setVisible(true);
		            		}else if(phoneBox.getText().equals("")){
		            			missingInfo.setVisible(true);
		            		}else if(password.getText().equals("")){
		            			missingInfo.setVisible(true);
		            		}else{
		            		
		            			//rs = st.executeQuery("SELECT COUNT(*) FROM skiresort.customer");
			            		//rs.next();
			            	    //int rowCount = rs.getInt(1) + 1;
			            	    //System.out.println(rowCount);
			            		String n = nameBox.getText(); 
			            		String b = dateOfBirthBox.getText();
			            		String em = emailBox.getText();
			            		String number = phoneBox.getText();
			            		String pass = password.getText();
			 
			            		PreparedStatement pst = con.prepareStatement("INSERT INTO skiresort.customer ("
								+ "Password, Name, DateOfBirth, Email, PhoneNumber, PurchaseHistory)" 
			            		+ "VALUES('"+ pass + "',"
								+ "'"+ n +"', '"+ b +"', '"+ em +"', '"+ number +"', 'none')");
							    
			            		pst.execute();
								
								primaryStage.setScene(loginOrCreateAccountScene);
								emailBox.clear();
								custPasswordBox.clear();
								dateOfBirthBox.clear();
								phoneBox.clear();
								password.clear();
								nameBox.clear();
		            		}
		            		
		            	
						}catch (Exception e1){
							e1.printStackTrace();
						}
						
				});
			
			});
			
			loginButton.setOnAction(loginButtonEvent ->
			{
				
				primaryStage.setScene(loginScene);
				
				loginCancelButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						primaryStage.setScene(loginOrCreateAccountScene);
						custEmailBox.clear();
						custPasswordBox.clear();
						custWrongEmail.setVisible(false);
		           }	
				});
				
				
				
				loginOkButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
					
					custTicketLogoutButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent e) {
							primaryStage.setScene(loginScene);
							custEmailBox.clear();
							custPasswordBox.clear();
						}
					});
						
						
					String custEmail = custEmailBox.getText();
					String custPassword = custPasswordBox.getText();
					
					
	            	  try{	
	                  	st = con.createStatement();
	                  	rs = st.executeQuery("SELECT * FROM skiresort.customer WHERE Email = '" + custEmail + "';");
	                  		
	                  	String datapass = "";
	                  	while(rs.next()){
	           		   		datapass = rs.getString("Password");
	                  	}
	                  	
	                  	if((custPassword.equals(""))){
	                  		
	                  		custWrongEmail.setVisible(true);
	                  	}
	                  	else if((custPassword.equals(datapass))){
	           		 
	           		   		custWrongEmail.setVisible(false);
	           		   		primaryStage.setScene(custTicketScene);

	           		   	}else{
	           		   		
	           		   		custWrongEmail.setVisible(true);
	           		   	}
	           		   		
	            	  }catch(Exception e1){
	           			System.out.println(e1);
	            	  }
		        	}
				});
				
			
			});
			
			
			
			
			buyLiftTicket.setOnAction(new EventHandler<ActionEvent>() {
				int ticketcount = 0;
				@Override public void handle(ActionEvent e) {
					ticketcount++;
					custTicketBought.setVisible(true);
					if(ticketcount > 1){
						custTicketBought.setText("You have successfully bought " + ticketcount + " tickets");
					}
					
				}
			});
			
			buyLesson.setOnAction(new EventHandler<ActionEvent>() {
				int lessoncount = 0;
				@Override public void handle(ActionEvent e) {
					lessoncount++;
					custLessonBought.setVisible(true);
					if(lessoncount > 1){
						custLessonBought.setText("You have successfully bought " + lessoncount + " lessons");
					}
					
				}
			});
			
			
			loginOrCreateAccountCancelButton.setOnAction(loginOrCreateAccountCancelButtonEvent ->
			{
				
				primaryStage.setScene(mainScene);
			
			});
		
		});
		
		emplButton.setOnAction(e -> 
		{
			primaryStage.setScene(emplLoginScene);              
				
				emplLoginCancelButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						primaryStage.setScene(mainScene);
						emplIdBox.clear();
						emplPasswordBox.clear();
						emplWrongId.setVisible(false);
		           }	
				});
				
				
				
				emplLoginOkButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
					
					String emplId = emplIdBox.getText();
					String emplPassword = emplPasswordBox.getText();
					
					
	            	  try{	
	                  	st = con.createStatement();
	                  	rs = st.executeQuery("SELECT * FROM skiresort.employees WHERE EmployeeID = '" + emplId + "';");
	                  		
	                  	String datapass = "";
	                  	while(rs.next()){
	           		   		datapass = rs.getString("Password");
	                  	}
	                  	
	                  	if((emplPassword.equals(""))){
	                  		System.out.println("Wrong Id or Password");
	                  		emplWrongId.setVisible(true);
	                  	}
	                  	else if((emplPassword.equals(datapass))){
	           		   		System.out.println("You are logged in");
	           		   		emplWrongId.setVisible(false);
	           		   		primaryStage.setScene(employeeScene);
							emplIdBox.clear();
							emplPasswordBox.clear();
	           		   		
	           		   	}else{
	           		   		System.out.println("Wrong Id or Password");
	           		   		emplWrongId.setVisible(true);
	           		   	}
	           		   		
	            	  }catch(Exception e1){
	           			System.out.println(e1);
	            	  }
		        	}
				});
				

				
				clockButton.setOnAction(new EventHandler<ActionEvent>() {
					int clockcount = 0;
					@Override public void handle(ActionEvent e) {
						clockcount = clockcount + 1;
						DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
						Date dateobj = new Date();
						if((clockcount % 2) == 0){
							clockButton.setText("Clock In");
							clockText.setText("You have successfully clocked out at " + df.format(dateobj));
							lessonButton.setVisible(true);
					        employeeCenterPane.getChildren().remove(employeeCenterGridPane);
						}else{
							clockButton.setText("Clock Out");
							clockText.setText("You have successfully clocked in at " + df.format(dateobj));
					        employeeCenterPane.getChildren().add(employeeCenterGridPane);
						}
						
						lessonButton.setVisible(true);
						
						lessonButton.setOnAction(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent e) {
								primaryStage.setScene(lessonScene);
								lessonCancelButton.setOnAction(new EventHandler<ActionEvent>() {
									@Override public void handle(ActionEvent e) {
										primaryStage.setScene(employeeScene);
									}
								});
							}
						});
						
                        employeeCancelButton.setOnAction(employeeCancelButtonEvent -> 
                        {
                                      
                               	primaryStage.setScene(emplLoginScene);
                                      
                        });

						
					}
				});
			
				instructorLogoutButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						
					primaryStage.setScene(emplLoginScene);	
					emplIdBox.clear();
					emplPasswordBox.clear();
					
					}
				});
				
			
		});
		
		
		
		
		
	}
	
	//SQL Connection
	public static Connection getConnection() throws Exception{
		try{
		   String driver = "com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://localhost:3306/skiresort";
		   String username = "root";
		   String password = "Comp2650!";
		   Class.forName(driver);
		   
		   con = DriverManager.getConnection(url,username,password);
		   
		   return con;
		}catch(Exception e){
			System.out.println(e);
		}
		  
		return null;
	}
	
	

}
