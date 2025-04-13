package PP03;


public class Employee extends Person{
	
	private int eID;
    private Status empStatus;
    private static int employeeCount = 0;
	
    
    // 1- The Employee class extends superclass Person
    // 2- add the subclass Employee constructor that calls the supper Person class constructor, you should provide input data for all parent class data fields
   // 3- add setters/getters methods
   // 4- add override toString() method that overrides toString() in the superclass Person 
    
    Employee(int eID, Status empStatus, String fname, String lname, Address address) {
		super(fname, lname, address);
		this.eID = eID;
		this.empStatus = empStatus;
		this.employeeCount++;
	}


	public int geteID() {
		return eID;
	}


	public void seteID(int eID) {
		this.eID = eID;
	}


	public Status getEmpStatus() {
		return empStatus;
	}


	public void setEmpStatus(Status empStatus) {
		this.empStatus = empStatus;
	}
	
	public static int getEmployeeCount() {
		return employeeCount;
	}


	@Override
	public String toString() {
		return  super.toString() + "\t" + eID + "\t" + empStatus;
	}
    
    
    

	
}
