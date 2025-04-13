package PP03;


public class PayRecord {
	
	private int rID;
    private Employee employee;
    private PayPeriod payPeriod;
    private TaxIncome payTax;
    
    private double payHours;
    private double payRate;
    
    private double montlyIncome;
    private int numMonths;
    
    private static int payRecordCount = 0;
    
    public static final int REG_HOURS = 40;
    public static final double OT_RATE = 1.25;
    
    // pay record constructor for hourly employee
    public PayRecord(int id, Employee e, PayPeriod period, double hours, double rate){
    	
    	this.rID = id;
    	this.employee = e;
    	this.payPeriod = period;
    	this.payHours = hours;
    	this.payRate = rate;
    	this.montlyIncome = 0;
    	this.numMonths = 0;
    	this.payTax = new TaxIncome();
    	this.payRecordCount++;
    }
    
    // pay record constructor for full time employee
    public PayRecord(int id, Employee e, PayPeriod period, double mIncome, int mNum){
 	
 	this.rID = id;
 	this.employee = e;
 	this.payPeriod = period;
 	this.payHours = 0;
 	this.payRate = 0;
 	this.montlyIncome = mIncome;
 	this.numMonths = mNum;
 	this.payTax = new TaxIncome();
 	this.payRecordCount++;
 }


  // 1- add setters and getters methods
  // 2- add override method toString()
  // 3- complete the code in the following methods: grossPay() and netPay()
    
  public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PayPeriod getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}

	public TaxIncome getPayTax() {
		return payTax;
	}

	public void setPayTax(TaxIncome payTax) {
		this.payTax = payTax;
	}

	public double getPayHours() {
		return payHours;
	}

	public void setPayHours(double payHours) {
		this.payHours = payHours;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getMontlyIncome() {
		return montlyIncome;
	}

	public void setMontlyIncome(double montlyIncome) {
		this.montlyIncome = montlyIncome;
	}

	public int getNumMonths() {
		return numMonths;
	}

	public void setNumMonths(int numMonths) {
		this.numMonths = numMonths;
	}

	public static int getPayRecordCount() {
		return payRecordCount;
	}

	@Override
	public String toString() {
		return rID + "\t" + employee.toString() + "\t" + payPeriod.toString() + "\t" +
				+ payHours + "\t" + payRate + "\t" + montlyIncome + "\t" + numMonths + "\t" + grossPay() + payTax.compIncomeTax(grossPay()) + netPay();
	}

	// complete the code in this method to compute the net pay of the employee after taxes (state and federal)
     public double netPay(){
		  return grossPay() - payTax.compIncomeTax(grossPay());
  }
     
     // complete the code to compute the gross pay for the employee based on the employee status
 	public double grossPay(){
 		double grossPay = 0;
 		if(employee.getEmpStatus() == Status.FullTime) {
 			grossPay = numMonths * montlyIncome;
 		}else if(employee.getEmpStatus() == Status.Hourly) {
 			if(payHours > REG_HOURS) {
 				grossPay = (payRate * REG_HOURS) + (payRate * OT_RATE * (payHours - REG_HOURS));
 			}else {
 				grossPay = payRate * payHours;
 			}
 		}
 		return grossPay;
 	}
 

}
