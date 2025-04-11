package PP03;

import java.util.Date;


public class PayPeriod {
	
	private int pID;
    private Date pStartDate, pEndDate;

    
    // 1- add the class constructor
    // 2- add the setters/getters methods
    // 3- add override method toString() 
    
	PayPeriod(int pID, Date pStartDate, Date pEndDate) {
		this.pID = pID;
		this.pStartDate = pStartDate;
		this.pEndDate = pEndDate;
	}


	public int getpID() {
		return pID;
	}


	public void setpID(int pID) {
		this.pID = pID;
	}


	public Date getpStartDate() {
		return pStartDate;
	}


	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}


	public Date getpEndDate() {
		return pEndDate;
	}


	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}


	@Override
	public String toString() {
		return "PayPeriod [pID=" + pID + ", pStartDate=" + pStartDate + ", pEndDate=" + pEndDate + "]";
	}
	
}
