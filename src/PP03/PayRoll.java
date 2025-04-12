package PP03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PayRoll {
	
	private String fileName;
	private PayRecord[] payRecords;
	private Employee[] employees;
	
	private  double totalNetPay;
	private  double avgNetPay;
	
	public PayRoll(String fileName, int n){
		
		this.fileName = fileName;
        this.payRecords = new PayRecord[n];	
        this.employees = new Employee[n];
	}
	
	
   public void readFromFile(){
		
		// read the initial data from PayRoll file to create the full 
	   // pay records with gross pay, taxes, and net pay, and then store it in PayRecord.txt file
	   BufferedReader br = null;
	   try {
		   
			String line;

			br = new BufferedReader(new FileReader("data.txt"));

			while ((line = br.readLine()) != null) {
				
				String[] stringArr = line.split(",");
				for (String s:stringArr) {
					if(line.contains("employee")) {
						
					}
					else if(line.contains("payRecord")) {
						
					}
				}
				   
				
				System.out.println();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	} 
   
   
   public void writeToFile(){
		
		// write employees' pay records to the PayRecord.txt file, it should add employee pay record to the current file data
	   
		
	} 
   
	public Employee createEmployee(int eID, Status empStatus, String fname, String lname,Address address){
		// creates a new Employee object and add it to the employees array, you need to pass parameters to this method
		Employee emp = new Employee(eID, empStatus, fname, lname, address);
		employees[Employee.getEmployeeCount()] = emp;
		return emp;
	}
	
 
	public void createPayRecord(Employee employee ){
		
		// creates a new PayRecord for an Employee object and add it to  the payRecords array, you need to pass parameters to this method
		
		
		
		
	}
	
	
    public  void displayPayRecord(){
		
		// it shows all payroll records for all currently added employee and the total net pay and average net pay in the GUI text area
    	// at should append data to text area, it must not overwrite data in the GUI text area
		
	}

    
   public double avgNetPay(){
		
		  	// returns the average of the total net pay of all added employees
	   return 0;
		
	}
    	

}
