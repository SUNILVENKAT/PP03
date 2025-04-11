package PP03;

public class TaxIncome implements Taxable{

	// 1- this class implements the Taxable interface
	// 2- implement all the unimplemented abstract methods in the Taxable Interface, income tax is computed based on state and federal taxes   
	
	@Override
	public double compStateTax(double grossPay) {
		return grossPay * STATE_TAX;
	}

	@Override
	public double compFederalTax(double grossPay) {
		return grossPay * FEDERAL_TAX;
	}

	@Override
	public double compIncomeTax(double grossPay) {
		return compStateTax(grossPay) + compFederalTax(grossPay);
	}
}
