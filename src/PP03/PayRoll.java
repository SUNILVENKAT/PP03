package PP03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PayRoll {

	private String fileName;
	private PayRecord[] payRecords;
	private Employee[] employees;

	private double totalNetPay;
	private double avgNetPay;

	public PayRoll(String fileName, int n) {

		this.fileName = fileName;
		this.payRecords = new PayRecord[n];
		this.employees = new Employee[n];
	}

	public void readFromFile() throws ParseException {

		// read the initial data from PayRoll file to create the full
		// pay records with gross pay, taxes, and net pay, and then store it in
		// PayRecord.txt file
		BufferedReader br = null;
		try {

			String line;

			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null) {

				String[] stringArr = line.split(",");

				if (line.contains("employee")) {
					int id = Integer.parseInt(stringArr[1].trim());
					String fname = stringArr[2].trim();
					String lname = stringArr[3].trim();
					Status status = null;
					if (stringArr[4].trim().equalsIgnoreCase("FULLTIME")) {
						status = Status.FullTime;
					} else if (stringArr[4].trim().equalsIgnoreCase("HOURLY")) {
						status = Status.Hourly;
					}
					String street = stringArr[5].trim();
					int houseNo = Integer.parseInt(stringArr[6].trim());
					String city = stringArr[7].trim();
					String state = stringArr[8].trim();
					int zipCode = Integer.parseInt(stringArr[9].trim());

					createEmployee(id, status, lname, fname, street, houseNo, city, state, zipCode);

				} else if (line.contains("payRecord")) {
					int id = Integer.parseInt(stringArr[1].trim());
					int empId = Integer.parseInt(stringArr[2].trim());
					double payHours = 0;
					double payRate = 0;
					double monthlyIncome = 0;
					int numMonths = 0;
					if (stringArr[3].contains("m")) {
						monthlyIncome = Double
								.parseDouble(stringArr[3].trim().substring(0, stringArr[3].indexOf("<") - 1));
						numMonths = Integer.parseInt(stringArr[4].trim().substring(0, stringArr[4].indexOf("<") - 1));
					} else if (stringArr[3].contains("h")) {
						payRate = Double.parseDouble(stringArr[3].trim().substring(0, stringArr[3].indexOf("<") - 1));
						payHours = Double.parseDouble(stringArr[4].trim().substring(0, stringArr[4].indexOf("<") - 1));
					}
					int pId = Integer.parseInt(stringArr[5].trim());
					Date startDate = new java.text.SimpleDateFormat("yyyy/MM/dd")
							.parse(new java.text.SimpleDateFormat("yyyy/MM/dd")
									.format(new java.text.SimpleDateFormat("MM/dd/yyyy").parse(stringArr[6])));
					Date endDate = new java.text.SimpleDateFormat("yyyy/MM/dd")
							.parse(new java.text.SimpleDateFormat("yyyy/MM/dd")
									.format(new java.text.SimpleDateFormat("MM/dd/yyyy").parse(stringArr[7])));
					Employee employee = null;

					for (int i = 0; i < Employee.getEmployeeCount(); i++) {
						if (employees[i].geteID() == empId) {
							employee = employees[i];
						}
					}

					if (employee.getEmpStatus() == Status.FullTime) {
						createPayRecord(id, employee, new PayPeriod(pId, startDate, endDate), monthlyIncome, numMonths);
					} else if (employee.getEmpStatus() == Status.Hourly) {
						createPayRecord(id, employee, new PayPeriod(pId, startDate, endDate), payHours, payRate);
					}

				}

			}

			avgNetPay();

			writeToFile();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void writeToFile() {

		// write employees' pay records to the PayRecord.txt file, it should add
		// employee pay record to the current file data

		try {
			String data = "Pay Record ID\tFirst Name\tLastName\tStreet\tHouse Number\tCity\tState\tZipCode\tEmployee ID\tEmployee Status\t"
					+ "Pay Period ID\tPay start date\tPay end date\tHours\tHourly Rate\tMontly Income\tNo. of months\tGross Pay\tTax\tNet Pay\n";

			for (int i = 0; i < PayRecord.getPayRecordCount(); i++) {
				data += payRecords[i].toString() + "\n";
			}
			data += "\n\n";

			data += "\tTotal No. of Employees\tTotal Net Pay\tAverage Net Pay\n";
			data += "\t" + PayRecord.getPayRecordCount() + "\t" + totalNetPay + "\t" + avgNetPay;

			File newTextFile = new File("PayRecord.txt");

			FileWriter fw = new FileWriter(newTextFile, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.newLine();
			bw.write(data);
			bw.close();

		} catch (IOException iox) {
			// do stuff with exception
			iox.printStackTrace();
		}
	}

	public Employee createEmployee(int eID, Status empStatus, String fname, String lname, String street, int houseNo,
			String city, String state, int zipcode) {
		// creates a new Employee object and add it to the employees array, you need to
		// pass parameters to this method
		Employee emp = new Employee(eID, empStatus, fname, lname, new Address(street, houseNo, city, state, zipcode));
		employees[Employee.getEmployeeCount() - 1] = emp;
		return emp;
	}

	public void createPayRecord(int id, Employee employee, PayPeriod payPeriod, double monthlyIncome, int nmonths) {

		// creates a new PayRecord for an Employee object and add it to the payRecords
		// array, you need to pass parameters to this method
		payRecords[PayRecord.getPayRecordCount()] = new PayRecord(id, employee, payPeriod, monthlyIncome, nmonths);

	}

	public void createPayRecord(int id, Employee employee, PayPeriod payPeriod, double hours, double rate) {

		// creates a new PayRecord for an Employee object and add it to the payRecords
		// array, you need to pass parameters to this method
		payRecords[PayRecord.getPayRecordCount()] = new PayRecord(id, employee, payPeriod, hours, rate);
	}

	public void displayPayRecord(JTextArea textArea) {

		// it shows all payroll records for all currently added employee and the total
		// net pay and average net pay in the GUI text area
		// at should append data to text area, it must not overwrite data in the GUI
		// text area
		String dispString = "Pay Record ID\tFirst Name\tLastName\tStreet\tHouse Number\tCity\tState\tZipCode\tEmployee ID\tEmployee Status\t"
				+ "Pay Period ID\tPay start date\tPay end date\tHours\tHourly Rate\tMontly Income\tNo. of months\tGross Pay\tTax\tNet Pay\n";

		for (int i = 0; i < PayRecord.getPayRecordCount(); i++) {
			dispString += payRecords[i].toString() + "\n";
		}
		dispString += "\n\n";

		dispString += "\tTotal No. of Employees\tTotal Net Pay\tAverage Net Pay\n";
		dispString += "\t" + PayRecord.getPayRecordCount() + "\t" + totalNetPay + "\t"
				+ String.format("%.2f", avgNetPay);

		textArea.setText(dispString);

	}

	public double avgNetPay() {

		// returns the average of the total net pay of all added employees

		for (int i = 0; i < PayRecord.getPayRecordCount(); i++) {
			totalNetPay += payRecords[i].netPay();
		}

		avgNetPay = totalNetPay / PayRecord.getPayRecordCount();

		return avgNetPay;

	}

}
