//Task:                task2.java
//Assigned to:         Admin
//Date assigned:       7th April 2023
//Due date:            7th April 2023
//Task complete?       Yes
//Task description:    Create an java file called task2.java

package task2;

import java.sql.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
        try {
           
            Connection newCon = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "newuser1",
                    "swordfish"
                    );
            
            Statement stmnt = newCon.createStatement();
            ResultSet answer;
            int update;
            
            /* Below I am displaying the original tables
             * There are 4 methods being called
             * each method displays a separate table
             */
            display(stmnt);
            displayCustomer(stmnt);
            displayContractor(stmnt);
            displayArchitect(stmnt);
            System.out.println("\n");
            
            
            // Below I am asking what the user would like to do
            Scanner q1 = new Scanner(System.in);
            System.out.println("Would you like to enter, update, delete, finalise or search?");
            String a1 = q1.next();
            
            if (a1.equals("enter")) {
            	
            	// Adds a new project
            	update = stmnt.executeUpdate(
                        "INSERT INTO project VALUES (1724, 'The Charelton', 'Hotel', '9 Westbrook rd', 156, 'R10 000 000', '2025-04-03', 'Incomplete', 'Incomplete')"
                    );
            	display(stmnt);
                System.out.println("Query complete, " + update + " rows added.");
                
                
                // This asks the user if they would like to update the remaining tables
                Scanner q2 = new Scanner(System.in);
                System.out.println("Would you like to update the remaining tables?");
                String a2 = q2.next();
                
                if (a2.equals("yes")) {
                	
                	// Inserting new projects into the customer table
                	update = stmnt.executeUpdate(
                            "INSERT INTO customer VALUES (1724, 'Callum', '0613581259', 'mathews.callum@gmail.com', '17 Terrace st')"
                        );
                	displayCustomer(stmnt);
                    System.out.println("Query complete, " + update + " rows added.");
                    
                	
                    //------------------------------------------------------------------------------------------------------------------
                    // Inserting new projects into the Contractor table
                	update = stmnt.executeUpdate(
                            "INSERT INTO contractor VALUES (1724, 'Marry', '0105634471', 'marry.buildit@gmail.com', '5 Jonathon rd')"
                        );
                	displayContractor(stmnt);
                    System.out.println("Query complete, " + update + " rows added.");
                    
                    
                  //------------------------------------------------------------------------------------------------------------------
                    // Inserting new projects into the Contractor table
                	update = stmnt.executeUpdate(
                            "INSERT INTO architect VALUES (1724, 'Harry', '0102369985', 'harry.matherson@icloud.com', '19 Pamona ave')"
                        );
                	displayArchitect(stmnt);
                    System.out.println("Query complete, " + update + " rows added.");
                    
                }
                
                
            	
            } else if (a1.equals("update")) {
            	
            	// This asks the user which table they would like to update
            	Scanner q3 = new Scanner(System.in);
                System.out.println("Which table would you like to update, project, customer, contractor or architect?");
                String a3 = q3.next();
                
                if (a3.equals("project")) {
                	
                	// This allows the user to update the project table 
                	update = stmnt.executeUpdate(
                            "UPDATE project SET Complete='Complete' WHERE Proj_Num=1542"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    display(stmnt);
                    
                	
                } else if (a3.equals("customer")) {
                	
                	// This allows the user to update the customer table
                	update = stmnt.executeUpdate(
                            "UPDATE customer SET Number='0605154632' WHERE Proj_Num=1542"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    displayCustomer(stmnt);
                    
                	
                } else if (a3.equals("contractor")) {
                	
                	// This allows the user to update the contractor table
                	update = stmnt.executeUpdate(
                            "UPDATE contractor SET Name='James' WHERE Proj_Num=1542"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    displayContractor(stmnt);
                    
                	
                } else if (a3.equals("architect")) {
                	
                	// This allows the user to update the architect table
                	update = stmnt.executeUpdate(
                            "UPDATE architect SET Email='george.gavin@icloud.com' WHERE Proj_Num=1542"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    displayArchitect(stmnt);
                	
                }
            	
            } else if (a1.equals("delete")) {
            	
            	// This deletes a project identified by the project number
            	update = stmnt.executeUpdate(
                 		"DELETE FROM project WHERE Proj_Num = 1724"
                 		);
                 System.out.println("Query complete, " + update + " rows removed.");
                 display(stmnt);
                 
                 Scanner q4 = new Scanner(System.in);
                 System.out.println("Would you like to delete the remaining tables with the same Proj_Num?");
                 String a4 = q4.next();
                 
                 if (a4.equals("yes")) {
                	 
                	 //------------------------------------------------------------------
                	 // Deleting from the customer table
                	 update = stmnt.executeUpdate(
                      		"DELETE FROM customer WHERE Proj_Num = 1724"
                      		);
                      System.out.println("Query complete, " + update + " rows removed.");
                      displayCustomer(stmnt);
                      
                      //------------------------------------------------------------------
                      // Deleting from the contractor table
                      update = stmnt.executeUpdate(
                       		"DELETE FROM contractor WHERE Proj_Num = 1724"
                       		);
                       System.out.println("Query complete, " + update + " rows removed.");
                       displayContractor(stmnt);
                       
                       //------------------------------------------------------------------
                       // Deleting from the architect table
                       update = stmnt.executeUpdate(
                        		"DELETE FROM architect WHERE Proj_Num = 1724"
                        		);
                        System.out.println("Query complete, " + update + " rows removed.");
                        displayArchitect(stmnt);
                	 
                 }
            	
            } else if (a1.equals("finalise")) {
            	
            	// This allows the user to finalize a project in the project table 
            	update = stmnt.executeUpdate(
                        "UPDATE project SET Complete='finalised' WHERE Proj_Num=1542"
                    );
            	update = stmnt.executeUpdate(
                        "UPDATE project SET Completion_Date='2023-04-20' WHERE Proj_Num=1542"
                    );
                System.out.println("Query complete, " + update + " rows updated.");
                display(stmnt);
                
            	
            } else if (a1.equals("search")) {
            	
            	Scanner q5 = new Scanner(System.in);
                System.out.println("Would you like to search for projects that are incomplete, overdue or by their number?");
                String a5 = q5.next();
                
                // Below returns incomplete projects
                if (a5.equals("incomplete")) {
                	
                	update = stmnt.executeUpdate(
                			"SELECT * FROM project WHERE Complete = 'incomplete'"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    display(stmnt);
                	
                // Below returns overdue projects.
                } else if (a5.equals("overdue")) {
                	
                	update = stmnt.executeUpdate(
                			"select * from project where Deadline < NOW() and Complete = 'Incomplete'"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    display(stmnt);
                    
                // Below return the project by the project number
                } else if (a5.equals("number")) {
                	
                	update = stmnt.executeUpdate(
                			"select * from project where Proj_Num = 1234"
                        );
                    System.out.println("Query complete, " + update + " rows updated.");
                    display(stmnt);
                	
                }
                
            }
            
            
            System.out.println("\n");
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	// Below is the method that allows the project table to be displayed
	public static void display(Statement stmnt) throws SQLException{
        
		System.out.println("The Project table:");
        ResultSet rslt = stmnt.executeQuery("SELECT Proj_Num, Proj_Name, Building_Type, Address, ERF_Num, Price, Deadline, Completion_Date, Complete FROM project");
        while (rslt.next()) {
            System.out.println(
            		rslt.getInt("Proj_Num") + ", "
                            + rslt.getString("Proj_Name") + ", "
                            + rslt.getString("Building_Type") + ", "
                            + rslt.getString("Address") + ", "
                            + rslt.getInt("ERF_Num") + ", "
                            + rslt.getString("Price") + ", "
                            + rslt.getString("Deadline") + ", "
                            + rslt.getString("Completion_Date") + ", "
                            + rslt.getString("Complete")
                );
        }
   
    } // display
	
	
	// Below is the method that allows the customer table to be displayed
	public static void displayCustomer(Statement stmnt) throws SQLException{
		
		System.out.println("\nThe Customer table:");
        ResultSet rslt2 = stmnt.executeQuery("SELECT Proj_Num, Name, Number, Email, Address FROM Customer");
        while (rslt2.next()) {
            System.out.println(
            		rslt2.getInt("Proj_Num") + ", "
                            + rslt2.getString("Name") + ", "
                            + rslt2.getInt("Number") + ", "
                            + rslt2.getString("Email") + ", "
                            + rslt2.getString("Address")
                );
        }
		
	} // displayCustomer
	
	
	// Below is the method that allows the contractor table to be displayed
	public static void displayContractor(Statement stmnt) throws SQLException{
		
		System.out.println("\nThe Contractor table:");
        ResultSet rslt3 = stmnt.executeQuery("SELECT Proj_Num, Name, Number, Email, Address FROM Contractor");
        while (rslt3.next()) {
            System.out.println(
            		rslt3.getInt("Proj_Num") + ", "
                            + rslt3.getString("Name") + ", "
                            + rslt3.getInt("Number") + ", "
                            + rslt3.getString("Email") + ", "
                            + rslt3.getString("Address")
                );
        }
		
	} // displayContractor
	
	
	// Below is the method that allows the architect table to be displayed
	public static void displayArchitect(Statement stmnt) throws SQLException {
		
		System.out.println("\nThe Architect table:");
        ResultSet rslt4 = stmnt.executeQuery("SELECT Proj_Num, Name, Number, Email, Address FROM Architect");
        while (rslt4.next()) {
            System.out.println(
            		rslt4.getInt("Proj_Num") + ", "
                            + rslt4.getString("Name") + ", "
                            + rslt4.getInt("Number") + ", "
                            + rslt4.getString("Email") + ", "
                            + rslt4.getString("Address")
                );
        }
		
	} // displayArchitect
	

}
